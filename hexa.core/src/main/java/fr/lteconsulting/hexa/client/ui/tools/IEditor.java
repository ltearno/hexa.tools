package fr.lteconsulting.hexa.client.ui.tools;

import com.google.gwt.user.client.ui.Widget;

// an editor has a widget and
// can instruct container to show a cancel button around
public interface IEditor
{
	void setHost( IEditorHost editorHost );

	Widget getWidget();
}