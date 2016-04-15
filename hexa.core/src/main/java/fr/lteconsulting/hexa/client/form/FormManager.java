package fr.lteconsulting.hexa.client.form;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.application.archi.View;
import fr.lteconsulting.hexa.client.form.fieldtypes.FieldType;
import fr.lteconsulting.hexa.client.form.fieldtypes.FieldType.FieldChangeHandler;
import fr.lteconsulting.hexa.client.form.fieldtypes.FieldType.FieldChangeHandlerManager;
import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;
import fr.lteconsulting.hexa.client.tools.HTMLStream;

public class FormManager implements View
{
	public interface Constraint
	{
		void install( FormManager formManager );

		boolean evaluate();

		void display();
	}

	public interface Marshall<T>
	{
		T get( JSONValue value );

		JSONValue get( T object );
	}

	class Info
	{
		String name;
		int row;

		Info( String name, int row )
		{
			this.name = name;
			this.row = row;
		}

		GroupInfo isGroup()
		{
			if( this instanceof GroupInfo )
				return (GroupInfo) this;
			return null;
		}

		FieldInfo isField()
		{
			if( this instanceof FieldInfo )
				return (FieldInfo) this;
			return null;
		}
	}

	class GroupInfo extends Info
	{
		public GroupInfo( String name, GroupInfo parent, int row )
		{
			super( name, row );

			if( parent != null )
				parent.addChild( this );
			else
				fields.add( this );
		}

		ArrayList<Info> children = null;

		void addChild( Info child )
		{
			if( children == null )
				children = new ArrayList<Info>();
			children.add( child );
		}
	}

	class FieldInfo extends Info
	{
		FieldType type = null;
		Widget widget = null;

		FieldInfo( String name, GroupInfo parent, int row, FieldType type, Widget widget )
		{
			super( name, row );

			this.type = type;
			this.widget = widget;

			if( parent != null )
				parent.addChild( this );
			else
				fields.add( this );
		}
	}

	ArrayList<Constraint> constraints = new ArrayList<FormManager.Constraint>();

	FlexTable table = new FlexTable();

	public FormManager()
	{
	}

	public boolean validate()
	{
		boolean result = true;
		for( Constraint c : constraints )
		{
			if( !c.evaluate() )
				result = false;
			c.display();
		}

		if( !result )
		{
			Window.alert( "Please correct the form as some fields are not valid (they are indicated in red)." );
		}

		return result;
	}

	public boolean isValidated()
	{
		boolean result = true;
		for( Constraint c : constraints )
		{
			if( !c.evaluate() )
				result = false;
			c.display();
		}

		return result;
	}

	@Override
	public Widget asWidget()
	{
		return table;
	}

	public void showField( Object field, boolean fShow )
	{
		FieldInfo info = (FieldInfo) field;

		if( fShow )
			table.getRowFormatter().getElement( info.row ).getStyle().clearDisplay();
		else
			table.getRowFormatter().getElement( info.row ).getStyle().setDisplay( Display.NONE );
	}

	// TODO here temporarily
	HashMap<FieldInfo, HTMLStream> fieldConstraintSlots = new HashMap<FieldInfo, HTMLStream>();
	ArrayList<Info> fields = new ArrayList<Info>();

	HashMap<FieldInfo, Object> fieldWatcherRegistrations = null;
	HashMap<FieldInfo, ArrayList<Constraint>> fieldsWatchingConstraints = null;
	HashMap<FieldInfo, ArrayList<IAsyncCallback<Object>>> fieldsWatchers = null;

	public AcceptsOneWidget addConstraintSlot( Constraint client, Object field )
	{
		FieldInfo info = (FieldInfo) field;

		HTMLStream stream = fieldConstraintSlots.get( info );
		if( stream == null )
		{
			stream = new HTMLStream();
			fieldConstraintSlots.put( info, stream );

			table.setWidget( info.row, 2, stream );
		}

		SimplePanel slot = new SimplePanel();
		stream.addLeft( slot );

		return slot;
	}

	private void prepareFieldToBeWatched( FieldInfo info )
	{
		if( fieldWatcherRegistrations == null )
			fieldWatcherRegistrations = new HashMap<FieldInfo, Object>();

		// ensure we are registered to this field's changes
		Object registration = fieldWatcherRegistrations.get( info );
		if( registration == null )
		{
			FieldChangeHandlerManager mng = info.type.getHandlerManager( info.widget );
			if( mng == null )
				return;

			registration = mng.addChangeHandler( fieldChangeHandler, info );
			fieldWatcherRegistrations.put( info, registration );
		}
	}

	public void watchField( Object field, IAsyncCallback<Object> callback )
	{
		FieldInfo info = (FieldInfo) field;

		prepareFieldToBeWatched( info );

		// add the constraint to the watchers list
		if( fieldsWatchers == null )
			fieldsWatchers = new HashMap<FieldInfo, ArrayList<IAsyncCallback<Object>>>();
		ArrayList<IAsyncCallback<Object>> watchers = fieldsWatchers.get( info );
		if( watchers == null )
		{
			watchers = new ArrayList<IAsyncCallback<Object>>();
			fieldsWatchers.put( info, watchers );
		}
		watchers.add( callback );
	}

	public void watchConstraintField( Constraint client, Object field )
	{
		FieldInfo info = (FieldInfo) field;

		prepareFieldToBeWatched( info );

		// add the constraint to the watchers list
		if( fieldsWatchingConstraints == null )
			fieldsWatchingConstraints = new HashMap<FieldInfo, ArrayList<Constraint>>();
		ArrayList<Constraint> constraints = fieldsWatchingConstraints.get( info );
		if( constraints == null )
		{
			constraints = new ArrayList<FormManager.Constraint>();
			fieldsWatchingConstraints.put( info, constraints );
		}
		constraints.add( client );
	}

