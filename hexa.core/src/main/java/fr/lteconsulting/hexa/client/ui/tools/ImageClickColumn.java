package fr.lteconsulting.hexa.client.ui.tools;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Event;

import fr.lteconsulting.hexa.client.datatable.Cell;
import fr.lteconsulting.hexa.client.datatable.DataTable;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;

public abstract class ImageClickColumn<T> implements IColumn<T>
{
	protected abstract void onClick( Cell cell );
	protected abstract int getColumnIdx();
	
	String title;
	ImageResource image;
	DataTable table;
	
	public ImageClickColumn( String title, ImageResource image, DataTable table )
	{
		this.title = title;
		this.image = image;
		this.table = table;
		
		table.addCellClickHandler( handler );
	}
	
	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public void fillCell( Printer printer, T record )
	{
		printer.setHTML( "<img src='" + image.getSafeUri().asString() + "'/>" );
	}

	@Override
	public IEditor editCell( T record )
	{
		return null;
	}
	
	private final ClickHandler handler = new ClickHandler()
	{	
		@Override
		public void onClick( ClickEvent event )
		{
			Cell cell = table.getCellForEvent( event.getNativeEvent().<Event>cast() );
			if( cell == null )
				return;
			
			//check our column
			if( cell.getCellIndex() != getColumnIdx() )
				return;
			
			ImageClickColumn.this.onClick( cell );
		}
	};
}
