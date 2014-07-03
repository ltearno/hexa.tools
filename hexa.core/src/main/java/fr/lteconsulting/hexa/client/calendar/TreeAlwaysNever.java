package fr.lteconsulting.hexa.client.calendar;

public class TreeAlwaysNever extends Tree
{
	public TreeAlwaysNever( boolean fAlways )
	{
		super( fAlways ? Tree.Type.ALWAYS : Tree.Type.NEVER );
	}

	@Override
	public boolean HasDaySpec()
	{
		return false;
	}

	@Override
	public CalendarPeriod processFlat()
	{
		switch( type )
		{
			case ALWAYS:
				return new CalendarPeriod( CalendarFunctions.TIME_BEGIN, CalendarFunctions.TIME_END );

			case NEVER:
			{
				CalendarPeriod p = new CalendarPeriod();
				p.Init( null, null );
				return p;
			}

			default:
				throw new IllegalStateException( "Should not be here !" );
		}
	}

	@Override
	public String getBeautifulInternal()
	{
		switch( type )
		{
			case ALWAYS:
				return CalendarMessages.INSTANCE.always();

			case NEVER:
				return CalendarMessages.INSTANCE.never();

			default:
				throw new IllegalStateException( "Should not be here !" );
		}
	}
}
