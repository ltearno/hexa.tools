package com.hexa.client.tools;

import com.hexa.client.tools.ColumnsSet.IColumnMng;
import com.hexa.client.ui.ITreeTableEditorManager;
import com.hexa.client.ui.TreeTable;
import com.google.gwt.user.client.ui.Widget;

public class EmptyColumn<T> implements IColumnMng<T>{
	public void fillCell(int ordinal, TreeTable table, Object item, T record) {
	}
	
	public void getAsyncCellEditorWidget(int ordinal, Object item, T record, ITreeTableEditorManager callback) {
	}

	public String getTitle() {
		return "";
	}

	public void onCellEditorValidation(int ordinal, Widget editor, TreeTable table, Object item, T record) {
	}

}
