package fr.lteconsulting.hexa.client.ui.tree;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;

public abstract class NodeWidgetBase<NODE_DATA, LEAF_DATA> extends Composite implements INodeWidget<NODE_DATA, LEAF_DATA>
{
	NODE_DATA data = null;

	XNodeWidget<NODE_DATA, LEAF_DATA> callback;

	INodeWidget<NODE_DATA, LEAF_DATA> parent;

	ArrayList<TreeWidget<NODE_DATA, LEAF_DATA>> children = new ArrayList<TreeWidget<NODE_DATA, LEAF_DATA>>();

	abstract void addChildWidget( IsWidget isWidget );

	abstract void removeChildWidget( IsWidget isWidget );

	abstract void replaceChildWidget( IsWidget oldIsWidget, IsWidget newIsWidget );

	public NodeWidgetBase( XNodeWidget<NODE_DATA, LEAF_DATA> callback )
	{
		this.callback = callback;
	}

	public ArrayList<TreeWidget<NODE_DATA, LEAF_DATA>> getChildren()
	{
		return children;
	}

	public void beginNewChildProcess( UIObject uiObject )
	{
		callback.onWantAdd( this, uiObject );
	}

	public void addChild( TreeWidget<NODE_DATA, LEAF_DATA> treeWidget )
	{
		children.add( treeWidget );

		treeWidget.setParent( this );

		addChildWidget( treeWidget );
	}

	public void removeChild( TreeWidget<NODE_DATA, LEAF_DATA> child )
	{
		children.remove( child );

		child.setParent( null );

		if( children.size() == 0 && parent != null )
		{
			parent.removeChild( this );
			removeChildWidget( child );
			return;
		}

		// we have only one children, so we are of no use
		if( children.size() == 1 && parent != null )
		{
			parent.replaceChild( this, children.get( 0 ) );
			removeChildWidget( child );
			return;
		}

		removeChildWidget( child );
	}

	public void replaceChild( TreeWidget<NODE_DATA, LEAF_DATA> child, TreeWidget<NODE_DATA, LEAF_DATA> newChild )
	{
		int curIdx = children.indexOf( child );
		children.set( curIdx, newChild );

		child.setParent( null );
		newChild.setParent( this );

		replaceChildWidget( child, newChild );
	}

	public void setParent( INodeWidget<NODE_DATA, LEAF_DATA> parent )
	{
		this.parent = parent;
	}

	public ILeafWidget<NODE_DATA, LEAF_DATA> isLeaf()
	{
		return null;
	}

	public INodeWidget<NODE_DATA, LEAF_DATA> isNode()
	{
		return this;
	}

	@Override
	public void childWantsAdd( UIObject uiObject )
	{
		callback.onWantAdd( this, uiObject );
	}

	public void setData( NODE_DATA data )
	{
		this.data = data;
	}

	public NODE_DATA getData()
	{
		return data;
	}
}
