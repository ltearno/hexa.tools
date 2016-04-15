package fr.lteconsulting.hexa.client.ui.tree;

import com.google.gwt.user.client.ui.IsWidget;

public interface TreeWidget<NODE_DATA, LEAF_DATA> extends IsWidget
{
	void setParent( INodeWidget<NODE_DATA, LEAF_DATA> parent );

	ILeafWidget<NODE_DATA, LEAF_DATA> isLeaf();

	INodeWidget<NODE_DATA, LEAF_DATA> isNode();
}