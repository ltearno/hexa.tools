package com.hexa.client.tableobserver;

import com.google.gwt.core.client.GWT;
import com.hexa.client.interfaces.IHasIntegerId;

public class LogDataPlug<T extends IHasIntegerId> implements XTableListen<T>
{
	String plugName;
	XTableListen<T> realPlug;

	public LogDataPlug( String plugName, XTableListen<T> realPlug )
	{
		this.plugName = "LogDataPlug " + plugName + ": ";
		this.realPlug = realPlug;
	}

	@Override
	public void deleted( int recordId, T oldRecord )
	{
		GWT.log( plugName + "deleted record " + recordId );
		realPlug.deleted( recordId, oldRecord );
	}

	@Override
	public void updated( int recordId, T record )
	{
		GWT.log( plugName + "updated record " + recordId );
		realPlug.updated( recordId, record );
	}

	@Override
	public void updatedField( int recordId, String fieldName, T record )
	{
		GWT.log( plugName + "updated field record " + recordId + "/" + fieldName );
		realPlug.updatedField( recordId, fieldName, record );
	}

	@Override
	public void wholeTable( Iterable<T> records )
	{
		int c = 0;
		for( @SuppressWarnings( "unused" )
		T i : records )
			c++;
		GWT.log( plugName + "whole table " + c + " elements" );
		realPlug.wholeTable( records );
	}

	@Override
	public void clearAll()
	{
		GWT.log( plugName + "clear all" );
		realPlug.clearAll();
	}

}
