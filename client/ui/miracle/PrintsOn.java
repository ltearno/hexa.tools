package com.hexa.client.ui.miracle;

public interface PrintsOn<T>
{
	// return false to say printer can be reused
	// true means the printer is still in use by the called function
	boolean print( T data, Printer printer );
}
