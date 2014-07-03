package fr.lteconsulting.hexa.client.ui.gwtbootstrap;

public class IntegerBox extends NumberBox
{
	public IntegerBox()
	{
		this( "", false );
	}

	public IntegerBox( String placeholder )
	{
		this( placeholder, false );
	}

	public IntegerBox( String placeholder, boolean allowNegative )
	{
		super( placeholder, false, allowNegative );
	}

	public Integer getValue()
	{
		String text = textBox.getValue();
		if( text == null )
			return null;

		try
		{
			return Integer.parseInt( text );
		}
		catch( Exception e )
		{
		}

		return null;
	}

	public void setValue( Integer value )
	{
		if( value == null )
			textBox.setText( "" );
		else
			textBox.setText( String.valueOf( value ) );
	}
}
