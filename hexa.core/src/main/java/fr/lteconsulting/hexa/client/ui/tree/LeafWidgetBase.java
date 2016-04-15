package fr.lteconsulting.hexa.client.ui.tree;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class LeafWidgetBase<NODE_DATA, LEAF_DATA> extends Composite implements ILeafWidget<NODE_DATA, LEAF_DATA>
{
	INodeWidget<NODE_DATA, LEAF_DATA> parent;

	LEAF_DATA data = null;

	abstract public void setWidget( Widget widget );

	public void setData( LEAF_DATA data )
	{
		this.data = data;
	}

	public LEAF_DATA getData()
	{
		return data;
	}

	public void setParent( INodeWidget<NODE_DATA, LEAF_DATA> parent )
	{
		this.parent = parent;
	}

	public ILeafWidget<NODE_DATA, LEAF_DATA> isLeaf()
	{
		return this;
	}

	public INodeWidget<NODE_DATA, LEAF_DATA> isNode()
	{
		return null;
	}
}