package com.hexa.client.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.hexa.client.tableobserver.XTableListen;
import com.hexa.client.comm.HexaFramework;
import com.hexa.client.tools.ColumnsSet;
import com.hexa.client.tools.ColumnsSet.IColumnMng;
import com.hexa.client.ui.TreeTableEditorManager.TreeTableEditorManagerCallback;
import com.hexa.client.ui.TreeTableElemMng.TreeTableElemMngCallback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public abstract class TreeTableCollectionMng<T>  implements AsyncCallback<ArrayList<T>>, TreeTableElemMngCallback<T>, TreeTableEditorManagerCallback, ClickHandler
{
	public abstract void reload();
	public abstract void onWantAdd();
	public abstract void onWantDelete( T record ); 
	public abstract int getElementIdentifier( T element );
	public abstract void initColumns( ColumnsSet<T> columns );
	
	@SuppressWarnings("unused")
	private String addButtonTitle;
	@SuppressWarnings("unused")
	private String deleteButtonTitle;
	
	private TreeTable table = new TreeTable( HexaFramework.images.treeMinus(), HexaFramework.images.treePlus() );
	private TreeTableElemMng<T> tableMng = new TreeTableElemMng<T>( this );
	
	private ColumnsSet<T> columns = new ColumnsSet<T>();
	private TreeTableEditorManager tableEd = new TreeTableEditorManager();
	HashMap<Object, T> records = new HashMap<Object, T>();
	
	ImageTextButton addButton;
	
	public TreeTableCollectionMng()
	{
		this( null, null );
	}
	
	public TreeTableCollectionMng( String addButtonTitle, final String deleteButtonTitle )
	{
		this.addButtonTitle = addButtonTitle;
		this.deleteButtonTitle = deleteButtonTitle;
		
		if( addButtonTitle != null )
		{
			addButton = new ImageTextButton( HexaFramework.images.add(), addButtonTitle );
			addButton.addClickHandler( this );
		}
		
		initColumns( columns );
		if( deleteButtonTitle != null )
		{
			columns.addColumn( new IColumnMng<T>() {
				public void fillCell(int ordinal, TreeTable table, Object item, final T record) {
					ImageButton im = new ImageButton( HexaFramework.images.delete(), deleteButtonTitle );
					im.addClickHandler( new ClickHandler() {
						public void onClick(ClickEvent event) {
							onWantDelete( record );
						}
					} );
					table.setWidget( item, ordinal, im );
				}
				public void getAsyncCellEditorWidget(int ordinal, Object item, T record, ITreeTableEditorManager callback) {
				}
				public String getTitle() {
					return "Delete";
				}
				public void onCellEditorValidation(int ordinal, Widget editor, TreeTable table, Object item, T record) {
				}
			});
		}
		columns.setHeaders( table );
		
		tableEd.setTable( table, this );
	}
	
	public Widget getTable() {
		return table;
	}
	
	public Widget getAddButton() {
		return addButton;
	}
	
	@Override
	public void onFailure(Throwable caught) {}
	
	public void addOrUpdateElemInCurrentVersion( T elem )
	{
		Object item = tableMng.addOrUpdateItemInCurrentVersion( elem, table, null );
		records.put( item, elem );
		columns.fillRow( table, item, elem );
	}
	
	public void deleteElemInCurrentVersion( T elem )
	{
		tableMng.deleteItemInCurrentVersion( elem, table );
	}

	@Override
	public void onSuccess( ArrayList<T> result )
	{
		records.clear();
		for( T c : result )
			addElem( c );
		tableMng.commitVersion( table );
	}
	
	private XTableListen<T> dataPlug = new XTableListen<T>() {
		public void deleted(int recordId, T oldRecord, Object cookie) {
			remElem( recordId );
		}
		public void updated(int recordId, T record, Object cookie) {
			addElem( record );
		}
		public void updatedField(int recordId, String fieldName, T record, Object cookie) {
			addElem( record );
		}
		public void wholeTable(Iterable<T> data, Object cookie) {
			records.clear();
			for( T c : data )
				addElem( c );
			tableMng.commitVersion( table );
		}
		public void clearAll(Object cookie){
			records.clear();
			tableMng.commitVersion( table );
		}
	};
	
	public XTableListen<T> getDataPlug()
	{
		return dataPlug;
	};
	
	private void addElem( T elem )
	{
		Object item = tableMng.getItem( elem, table, null );
		records.put( item, elem );
		columns.fillRow( table, item, elem );
	}
	
	private void remElem( int elemId )
	{
		Object item = tableMng.remove( elemId, table );
		records.remove( item );
	}
	
	@Override
	public void getAsyncCellEditorWidget(Object item, int column, ITreeTableEditorManager callback) {
		T record = records.get(item);
		columns.getAsyncCellEditorWidget( column, item, record, callback );
	}

	@Override
	public void onCellEditorValidation(Widget editor, TreeTable table, Object item, int column) {
		T record = records.get(item);
		columns.onCellEditorValidation( column, editor, table, item, record );
	}

	@Override
	public void onTouchCellContent(TreeTable table, Object item, int column) {
		T record = records.get(item);
		if( record == null ) {
			GWT.log( "NULL RECORD IN TreeTableCollectionMng for onTouchCellContent", null );
			table.setText( item, column, "EMPTY" );
			return;
		}
		columns.fillCell( column, table, item, record );
	}

	@Override
	public void onClick(ClickEvent event)
	{
		onWantAdd();
	}
}
