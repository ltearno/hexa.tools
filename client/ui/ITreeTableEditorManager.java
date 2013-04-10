/**
 * 
 */
package com.hexa.client.ui;

import com.google.gwt.user.client.ui.Widget;

public interface ITreeTableEditorManager
{
	public void editorReady( Object item, int column, Widget editor, boolean fShowCancel );
}