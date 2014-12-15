package fr.lteconsulting.hexa.client.ui.tools;


public abstract class ReadOnlyColumn<T> extends SimpleColumn<T>
{
	public ReadOnlyColumn( String title )
	{
		super( title );
	}

	@Override
	protected final void setRecordValue( T record, Object value )
	{
	}

	@Override
	protected final Class<?> getPropertyType()
	{
		// return null so that no editor is allowed
		return null;
	}
}
