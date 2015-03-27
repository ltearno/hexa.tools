package fr.lteconsulting.hexa.client.ui.treetable;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.miracle.Size;
import fr.lteconsulting.hexa.client.ui.tools.IEditor;
import fr.lteconsulting.hexa.client.ui.tools.IEditorHost;
import fr.lteconsulting.hexa.client.ui.treetable.event.TableCellDoubleClickEvent.TableCellDoubleClickHandler;

/**
 * @author Arnaud
 *
 */
public class TreeTableEditorManager
{
	public interface TreeTableEditorManagerCallback
	{
		// when the cell must be updated by the client
		public void onTouchCellContent( Row row, int column );

		// when the editor is constructed, call callback.editorReady( ... )
		public IEditor editCell( Row row, int column );
	}

	private TreeTable m_table = null;
	private TreeTableEditorManagerCallback m_callback = null;

	private Row m_currentEditedItem = null;
	private int m_currentEditedColumn = -1;
	private Widget m_currentEditor = null;

	public void setTable( TreeTable table, TreeTableEditorManagerCallback callback )
	{
		m_table = table;
		m_callback = callback;
		m_table.addTableCellDoubleClickHandler( tableCellDoubleClickHandler );
	}

	public void editCell( Row item, int column )
	{
		// forget edition if already opened at the same place
		if( m_currentEditor != null && m_currentEditedItem == item && m_currentEditedColumn == column )
			return;

		// remove any previous edition state
		_RemoveValidator( m_currentEditedItem, m_currentEditedColumn );

		if( m_callback == null )
			return;

		// now we really register as editing
		m_currentEditedItem = item;
		m_currentEditedColumn = column;

		// get any editor for that cell or forget about it
		IEditor editor = m_callback.editCell( item, column );
		if( editor != null )
			useEditor( item, column, editor );
	}

	TableCellDoubleClickHandler tableCellDoubleClickHandler = new TableCellDoubleClickHandler()
	{
		@Override
		public void onTableCellDoubleClick( Row item, int column, DoubleClickEvent clickEvent )
		{
			editCell( item, column );
		}
	};

	private void useEditor( final Row item, final int column, IEditor editor )
	{
		// forget any not relevant editor
		if( m_currentEditedItem != item || m_currentEditedColumn != column )
			return;

		// store the pixel size of the TD, editor might be gentleful to ask
		Element td = item.getCell( column ).getTdElement();
		int width = td.getOffsetWidth() - 2;
		int height = td.getOffsetHeight() - 2;
		final Size preferredEditorSize = new Size( width, height );

		editor.setHost( new IEditorHost()
		{
			@Override
			public Size getPreferredSize()
			{
				return preferredEditorSize;
			}

			@Override
			public void finishedEdition()
			{
				_RemoveValidator( item, column );
			}
		} );

		m_currentEditor = editor.getWidget();
		if( m_currentEditor == null )
			return;

		// display that in the table
		item.setWidget( column, m_currentEditor );
	}

	// just replace the validator widget by a text in the table
	private void _RemoveValidator( Row item, int column )
	{
		// already clean ?
		if( m_currentEditedItem == null && m_currentEditedColumn < 0 )
			return;

		// touch the table
		if( m_callback != null )
			m_callback.onTouchCellContent( item, column );

		if( m_currentEditor != null )
			m_currentEditor.removeFromParent();

		m_currentEditor = null;
		m_currentEditedItem = null;
		m_currentEditedColumn = -1;
	}
}
