package fr.lteconsulting.hexa.client.other;


import java.util.ArrayList;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.user.client.DOM;

import fr.lteconsulting.hexa.client.common.Pair;
import fr.lteconsulting.hexa.client.tools.Action4;

public class HtmlTableTemplate
{
	public static void build( int nbCols, int nbRows, StringBuilder sb, Action4<String, Integer, Integer, StringBuilder> callback )
	{
		sb.append( "<table><thead>" );
		sb.append( "<tr>" );
		for( int i = 0; i<nbCols; i++ )
		{
			sb.append( "<th>" );
			callback.exec( "th", i, 0, sb );
			sb.append( "</th>" );
		}
		sb.append( "</tr>" );
		
		sb.append( "</thead><tbody>" );
		
		for( int j=0; j<nbRows; j++ )
		{
			sb.append( "<tr>" );
			for( int i = 0; i<nbCols; i++ )
			{
				sb.append( "<td>" );
				callback.exec( "td", i, j, sb );
				sb.append( "</td>" );
			}
			
			sb.append( "</tr>" );
		}
		
		sb.append( "</tbody</table>" );
	}
	
	public static TableSectionElement getTBodyElement( Element root )
	{
		return root.getChild( 1 ).cast();
	}
	
	/**
	 * Consumes a path from the root element which is supposed to be path.get(0).
	 * The method then returns the path beginning from the TBody element of the table
	 * 
	 * @param path
	 * @return
	 */
	public static ArrayList<Element> getTBodyFromPath( ArrayList<Element> path )
	{
		path.remove( 0 );
		
		return path;
	}
	
	/**
	 * Get the TD element
	 * 
	 * @param root
	 * @param column
	 * @param row
	 * @return
	 */
	public static TableCellElement getCell( Element root, int column, int row )
	{
		TableSectionElement tbody = getTBodyElement( root );
		
		TableRowElement tr = tbody.getChild( row ).cast();
		TableCellElement td = tr.getChild( column ).cast();
		
		return td;
	}
	
	/**
	 * Returns the coordinate of the cell containing the element, given that root is the root of a HtmlTableTemplate
	 * @param root
	 * @param element
	 * @return
	 */
	public static Pair<Integer, Integer> getParentCellForElement( Element root, Element element )
	{
		Element tbody = getTBodyElement( root );
		
		// Is the element in our table ? and what's the path from the table to it ?
		ArrayList<Element> path = TemplateUtils.isDescendant( tbody, element );
		if( path == null )
			return null;
		
		// we know that path[0] is tbody and that path[1] is a tr template
		int row = DOM.getChildIndex( path.get( 0 ), path.get( 1 ) );
		int col = DOM.getChildIndex( path.get( 1 ), path.get( 2 ) );
		
		return new Pair<Integer, Integer>( col, row );
	}
}