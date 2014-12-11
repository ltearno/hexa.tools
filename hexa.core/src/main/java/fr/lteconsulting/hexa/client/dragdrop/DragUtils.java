package fr.lteconsulting.hexa.client.dragdrop;

import com.google.gwt.dom.client.DataTransfer;
import com.google.gwt.dom.client.Element;

public class DragUtils
{
	private static Object source;
	private static Object data;
	
	public static void setDraggable( Element element, boolean isDraggable )
	{
		if( isDraggable )
			element.setAttribute( "draggable", "true" );
		else
			element.removeAttribute( "draggable" );
	}
	
	public static native void setDropEffect( DataTransfer dataTransfer, String effect )
	/*-{
		dataTransfer.dropEffect = effect;
	}-*/;
	
	/**
	 * Sets the drag and drop data and its source
	 * 
	 * @param source
	 * @param data
	 */
	public static void setDragData( Object source, Object data )
	{
		DragUtils.source = source;
		DragUtils.data = data;
	}
	
	public static Object getDragData()
	{
		return data;
	}
	
	public static Object getDragDataSource()
	{
		return source;
	}
}
