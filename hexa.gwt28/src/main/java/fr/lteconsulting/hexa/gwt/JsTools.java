package fr.lteconsulting.hexa.gwt;

class JsTools
{
	static void setImpl( Object o, Object name, Object value )
	{
		if( value instanceof Integer )
			setImplInternalInt( o, name, (int) ((Integer) value) );
		else
			setImplInternal( o, name, value );
	}

	static native void setImplInternal( Object o, Object name, Object value )
	/*-{
		o[name] = value;
	}-*/;

	static native void setImplInternalInt( Object o, Object name, int value )
	/*-{
		o[name] = value;
	}-*/;

	static native <T> T getImpl( Object o, Object name )
	/*-{
		return o[name] || null;
	}-*/;
}
