package fr.lteconsulting.hexa.client.calendar;

public class TreeDay extends Tree
{
	int day;

	public TreeDay( int day )
	{
		super( Type.DAY );

		this.day = day;
	}

	public int getVal()
	{
		return day;
	}

	@Override
	public boolean HasDaySpec()
	{
		return true;
	}

	@Override
	public CalendarPeriod processFlat()
	{
		CalendarPeriod p = new CalendarPeriod();
		p.InitWeekDays( day );
		return p;
	}

	@Override
	public String getBeautifulInternal()
	{
		return CalendarMessages.INSTANCE.weekDay( day );
	}
}
