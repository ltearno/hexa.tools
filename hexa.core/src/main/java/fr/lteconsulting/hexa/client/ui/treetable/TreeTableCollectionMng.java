package fr.lteconsulting.hexa.client.ui.treetable;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.comm.HexaFramework;
import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;
import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;
import fr.lteconsulting.hexa.client.tableobserver.XTableListen;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.tools.ColumnsSet;
import fr.lteconsulting.hexa.client.ui.tools.IColumn;
import fr.lteconsulting.hexa.client.ui.tools.IEditor;
import fr.lteconsulting.hexa.client.ui.treetable.TreeTableEditorManager.TreeTableEditorManagerCallback;
import fr.lteconsulting.hexa.client.ui.treetable.TreeTableElemMng.TreeTableElemMngCallback;
import fr.lteconsulting.hexa.client.ui.widget.ImageButton;
import fr.lteconsulting.hexa.client.ui.widget.ImageTextButton;

public abstract class TreeTableCollectionMng<T extends IHasIntegerId> implements IAsyncCallback<List<T>>, TreeTableElemMngCallback<T>, TreeTableEditorManagerCallback, ClickHandler
{
	//@Deprecated
	public abstract void reload();

	public abstract void onWantAdd();

	public abstract void onWantDelete( T record );

	public abstract void initColumns( ColumnsSet<T> columns );

	@SuppressWarnings( "unused" )
	private String addButtonTitle;
	@SuppressWarnings( "unused" )
	private String deleteButtonTitle;

	private final TreeTable table = new TreeTable( HexaFramework.images != null ? HexaFramework.images.treeMinus() : null, HexaFramework.images != null ? HexaFramework.images.treePlus() : null );
	private final TreeTableElemMng<T> tableMng = new TreeTableElemMng<T>( table, this );

	private final ColumnsSet<T> columns = new ColumnsSet<T>();
	private final TreeTableEditorManager tableEd = new TreeTableEditorManager();
	HashMap<Row, T> records = new HashMap<Row, T>();

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
			addButton = new ImageTextButton( HexaFramework.images != null ? HexaFramework.images.add() : null, addButtonTitle );
			addButton.addClickHandler( this );
		}

		initColumns( columns );
		if( deleteButtonTitle != null )
		{
			columns.addColumn( new IColumn<T>()
			{
				@Override
				public void fillCell( Printer printer, final T record )
				{
					ImageButton im = new ImageButton( HexaFramework.images.delete(), deleteButtonTitle );
					im.addClickHandler( new ClickHandler()
					{
						@Override
						public void onClick( ClickEvent event )
						{
							onWantDelete( record );
						}
					} );

					printer.setWidget( im );
				}

				@Override
				public String getTitle()
				{
					return "Delete";
				}

				@Override
				public IEditor editCell( T record )
				{
					return null;
				}
			} );
		}
		columns.setHeaders( table );

		tableEd.setTable( table, this );
	}

	public TreeTable getTable()
	{
		return table;
	}

	public Widget getAddButton()
	{
		return addButton;
	}

	public void addOrUpdateElemInCurrentVersion( T elem )
	{
		Row item = tableMng.addOrUpdateItemInCurrentVersion( elem, null );
		records.put( item, elem );
		columns.fillRow( item, elem );
	}

	public void deleteElemInCurrentVersion( T elem )
	{
		tableMng.deleteItemInCurrentVersion( elem, table );
	}
	
	@Override
	public final int getElementIdentifier( T record )
	{
		return record.getId();
	}

	@Override
	public void onSuccess( List<T> result )
	{
		records.clear();
		for( T c : result )
			addElem( c );
		tableMng.commitVersion();
	}

	private final XTableListen<T> dataPlug = new XTableListen<T>()
	{
		@Override
		public void deleted( int recordId, T oldRecord )
		{
			remElem( recordId );
		}

		@Override
		public void updated( T record )
		{
			addElem( record );
		}

		@Override
		public void updatedField( String fieldName, T record )
		{
			addElem( record );
		}

		@Override
		public void wholeTable( Iterable<T> data )
		{
			records.clear();
			for( T c : data )
				addElem( c );
			tableMng.commitVersion();
		}

		@Override
		public void clearAll()
		{
			records.clear();
			tableMng.commitVersion();
		}
	};

	public XTableListen<T> getDataPlug()
	{
		return dataPlug;
	};

	private void addElem( T elem )
	{
		Row item = tableMng.getItem( elem, null );
		records.put( item, elem );
		columns.fillRow( item, elem );
	}

	private void remElem( int elemId )
	{
		Row item = tableMng.remove( elemId );
		records.remove( item );
	}

	@Override
	public IEditor editCell( Row row, int column )
	{
		T record = records.get( row );
		return columns.editCell( column, record );
	}

	@Override
	public void onTouchCellContent( Row row, int column )
	{
		T record = records.get( row );
		if( record == null )
		{
			GWT.log( "NULL RECORD IN TreeTableCollectionMng for onTouchCellContent", null );
			row.setText( column, "EMPTY" );
			return;
		}

		columns.fillCell( column, row, record );
	}

	@Override
	public void onClick( ClickEvent event )
	{
		onWantAdd();
	}
}
