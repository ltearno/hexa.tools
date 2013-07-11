package com.hexa.client.databinding;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.hexa.client.classinfo.Clazz;
import com.hexa.client.classinfo.Field;
import com.hexa.client.classinfo.Method;

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
	public List<Field<JavaScriptObject>> getFields()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static HashMap<String, Field<JavaScriptObject>> fields = new HashMap<String, Field<JavaScriptObject>>(); 
	
	private native void setJsoProperty( JavaScriptObject jso, String property, Object value )
	/*-{
		jso[property] = value;
	}-*/;

	@Override
	public Field<JavaScriptObject> getField( final String fieldName )
	{
		Field<JavaScriptObject> res = fields.get( fieldName );
		if( res == null )
		{
			res = new Field<JavaScriptObject>()
			{
				@Override
				public void setValue( Object object, Object value )
				{
					setJsoProperty( (JavaScriptObject) object, fieldName, value );
				}
				
				@Override
				public <OUT> OUT getValue( Object object )
				{
					throw new RuntimeException( "Not yet implemented" );
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
				public void copyValueTo( JavaScriptObject source, JavaScriptObject destination )
				{
					throw new RuntimeException( "Not yet implemented" );
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
		throw new RuntimeException("Cannot create a JavaScriptObject");
	}

}
