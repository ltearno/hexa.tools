package fr.lteconsulting.hexa.client.ui.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

import fr.lteconsulting.hexa.client.comm.GenericJSO;
import fr.lteconsulting.hexa.client.comm.HexaFramework;
import fr.lteconsulting.hexa.client.tools.HierarchySet.IHierarchyLevel;
import fr.lteconsulting.hexa.client.ui.Styles;
import fr.lteconsulting.hexa.client.ui.dialog.MyPopupPanel;
import fr.lteconsulting.hexa.client.ui.treetable.Row;
import fr.lteconsulting.hexa.client.ui.treetable.TreeTable;
import fr.lteconsulting.hexa.client.ui.treetable.event.TableCellClickEvent.TableCellClickHandler;

public class HierarchyHeaderControl<T> extends Composite implements ClickHandler
{
	public interface HierarchyHeaderControlCallback<T>
	{
		public void onHierarchyHeaderControlChange( List<IHierarchyLevel<T>> levels );
	}

	HierarchyHeaderControlCallback<T> callback = null;

	HorizontalPanel currentListPanel = new HorizontalPanel();
	ImageButton addLevel = null;

	ArrayList<IHierarchyLevel<T>> levels = new ArrayList<IHierarchyLevel<T>>();
	ArrayList<IHierarchyLevel<T>> currentLevels = new ArrayList<IHierarchyLevel<T>>();

	public HierarchyHeaderControl()
	{
		HorizontalPanel panel = new HorizontalPanel();

		panel.add( currentListPanel );
		addLevel = new ImageButton( HexaFramework.images.addPlus(), "Add a level to this hierachy" );
		addLevel.addClickHandler( this );
		panel.add( addLevel );

		initWidget( panel );
		addStyleName( Styles.CSS.commentPanel() );
	}

	public void setCallback( HierarchyHeaderControlCallback<T> callback )
	{
		this.callback = callback;
	}

	public void add( IHierarchyLevel<T> level )
	{
		levels.add( level );

		_RefreshAll();
	}

	public void clearAll()
	{
		levels.clear();
		currentLevels.clear();

		addLevel.setVisible( true );
		currentListPanel.clear();

		_RefreshAll();
	}

	public ArrayList<IHierarchyLevel<T>> getLevels()
	{
		return currentLevels;
	}

	public JSONValue getLevelsJson()
	{
		JSONArray arr = new JSONArray();

		int i = 0;
		for( IHierarchyLevel<T> level : currentLevels )
		{
			arr.set( i, new JSONString( level.getName() ) );
			i++;
		}

		return arr;
	}

	private IHierarchyLevel<T> findLevelIndex( String name )
	{
		for( int i = 0; i < levels.size(); i++ )
			if( levels.get( i ).getName().equals( name ) )
				return levels.get( i );
		return null;
	}

	public void setLevelsString( GenericJSO jso )
	{
		currentLevels.clear();

		_RefreshAll();

		JsArrayString levels = jso.getAsArrayOfString();
		for( int i = 0; i < levels.length(); i++ )
		{
			String levelName = levels.get( i );
			IHierarchyLevel<T> level = findLevelIndex( levelName );
			addLevel( level );
		}
	}

	private void _RefreshAll()
	{
		currentListPanel.clear();
	}

	@Override
	public void onClick( ClickEvent event )
	{
		final MyPopupPanel popup = new MyPopupPanel( true, true );
		popup.setStylePrimaryName( "ArnoPopupPanel" );
		popup.showRelativeTo( addLevel );

		TreeTable table = new TreeTable();
		table.setHeader( 0, "Choose a level" );
		for( int i = 0; i < levels.size(); i++ )
		{
			if( currentLevels.contains( levels.get( i ) ) )
				continue;

			Row item = table.addRow( null );
			item.setText( 0, levels.get( i ).getName() );
			item.setDataObject( levels.get( i ) );
		}

		popup.setWidget( table );

		table.addTableCellClickHandler( new TableCellClickHandler()
		{
			@Override
			public void onTableCellClick( Row item, int column, ClickEvent event )
			{
				@SuppressWarnings( "unchecked" )
				IHierarchyLevel<T> level = (IHierarchyLevel<T>) (item.getDataObject());
				addLevel( level );
				_signalChange();

				popup.hide();
			}
		} );
	}

	private void addLevel( final IHierarchyLevel<T> level )
	{
		if( level == null )
			return;

		final ImageTextButton im = new ImageTextButton( HexaFramework.images.delete(), level.getName() );
		im.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				_onLevelClick( level, im );
			}
		} );
		currentListPanel.add( im );

		currentLevels.add( level );

		if( currentLevels.size() == levels.size() )
			addLevel.setVisible( false );

		_signalChange();
	}

	private void _onLevelClick( IHierarchyLevel<T> level, ImageTextButton button )
	{
		addLevel.setVisible( true );

		currentListPanel.remove( button );

		currentLevels.remove( level );

		_signalChange();
	}

	private void _signalChange()
	{
		if( callback == null )
			return;

		ArrayList<IHierarchyLevel<T>> cur = getLevels();
		callback.onHierarchyHeaderControlChange( cur );
	}
}
