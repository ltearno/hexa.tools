package fr.lteconsulting.hexa.client.calendar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;

public interface CalendarMessages extends Messages
{
	public static CalendarMessages INSTANCE = GWT.create( CalendarMessages.class );

	@DefaultMessage( "Always" )
	String always();

	@DefaultMessage( "Never" )
	String never();

	@DefaultMessage( "( {0} and {1} )" )
	String and( String left, String right );

	@DefaultMessage( "( {0} or {1} )" )
	String or( String left, String right );

	@DefaultMessage( "not {0}" )
	String not( String inside );

	@DefaultMessage( "from {0} to {1}" )
	String dateRange( String from, String to );

	@DefaultMessage( "until {0}" )
	String until( String date );

	@DefaultMessage( "from {0}" )
	String from( String date );

	@DefaultMessage( "day {0}" )
	String weekDay( int day );
}
