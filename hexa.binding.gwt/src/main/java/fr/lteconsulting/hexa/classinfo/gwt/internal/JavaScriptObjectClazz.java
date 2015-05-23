package fr.lteconsulting.hexa.classinfo.gwt.internal;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.Field;
import fr.lteconsulting.hexa.classinfo.Method;

public class JavaScriptObjectClazz implements Clazz<JavaScriptObject>
{
	@Override
	public String getClassName()
	{
		return "JavaScriptObject";
	}

	@Override
	public Class<JavaScriptObject> getReflectedClass()
	{
		return JavaScriptObject.class;
	}

	@Override
	public List<Field> getFields()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static HashMap<String, Field> fields = new HashMap<String, Field>();

	private native void setJsoProperty( JavaScriptObject jso, String property, Object value )
	/*-{
		jso[property] = value;
	}-*/;
	
	private native <T> T getJsoProperty( JavaScriptObject jso, String property )
	/*-{
		return jso[property] || null;
	}-*/;

	@Override
	public Field getField( final String fieldName )
	{
		Field res = fields.get( fieldName );
		if( res == null )
		{
			res = new Field()
			{
				@Override
				public void setValue( Object object, Object value )
				{
					setJsoProperty( (JavaScriptObject) object, fieldName, value );
				}

				@Override
				public <OUT> OUT getValue( Object object )
				{
					return getJsoProperty( (JavaScriptObject) object, fieldName );
				}

				@Override
				public Class<?> getType()
				{
					return null;
				}

				@Override
				public String getName()
				{
					return fieldName;
				}

				@Override
				public void copyValueTo( Object source, Object destination )
				{
					throw new RuntimeException( "Not yet implemented" );
				}

				@Override
				public int getModifier()
				{
					// TODO Auto-generated method stub
					return 0;
				}
			};
			fields.put( fieldName, res );
		}
		return res;
	}

	@Override
	public List<Method> getMethods()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Method getMethod( String methodName )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaScriptObject NEW()
	{
		throw new RuntimeException( "Cannot create a JavaScriptObject" );
	}

	@Override
	public Clazz<? super JavaScriptObject> getSuperclass()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Field> getDeclaredFields()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field getDeclaredField( String fieldName )
	{
		return getField( fieldName );
	}

	@Override
	public List<Field> getAllFields()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field getAllField( String fieldName )
	{
		return getField( fieldName );
	}

}
