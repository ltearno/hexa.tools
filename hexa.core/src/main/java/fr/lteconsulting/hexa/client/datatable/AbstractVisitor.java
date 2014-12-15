package fr.lteconsulting.hexa.client.datatable;

public class AbstractVisitor<T> implements Visitor<T>
{
	@Override
	public void beginVisit( T node )
	{
	}

	@Override
	public void beginVisitChild( T node, T child )
	{
	}

	@Override
	public void endVisitChild( T node, T child, Object childResult )
	{
	}

	@Override
	public Object endVisit( T node )
	{
		return null;
	}
}
