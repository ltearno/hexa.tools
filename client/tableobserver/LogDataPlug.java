package com.hexa.client.tableobserver;

import com.google.gwt.core.client.GWT;

public class LogDataPlug<T> implements XTableListen<T>
{
	String plugName;
	XTableListen<T> realPlug;

	public LogDataPlug( String plugName, XTableListen<T> realPlug )
	{
		this.plugName = "LogDataPlug " + plugName + ": ";
		this.realPlug = realPlug;
	}

	public void deleted( int recordId, T oldRecord, Object cookie )
	{
		GWT.log( plugName + "deleted record " + recordId );
		realPlug.deleted( recordId, oldRecord, cookie );
	}

	@Override
	public void updated( int recordId, T record, Object cookie )
	{
		GWT.log( plugName + "updated record " + recordId );
		realPlug.updated( recordId, record, cookie );
	}

	@Override
	public void updatedField( int recordId, String fieldName, T record, Object cookie )
	{
		GWT.log( plugName + "updated field record " + recordId + "/" + fieldName );
		realPlug.updatedField( recordId, fieldName, record, cookie );
	}

	@Override
	public void wholeTable( Iterable<T> records, Object cookie )
	{
		int c = 0;
		for( @SuppressWarnings( "unused" )
		T i : records )
			c++;
		GWT.log( plugName + "whole table " + c + " elements" );
		realPlug.wholeTable( records, cookie );
	}

	@Override
	public void clearAll( Object cookie )
	{
		GWT.log( plugName + "clear all" );
		realPlug.clearAll( cookie );
	}

}
