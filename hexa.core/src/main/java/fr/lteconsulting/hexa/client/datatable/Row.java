package fr.lteconsulting.hexa.client.datatable;

import java.util.List;

import com.google.gwt.dom.client.TableRowElement;

/**
 * This interface models a table row
 * 
 * @author Arnaud
 */
public interface Row
{
	/**
	 * Get the cell for the specified column in the row
	 * 
	 * @param column
	 *            The column index of the cell to be retrieved
	 * 
	 * @return The cell implementation
	 */
	Cell getCell( int column );

	/**
	 * Gets the number of cells in this row
	 * 
	 * @return The number of cells
	 */
	int getCellCount();

	/**
	 * Adds a CSS class to the underlying tr element
	 * 
	 * @param className CSS class name to add
	 */
	void addClassName( String className );
	
	/**
	 * Removes a CSS class from the underlying tr element
	 * 
	 * @param className CSS class name to remove
	 */
	void removeClassName( String className );

	/**
	 * Visits the row tree, returning the previous one as in seeing
	 * the tree in a file explorer.
	 * <p>If called with the first row, it will go back to the last
	 */
	Row getPreviousRow();

	/**
	 * Visits the row tree, returning the next one as in seeing
	 * the tree in a file explorer
	 * <p>If called with the last row, it will go back to the first
	 */
	Row getNextRow();

	/**
	 * Removes the row from the table
	 */
	void remove();

	/**
	 * Creates a new {@link Row} and add it to the table
	 * 
	 * @return The created row
	 */
	Row addRow();
	
	/**
	 * Creates a new {@link Row} and add it at the specified position
	 * 
	 * @param position index of the inserted row
	 * @return The created row
	 */
	Row insertRowAt( int position );

	/**
	 * Returns the tr element underlying to the row
	 * 
	 * @return
	 */
	TableRowElement getTr();

	/**
	 * Returns the parent row or null.
	 * @return The parent row or null for the (invisible) root row
	 */
	Row getParentRow();
	
	/**
	 * Checks whether the children are displayed or not
	 * @return <code>true</code> if the children are <b>hidden</b>
	 */
	boolean isFolded();
	
	/**
	 * Hide or show row's children
	 * @param isFolded <code>true</code> to hide the row's children
	 */
	void setFolded( boolean isFolded );

	/**
	 * Toggles displaying of children
	 */
	void toggleChildDisplay();

	/**
	 * Gets the children rows, or null if none
	 * 
	 * @return
	 */
	List<Row> getChildrenRows();

	/**
	 * Accepts the given Row as the last of its children.<br/>
	 * It may imply that the row should be removed from its current parent
	 * if needed.
	 * 
	 * @param row
	 */
	void acceptAsLastChild( Row row );
	
	/**
	 * Accepts the given Row at the given position.<br/>
	 * It may imply that the row should be removed from its current parent
	 * if needed.
	 * 
	 * @param row
	 * @param position The insertion position for the row
	 */
	void acceptAsNthChild( Row row, int position );

	/**
	 * Returns the node level in the tree formed by the table rows.
	 * 
	 * @return Number of ancestors of this row
	 */
	int getLevel();

	/**
	 * Tells if the row has children
	 * 
	 * @return
	 */
	boolean hasChildren();

	/**
	 * Visit all rows from this one, going deeper first
	 * 
	 * @param visitor
	 */
	Object visitDepthFirstPre( Visitor<Row> visitor );
}