	FieldChangeHandler fieldChangeHandler = new FieldChangeHandler()
	{
		@Override
		public void onFieldChange( Object cookie )
		{
			FieldInfo info = (FieldInfo) cookie;

			GWT.log( "changed field in form " + info.name );

			if( fieldsWatchingConstraints != null )
			{
				ArrayList<Constraint> constraints = fieldsWatchingConstraints.get( info );
				if( constraints != null )
				{
					for( Constraint c : constraints )
						c.display();
				}
			}

			if( fieldsWatchers != null )
			{
				ArrayList<IAsyncCallback<Object>> watchers = fieldsWatchers.get( info );
				if( watchers != null )
				{
					for( IAsyncCallback<Object> watcher : watchers )
						watcher.onSuccess( info );
				}
			}
		}
	};

	/*
	 * HashMap<FieldInfo,HTMLStream> fieldCommentsSlots = new HashMap<FieldInfo,
	 * HTMLStream>(); public AcceptsOneWidget addCommentSlot( Object field ) {
	 * FieldInfo info = (FieldInfo)field;
	 * 
	 * HTMLStream stream = fieldCommentsSlots.get( info ); if( stream == null )
	 * { stream = new HTMLStream(); fieldCommentsSlots.put( info, stream );
	 * 
	 * table.setWidget( info.row, 3, stream );
	 * table.getCellFormatter().getElement( info.row, 3
	 * ).getStyle().setFontWeight( FontWeight.NORMAL ); }
	 * 
	 * SimplePanel slot = new SimplePanel(); stream.addLeft( slot );
	 * 
	 * return slot; }
	 */

	public Object addGroup( String display, String name, Object parent )
	{
		int row = display != null ? table.getRowCount() : -1;

		// create the new group
		GroupInfo parentInfo = (GroupInfo) parent;
		GroupInfo info = new GroupInfo( name, parentInfo, row );

		// display the new group
		if( display != null && row >= 0 )
		{
			table.setHTML( row, 0, display );
			table.getCellFormatter().getElement( row, 0 ).getStyle().setVerticalAlign( VerticalAlign.TOP );
			table.getCellFormatter().getElement( row, 0 ).setAttribute( "colSpan", "2" );
			table.getCellFormatter().getElement( row, 0 ).getStyle().setPaddingTop( 25, Unit.PX );
			table.getCellFormatter().getElement( row, 0 ).getStyle().setPaddingBottom( 10, Unit.PX );
			table.getCellFormatter().getElement( row, 0 ).getStyle().setFontWeight( FontWeight.BOLD );
		}

		return info;
	}

	public Object addField( String display, String name, FieldType fieldType )
	{
		return addField( display, name, fieldType, null );
	}

	public Object addField( String display, String name, FieldType fieldType, Object parent )
	{
		return addField( display, name, fieldType, fieldType.getWidget(), parent );
	}

	public Object addField( String display, String name, FieldType fieldType, Widget fieldWidget, Object parent )
	{
		int row = table.getRowCount();

		// create the new field
		GroupInfo parentInfo = (GroupInfo) parent;
		FieldInfo info = new FieldInfo( name, parentInfo, row, fieldType, fieldWidget );

		// Display the new field
		if( display != null )
		{
			HTMLStream stream = new HTMLStream();
			stream.addRight( new HTML( display ) );
			stream.clFl();
			table.setWidget( row, 0, stream );
		}
		table.setWidget( row, 1, info.widget );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setVerticalAlign( VerticalAlign.TOP );
		table.getCellFormatter().getElement( row, 1 ).getStyle().setVerticalAlign( VerticalAlign.TOP );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setFontWeight( FontWeight.BOLD );

		return info;
	}

	public JSONObject getJSONSerialized()
	{
		return getGroupSerialized( fields );
	}

	JSONObject getGroupSerialized( Iterable<Info> infos )
	{
		JSONObject object = new JSONObject();

		for( Info info : infos )
		{
			FieldInfo field = info.isField();
			if( field != null )
			{
				if( field.name != null )
					object.put( field.name, getFieldValue( field ) );
			}
			else
			{
				GroupInfo group = info.isGroup();
				object.put( group.name, getGroupSerialized( group.children ) );
			}
		}

		// Window.alert( "Serialized form : " + object.toString() );

		return object;
	}

	public void addSection( String text )
	{
		int row = table.getRowCount();
		table.setText( row, 0, text );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setVerticalAlign( VerticalAlign.TOP );
		table.getCellFormatter().getElement( row, 0 ).setAttribute( "colSpan", "2" );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setPaddingTop( 25, Unit.PX );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setPaddingBottom( 10, Unit.PX );
	}
	
	public void addHTMLSection( String html )
	{
		int row = table.getRowCount();
		table.setHTML( row, 0, html );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setVerticalAlign( VerticalAlign.TOP );
		table.getCellFormatter().getElement( row, 0 ).setAttribute( "colSpan", "2" );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setPaddingTop( 25, Unit.PX );
		table.getCellFormatter().getElement( row, 0 ).getStyle().setPaddingBottom( 10, Unit.PX );
	}

	public void addConstraint( Constraint constraint )
	{
		constraints.add( constraint );

		// put constraint in its context
		constraint.install( this );

		constraint.display();
	}

	public void setFieldValue( Object field, JSONValue value )
	{
		FieldInfo info = (FieldInfo) field;

		info.type.setValue( info.widget, value );
	}

	public JSONValue getFieldValue( Object field )
	{
		FieldInfo info = (FieldInfo) field;

		return info.type.getValue( info.widget );
	}
}
