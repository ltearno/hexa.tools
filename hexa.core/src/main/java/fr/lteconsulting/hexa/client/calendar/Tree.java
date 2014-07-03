package fr.lteconsulting.hexa.client.calendar;

public abstract class Tree
{
	public enum Type
	{
		ALWAYS,
		NEVER,
		DAY,
		PERIOD,
		NOT,
		AND,
		OR;
	}

	protected final Type type;

	// fields that are stored once processed
	private CalendarPeriod flat;
	private PeriodBoundaries boundaries;

	public static TreeAlwaysNever CreateAlways()
	{
		return new TreeAlwaysNever( true );
	}

	public static TreeAlwaysNever CreateNever()
	{
		return new TreeAlwaysNever( false );
	}

	public static TreeDay CreateDay( int day )
	{
		return new TreeDay( day );
	}

	public static TreePeriod CreatePeriod( String from, String to )
	{
		return new TreePeriod( from, to );
	}

	public static TreePeriod CreatePeriod( String fromTo )
	{
		return new TreePeriod( fromTo, fromTo );
	}

	public static TreeBinaryOperator CreateAnd( Tree leftOp, Tree rightOp )
	{
		return new TreeBinaryOperator( Type.AND, leftOp, rightOp );
	}

	public static TreeBinaryOperator CreateOr( Tree leftOp, Tree rightOp )
	{
		return new TreeBinaryOperator( Type.OR, leftOp, rightOp );
	}

	public static TreeUnaryOperator CreateNot( Tree op )
	{
		return new TreeUnaryOperator( op );
	}

	protected Tree( Type type )
	{
		this.type = type;
	}

	public TreeAlwaysNever asAlwaysNever()
	{
		return (TreeAlwaysNever) this;
	}

	public TreeBinaryOperator asBinaryOperator()
	{
		return (TreeBinaryOperator) this;
	}

	public TreeDay asDay()
	{
		return (TreeDay) this;
	}

	public TreePeriod asPeriod()
	{
		return (TreePeriod) this;
	}

	public TreeUnaryOperator asUnaryOperator()
	{
		return (TreeUnaryOperator) this;
	}

	public Type getType()
	{
		return type;
	}

	public CalendarPeriod getFlat()
	{
		if( flat == null )
			flat = processFlat();

		return flat;
	}

	public PeriodBoundaries getBoundaries()
	{
		if( boundaries == null )
		{
			boundaries = new PeriodBoundaries();
			getFlat().GetBoundaries( boundaries );
		}

		return boundaries;
	}

	// returns the number of days contained in this period
	public int getNbDays()
	{
		return getFlat().GetNbDays();
	}

	// returns the number of nights contained in this period
	public int getNbNights()
	{
		return getFlat().GetNbNights();
	}

	public final String getBeautiful()
	{
		// should one day find something better !
		if( HasDaySpec() )
			return getBeautifulInternal();

		String beauty = getFlat().GetBeautiful();
		if( beauty == null )
			return "ERROR";
		return beauty;
	}

	public abstract CalendarPeriod processFlat();

	public abstract boolean HasDaySpec();

	protected abstract String getBeautifulInternal();
}