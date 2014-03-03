package com.hexa.client.ui.treetable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.tools.ColumnsSet.IEditor;
import com.hexa.client.ui.treetable.TreeTable.Row;
import com.hexa.client.ui.widget.Validator;
import com.hexa.client.ui.widget.ValidatorCallback;

/**
 * @author Arnaud
 *
 */
public class TreeTableEditorManager implements TreeTableHandler, ValidatorCallback
{
	public interface TreeTableEditorManagerCallback
	{
		// when the cell must be updated by the client
		public void onTouchCellContent( Row row, int column );

		// when the editor is constructed, call callback.editorReady( ... )
		public IEditor editCell( Row row, int column );

		// implies a touch of course
		public void onCellEditorValidation( Widget editor, Row row, int column );
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
		m_table.setHandler( this );
	}

	@Override
	public void onTableHeaderClick( int column, ClickEvent event )
	{
	}

	@Override
	public void onTableCellClick( Row item, int column, ClickEvent event )
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
		useEditor( item, column, editor );
	}

	private void useEditor( Row item, int column, IEditor editor )
	{
		// forget any not relevant editor
		if( m_currentEditedItem != item || m_currentEditedColumn != column )
			return;

		m_currentEditor = editor.getWidget();
		if( m_currentEditor == null )
			return;

		// create the validator around
		final Validator<Widget> validator = new Validator<Widget>();
		validator.setEditor( m_currentEditor, editor.isShowCancel() );
		validator.setCallback( this );

		// display that in the table
		item.setWidget( column, validator );
	}

	@Override
	public void onValidatorAction( ValidatorCallback.Button button )
	{
		if( button == Button.Ok )
		{
			m_callback.onCellEditorValidation( m_currentEditor, m_currentEditedItem, m_currentEditedColumn );
		}

		_RemoveValidator( m_currentEditedItem, m_currentEditedColumn );
	}

	@Override
	public void onValidatorMoveRequest( int dx, int dy )
	{
		if( dx != 0 )
			onTableCellClick( m_currentEditedItem, m_currentEditedColumn + dx, null );
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
