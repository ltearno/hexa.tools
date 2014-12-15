package fr.lteconsulting.hexa.client.datatable;

/**
 * Visiting interface for rows hierarchy
 * 
 * @param <T>
 *            The type of instances being visited
 */
public interface Visitor<T>
{
	void beginVisit( T node );
	
	void beginVisitChild( T node, T child );

	void endVisitChild( T node, T child, Object childResult );

	Object endVisit( T node );
}