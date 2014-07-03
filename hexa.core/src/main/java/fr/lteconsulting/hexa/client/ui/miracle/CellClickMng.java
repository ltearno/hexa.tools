package fr.lteconsulting.hexa.client.ui.miracle;

import com.google.gwt.dom.client.Element;

public interface CellClickMng<T>
{
	// return true to say the event is handled and should not be processed
	// further
	boolean onTableClick( T data, Element source, Printer printer );
}
