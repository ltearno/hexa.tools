package fr.lteconsulting.hexa.client.calendar;

public class TreeBinaryOperator extends Tree
{
	Tree leftOp;
	Tree rightOp;

	public TreeBinaryOperator( Tree.Type type, Tree leftOp, Tree rightOp )
	{
		super( type );

		this.leftOp = leftOp;
		this.rightOp = rightOp;
	}

	public Tree getOpLeft()
	{
		return leftOp;
	}

	public Tree getOpRight()
	{
		return rightOp;
	}

	@Override
	public boolean HasDaySpec()
	{
		return leftOp.HasDaySpec() || rightOp.HasDaySpec();
	}

	@Override
	public CalendarPeriod processFlat()
	{
		CalendarPeriod leftFlat = leftOp.getFlat();
		CalendarPeriod rightFlat = rightOp.getFlat();

		switch( type )
		{
			case AND:
				leftFlat.Intersect( rightFlat );
				return leftFlat;

			case OR:
				leftFlat.Add( rightFlat );
				return leftFlat;

			default:
				throw new IllegalStateException( "Should not be here !" );
		}
	}

	@Override
	public String getBeautifulInternal()
	{
		switch( type )
		{
			case AND:
				return CalendarMessages.INSTANCE.and( leftOp.getBeautiful(), rightOp.getBeautiful() );

			case OR:
				return CalendarMessages.INSTANCE.or( leftOp.getBeautiful(), rightOp.getBeautiful() );

			default:
				throw new IllegalStateException( "Should not be here !" );
		}
	}
}
