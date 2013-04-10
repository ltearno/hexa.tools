package com.hexa.client.ui;

import java.util.ArrayList;

import com.hexa.client.comm.GenericJSO;
import com.hexa.client.comm.HexaFramework;
import com.hexa.client.tools.HierarchySet.IHierarchyLevel;
import com.hexa.client.ui.TreeTableBase.Item;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;

public class HierarchyHeaderControl<T> extends Composite implements ClickHandler
{
	public interface HierarchyHeaderControlCallback<T>
	{
		public void onHierarchyHeaderControlChange( ArrayList<IHierarchyLevel<T>> levels );
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
		addStyleName( "CommentPanel" );
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
	
	/*public String getLevelsString()
	{
		String res = "[ ";
		int count = 1;
		for( IHierarchyLevel<T> level : currentLevels )
		{
			res += "\"" + level.getName() + "\"";
			if( count < currentLevels.size() )
				res += ", ";
			count ++;
		}
		res += "]";
		
		return res;
	}*/
	
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
		for( int i=0; i<levels.size(); i++ )
			if( levels.get(i).getName().equals( name ) )
				return levels.get(i);
		return null;
	}
	
	public void setLevelsString( GenericJSO jso )
	{
		currentLevels.clear();
		
		_RefreshAll();
		
		JsArrayString levels = jso.getAsArrayOfString();
		for( int i=0; i<levels.length(); i++ )
		{
			String levelName = levels.get(i);
			IHierarchyLevel<T> level = findLevelIndex( levelName );
			addLevel( level );
		}
	}
	
	private void _RefreshAll()
	{
		currentListPanel.clear();
	}

	@Override
	public void onClick(ClickEvent event)
	{
		final PopupPanel popup = new PopupPanel( true, true );
		popup.setStylePrimaryName( "ArnoPopupPanel" );
		popup.showRelativeTo( addLevel );
		
		TreeTable table = new TreeTable();
		table.setHeader( 0, "Choose a level" );
		for( int i=0; i<levels.size(); i++ )
		{
			if( currentLevels.contains(levels.get(i)) )
				continue;
			
			TreeTableBase.Item item = table.addItem( null );
			item.setText( 0, levels.get(i).getName() );
			item.setDataObject( levels.get(i) );
		}
		
		popup.setWidget( table );
		
		table.setHandler( new TreeTableHandler() {
			@Override
			public void onTableHeaderClick( int column, ClickEvent event )
			{
			}
			
			@Override
			public void onTableCellClick(Item item, int column, ClickEvent event)
			{
				@SuppressWarnings("unchecked")
				IHierarchyLevel<T> level = (IHierarchyLevel<T>)(((TreeTableBase.Item)item).getDataObject());
				addLevel( level );
				_signalChange();
				
				popup.hide();
			}
		});
	}
	
	private void addLevel( final IHierarchyLevel<T> level )
	{
		final ImageTextButton im = new ImageTextButton( HexaFramework.images.delete(), level.getName() );
		im.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent event)
			{
				_onLevelClick( level, im );
			}
		});
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
