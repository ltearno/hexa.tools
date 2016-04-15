package fr.lteconsulting.hexa.client.ui.tree;

public interface ILeafWidget<NODE_DATA, LEAF_DATA> extends TreeWidget<NODE_DATA, LEAF_DATA>
{
	LEAF_DATA getData();

	void setData( LEAF_DATA data );
}
