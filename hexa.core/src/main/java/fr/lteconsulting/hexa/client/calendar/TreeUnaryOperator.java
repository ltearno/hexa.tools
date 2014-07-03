package fr.lteconsulting.hexa.client.calendar;

public class TreeUnaryOperator extends Tree
{
	Tree op;

	public TreeUnaryOperator( Tree op )
	{
		super( Type.NOT );

		this.op = op;
	}

	public Tree getOp()
	{
		return op;
	}

	@Override
	public boolean HasDaySpec()
	{
		return op.HasDaySpec();
	}

	@Override
	public CalendarPeriod processFlat()
	{
		CalendarPeriod p = op.getFlat();
		p.Not();

		return p;
	}

	@Override
	public String getBeautifulInternal()
	{
		if( op.getType() == Type.ALWAYS )
			return CalendarMessages.INSTANCE.never();

		return CalendarMessages.INSTANCE.not( op.getBeautiful() );
	}
}
