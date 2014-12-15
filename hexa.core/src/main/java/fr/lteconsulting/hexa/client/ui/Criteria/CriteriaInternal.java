/**
 *
 */
package fr.lteconsulting.hexa.client.ui.Criteria;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.comm.GenericJSO;
import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.interfaces.ICriteriaFieldMng;
import fr.lteconsulting.hexa.client.tools.HexaTools;
import fr.lteconsulting.hexa.client.ui.widget.ListBoxEx;

public class CriteriaInternal extends Composite
{
	public static String getBeautifulText( String searchString )
	{
		GenericJSO jso = null;
		try
		{
			jso = JsonUtils.unsafeEval( searchString );
		}
		catch( Exception e )
		{
		}
		if( jso == null )
			return null;

		return getBeautifulText( jso );
	}

	private static String getBeautifulText( GenericJSO jso )
	{
		String op = jso.getString( "op" );

		if( (op.compareTo( "and" ) == 0) || (op.compareTo( "or" ) == 0) )
		{
			JsArray<GenericJSO> ops = jso.getArray( "ops" );
			StringBuilder b = new StringBuilder();
			for( int i = 0; i < ops.length(); i++ )
			{
				b.append( getBeautifulText( ops.get( i ) ) );
				if( i < ops.length() - 1 )
					b.append( " " + op + " " );
			}

			return b.toString();
		}

		if( op.compareTo( "eq" ) == 0 )
			op = "=";
		if( op.compareTo( "ne" ) == 0 )
			op = "<>";
		else if( op.compareTo( "lt" ) == 0 )
			op = "<";
		else if( op.compareTo( "lte" ) == 0 )
			op = "<=";
		else if( op.compareTo( "gt" ) == 0 )
			op = ">";
		else if( op.compareTo( "gte" ) == 0 )
			op = ">=";

		return jso.getString( "field" ) + " " + op + " '" + jso.getString( "value" ) + "'";
	}

	String op = "eq";

	ArrayList<CriteriaInternal> ops = new ArrayList<CriteriaInternal>();
	int direction = 0;

	ICriteriaFieldMng field;
	String value = "";

	ICriteriaFieldMng[] criteriaMngs;

	public enum CriteriaOp
	{
		eq( "=" ),
		ne( "<>" ),
		gt( ">" ),
		gte( ">=" ),
		lt( "<" ),
		lte( "<=" );

		String display;

		CriteriaOp( String display )
		{
			this.display = display;
		}

		public String getDisplay()
		{
			return display;
		}
	}

	interface CriteriaInternalUiBinder extends UiBinder<Widget, CriteriaInternal>
	{
	}

	interface Css extends HexaCss
	{
		public static final Css CSS = GWT.create( Css.class );

		String main();
	}

	private static CriteriaInternalUiBinder uiBinder = GWT.create( CriteriaInternalUiBinder.class );

	Widget closeButton = null;

	@UiField
	Anchor andAnchor;
	@UiField
	Anchor orAnchor;
	@UiField
	Anchor compareAnchor;

	@UiField
	SimplePanel closeSpot;

	@UiField
	SimplePanel content;

