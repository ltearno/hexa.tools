package fr.lteconsulting.hexa.client.ui.tree;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.UIObject;

public interface INodeWidget<NODE_DATA, LEAF_DATA> extends TreeWidget<NODE_DATA, LEAF_DATA>
{
	public interface XNodeWidget<NODE_DATA, LEAF_DATA>
	{
		void onWantAdd( INodeWidget<NODE_DATA, LEAF_DATA> node, UIObject uiObject );

		void onWantDelete( INodeWidget<NODE_DATA, LEAF_DATA> node, TreeWidget<NODE_DATA, LEAF_DATA> child, UIObject uiObject );
	}

	void addChild( TreeWidget<NODE_DATA, LEAF_DATA> child );

	void removeChild( TreeWidget<NODE_DATA, LEAF_DATA> child );

	void replaceChild( TreeWidget<NODE_DATA, LEAF_DATA> oldChild, TreeWidget<NODE_DATA, LEAF_DATA> newChild );

	INodeWidget<NODE_DATA, LEAF_DATA> createEmptyNode();

	void childWantsAdd( UIObject uiObject );

	ArrayList<TreeWidget<NODE_DATA, LEAF_DATA>> getChildren();

	NODE_DATA getData();

	void setData( NODE_DATA data );
}
