package fr.lteconsulting.hexa.client.calendar;

public class TreePeriod extends Tree
{
	String from;
	String to;

	public TreePeriod( String from, String to )
	{
		super( Type.PERIOD );

		this.from = from;
		this.to = to;
	}

	public String getFrom()
	{
		return from;
	}

	public String getTo()
	{
		return to;
	}

	@Override
	public boolean HasDaySpec()
	{
		return false;
	}

	@Override
	public CalendarPeriod processFlat()
	{
		CalendarPeriod p = new CalendarPeriod();
		p.Init( from, to );
		return p;
	}

	@Override
	public String getBeautifulInternal()
	{
		return CalendarMessages.INSTANCE.dateRange( from, to );
	}
}