	public CriteriaInternal( ICriteriaFieldMng[] criteriaMngs )
	{
		this.criteriaMngs = criteriaMngs;
		field = criteriaMngs[0];

		initWidget( uiBinder.createAndBindUi( this ) );

		addStyleName( Css.CSS.main() );

		andAnchor.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				op = "and";
				UpdateInterface();
				event.preventDefault();
			}
		} );
		orAnchor.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				op = "or";
				UpdateInterface();
				event.preventDefault();
			}
		} );
		compareAnchor.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				op = "eq";
				UpdateInterface();
				event.preventDefault();
			}
		} );

		UpdateInterface();
	}

	private void SetFlowDirection( int dir )
	{
		direction = dir;
	}

	private void SetCloseButton( Widget w )
	{
		closeButton = w;
	}

	private void UpdateInterface()
	{
		andAnchor.getElement().getStyle().setFontWeight( op.compareTo( "and" ) == 0 ? FontWeight.BOLD : FontWeight.LIGHTER );
		orAnchor.getElement().getStyle().setFontWeight( op.compareTo( "or" ) == 0 ? FontWeight.BOLD : FontWeight.LIGHTER );
		compareAnchor.getElement().getStyle().setFontWeight( op.compareTo( "eq" ) == 0 ? FontWeight.BOLD : FontWeight.LIGHTER );

		// if any, position the close button
		if( closeButton != null )
			closeSpot.setWidget( closeButton );
		else
			closeSpot.clear();

		// different display according to node type
		if( (op.compareTo( "and" ) == 0) || (op.compareTo( "or" ) == 0) )
		{
			// ops, op, ops, op, .., ops, '+'
			Widget realPanel = null;
			InsertPanel panel = null;
			if( direction == 0 )
			{
				VerticalPanel vp = new VerticalPanel();
				realPanel = vp;
				panel = vp;
			}
			else if( direction == 1 )
			{
				HorizontalPanel hp = new HorizontalPanel();
				realPanel = hp;
				panel = hp;
			}

			if( ops.size() < 1 )
			{
				ops.add( new CriteriaInternal( criteriaMngs ) );
			}

			for( int i = 0; i < ops.size(); i++ )
			{
				// HorizontalPanel hp = new HorizontalPanel();

				final CriteriaInternal criteria = ops.get( i );
				// hp.add( criteria );
				criteria.SetFlowDirection( (direction + 1) % 2 );

				Anchor delAnchor = new Anchor( "remove" );
				delAnchor.setHref( "#" );
				delAnchor.addClickHandler( new ClickHandler()
				{
					@Override
					public void onClick( ClickEvent event )
					{
						ops.remove( criteria );
						UpdateInterface();
						event.preventDefault();
					}
				} );
				criteria.SetCloseButton( delAnchor );

				criteria.UpdateInterface();
				// hp.add( delButton );
				panel.add( criteria );

				if( i < ops.size() - 1 )
				{
					HTML ophtml = new HTML( op );
					ophtml.addStyleName( "CriteriaOperator" );
					panel.add( ophtml );
				}
			}

			Button addButton = new Button( "..." );
			addButton.addClickHandler( new ClickHandler()
			{
				@Override
				public void onClick( ClickEvent event )
				{
					ops.add( new CriteriaInternal( criteriaMngs ) );
					UpdateInterface();
				}
			} );
			panel.add( addButton );

			content.setWidget( realPanel );
		}
		else if( op.length() > 0 )
		{
			// propose field and value
			ListBoxEx lbField = new ListBoxEx();
			int curSel = -1;
			for( int i = 0; i < criteriaMngs.length; i++ )
			{
				if( criteriaMngs[i] == field )
					curSel = i;
				lbField.addItem( criteriaMngs[i].getDisplayName(), i );
			}
			if( curSel >= 0 )
				lbField.setSelected( curSel );
			lbField.setCallback( new ListBoxEx.Callback()
			{
				@Override
				public void onListBoxExChange( ListBoxEx listBoxEx, Object cookie )
				{
					int sel = listBoxEx.getSelected();
					field = criteriaMngs[sel];
					UpdateInterface();
				}
			}, null );

			HorizontalPanel hp = new HorizontalPanel();

			hp.add( lbField );

			ListBoxEx lbOp = new ListBoxEx();
			for( CriteriaOp v : CriteriaOp.values() )
				lbOp.addItem( v.getDisplay(), v.ordinal() );
			try
			{
				lbOp.setSelected( CriteriaOp.valueOf( op ).ordinal() );
			}
			catch( Exception e )
			{
			}
			lbOp.setCallback( new ListBoxEx.Callback()
			{
				@Override
				public void onListBoxExChange( ListBoxEx listBoxEx, Object cookie )
				{
					op = CriteriaOp.values()[listBoxEx.getSelected()].name();
					UpdateInterface();
				}
			}, null );
			hp.add( lbOp );

			if( field != null )
			{
				Widget valueWidget = field.getValueWidget( value, new ICriteriaFieldMng.Callback()
				{
					@Override
					public void onValueChange( String newValue )
					{
						value = newValue;
					}
				} );
				hp.add( valueWidget );
			}

			content.setWidget( hp );
		}
	}

	@Override
	public String toString()
	{
		return getSearchString();
	}

	public String getSearchString()
	{
		if( op.length() == 0 )
			return "";

		if( (op.compareTo( "and" ) == 0) || (op.compareTo( "or" ) == 0) )
		{
			String opsSearchString = "[" + HexaTools.arrayToString( ops ) + "]";
			return "{ \"op\":\"" + op + "\" , \"ops\":" + opsSearchString + " }";
		}
		else
		{
			return "{ \"field\":\"" + field.getField() + "\", \"op\":\"" + op + "\", \"value\":\"" + value + "\" }";
		}
	}

	public JSONValue getSearchJson()
	{
		if( op.length() == 0 )
			return null;

		if( (op.compareTo( "and" ) == 0) || (op.compareTo( "or" ) == 0) )
		{
			JSONObject obj = new JSONObject();

			obj.put( "op", new JSONString( op ) );

			JSONArray opsArray = new JSONArray();
			for( int i = 0; i < ops.size(); i++ )
			{
				CriteriaInternal ci = ops.get( i );

				opsArray.set( i, ci.getSearchJson() );
			}

			obj.put( "ops", opsArray );

			return obj;
		}
		else
		{
			JSONObject obj = new JSONObject();

			obj.put( "field", new JSONString( field.getField() ) );
			obj.put( "op", new JSONString( op ) );
			obj.put( "value", new JSONString( value ) );

			return obj;
		}
	}

	private ICriteriaFieldMng findField( String field )
	{
		for( ICriteriaFieldMng mng : criteriaMngs )
			if( mng.getField().compareTo( field ) == 0 )
				return mng;

		GWT.log( "ERROR !!! No field found for " + field );

		return null;
	}

	public void setSearchString( GenericJSO jso )
	{
		this.ops.clear();

		String op = jso.getString( "op" );
		if( op.compareTo( "and" ) == 0 || op.compareTo( "or" ) == 0 )
		{
			this.op = op;
			JsArray<GenericJSO> ops = jso.getArray( "ops" );
			for( int i = 0; i < ops.length(); i++ )
			{
				CriteriaInternal sub = new CriteriaInternal( criteriaMngs );
				sub.setSearchString( ops.get( i ) );
				this.ops.add( sub );
			}
		}
		else
		{
			this.field = findField( jso.getString( "field" ) );
			if( this.field != null )
			{
				this.op = op;
				this.value = jso.getString( "value" );
			}
		}

		UpdateInterface();
	}
}