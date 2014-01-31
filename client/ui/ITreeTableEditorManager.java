/**
 *
 */
package com.hexa.client.ui;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.treetable.TreeTable.Row;

public interface ITreeTableEditorManager
{
	public void editorReady( Row item, int column, Widget editor, boolean fShowCancel );
}