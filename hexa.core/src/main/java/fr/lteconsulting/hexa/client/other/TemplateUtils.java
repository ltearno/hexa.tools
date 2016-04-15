package fr.lteconsulting.hexa.client.other;

import java.util.ArrayList;

import com.google.gwt.dom.client.Element;

public class TemplateUtils
{
	// asserts that a path's root is the waited element
	public static ArrayList<Element> verifyPath( Element root, ArrayList<Element> path )
	{
		if( root == path.get( 0 ) )
			return path;
		return null;
	}
	
	/**
	 * If the parent Element is an ancestor of the maybeDescendant element,
	 * this method returns the path from which the maybeDescendant can be reached
	 * when starting from the parent.<br/>
	 * If the maybeDescendant is not a descendant, the method returns null
	 *  
	 * @param parent The tested parent Element
	 * @param maybeDescendant The tested descendant
	 * @return
	 */
	public static ArrayList<Element> isDescendant( Element parent, Element maybeDescendant )
	{
		ArrayList<Element> res = new ArrayList<>();
		
		Element cur = maybeDescendant;
		
		while( cur != null )
		{
			res.add( 0, cur );
			
			if( cur == parent )
				return res;
			
			cur = cur.getParentElement();
		}
		
		return null;
	}
}
