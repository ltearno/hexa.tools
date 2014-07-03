package fr.lteconsulting.hexa.client.tableobserver;

import com.google.gwt.core.client.GWT;
import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;

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
	public void updated( T record )
	{
		GWT.log( plugName + "updated record " + record.getId() );
		realPlug.updated( record );
	}

	@Override
	public void updatedField( String fieldName, T record )
	{
		GWT.log( plugName + "updated field record " + record.getId() + "/" + fieldName );
		realPlug.updatedField( fieldName, record );
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
