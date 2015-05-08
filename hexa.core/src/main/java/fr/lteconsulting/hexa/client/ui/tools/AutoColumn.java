package fr.lteconsulting.hexa.client.ui.tools;

import static fr.lteconsulting.hexa.classinfo.ClassInfo.Clazz;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.databinding.properties.Properties;


public class AutoColumn<T> extends SimpleColumn<T>
{
	private Clazz<T> dtoClazz;
	private String fieldName;
	private Class<?> propertyType;
	
	public AutoColumn( Class<T> dtoClass, String fieldName )
	{
		this( dtoClass, fieldName, fieldName );
	}
	
	public AutoColumn( Class<T> dtoClass, String fieldName, String title )
	{
		super(title);
		
		this.fieldName = fieldName;
		
		dtoClazz = Clazz( dtoClass );
		
		propertyType = Properties.getPropertyType( dtoClazz, fieldName );
	}

	@Override
	protected Object getRecordValue( T record )
	{
		return Properties.getValue( record, fieldName );
	}

	@Override
	protected void setRecordValue( T record, Object value )
	{
		Properties.setValue( record, fieldName, value );
	}
	
	@Override
	protected Class<?> getPropertyType()
	{
		return propertyType;
	}
}