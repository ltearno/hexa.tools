package fr.lteconsulting.hexa.classinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassInfoJre implements IClassInfo
{
	private static final ClassInfoJre instance = new ClassInfoJre();

	public static ClassInfoJre get()
	{
		return instance;
	}

	private ClassInfoJre()
	{
	}

	HashMap<Class<?>, ClazzJre<?>> clazzCache = new HashMap<>();

	@Override
	public <T> Clazz<T> Clazz( Class<T> clazz )
	{
		ClazzJre<T> result = FindClazz( clazz );
		if( result == null )
			throw new RuntimeException( "Cannot find class '" + clazz.getName() + "'" );

		return result;
	}

	@Override
	public <T> void RegisterClazz( Clazz<T> clazz )
	{
		clazzCache.put( clazz.getReflectedClass(), (ClazzJre<?>) clazz );
	}

	@Override
	public Clazz<?> FindClazz( String name )
	{
		try
		{
			return FindClazz( Class.forName( name ) );
		}
		catch( ClassNotFoundException e )
		{
			return null;
		}
	}

	@Override
	public <T> ClazzJre<T> FindClazz( Class<T> clazz )
	{
		@SuppressWarnings( "unchecked" )
		ClazzJre<T> result = (ClazzJre<T>) clazzCache.get( clazz );
		if( result == null )
		{
			result = new ClazzJre<T>( clazz );
			clazzCache.put( clazz, result );
		}

		return result;
	}

	@Override
	public Set<Class<?>> GetRegisteredClazz()
	{
		return clazzCache.keySet();
	}
}

class ClazzJre<T> implements Clazz<T>
{
	private Class<T> classs;
	private List<Field> fields;
	private List<Method> methods;
	private Map<String, Method> methodsByName;

	public ClazzJre( Class<T> classs )
	{
		this.classs = classs;
	}

	@Override
	public String getClassName()
	{
		return classs.getName();
	}

	@Override
	public Class<T> getReflectedClass()
	{
		return classs;
	}

	@Override
	public Clazz<? super T> getSuperclass()
	{
		return ClassInfoJre.get().Clazz( classs.getSuperclass() );
	}

	@Override
	public List<Field> getAllFields()
	{
		if( fields != null )
			return fields;

		fields = new ArrayList<>();

		Class<?> cur = classs;
		while( cur != null && cur != Object.class )
		{
			for( java.lang.reflect.Field f : classs.getDeclaredFields() )
				fields.add( new FieldJre( f ) );

			cur = cur.getSuperclass();
		}

		return fields;
	}

	@Override
	public Field getAllField( String fieldName )
	{
		for( Field field : getAllFields() )
		{
			if( field.getName().equals( fieldName ) )
				return field;
		}
		return null;
	}

	@Override
	public List<Field> getFields()
	{
		// TODO
		return getAllFields();
	}

	@Override
	public Field getField( String fieldName )
	{
		// TODO
		return getAllField( fieldName );
	}

	@Override
	public List<Field> getDeclaredFields()
	{
		// TODO
		return getAllFields();
	}

	@Override
	public Field getDeclaredField( String fieldName )
	{
		// TODO
		return getAllField( fieldName );
	}

	@Override
	public List<Method> getMethods()
	{
		if( methods != null )
			return methods;

		methods = new ArrayList<>();

		for( java.lang.reflect.Method m : classs.getMethods() )
			methods.add( new MethodJre( m ) );

		return methods;
	}

	@Override
	public Method getMethod( String methodName )
	{
		if( methodsByName == null ){
			methodsByName = new HashMap<>();
			for( Method m : getMethods() )
				methodsByName.put(m.getName(), m);
		}
		
		return methodsByName.get(methodName);
	}

	@Override
	public T NEW()
	{
		T result;
		try
		{
			result = classs.newInstance();
		}
		catch( Exception e )
		{
			throw new RuntimeException( e );
		}

		return result;
	}
}

class FieldJre implements Field
{
	private java.lang.reflect.Field field;

	public FieldJre( java.lang.reflect.Field field )
	{
		this.field = field;
		if( !field.isAccessible() )
			field.setAccessible( true );
	}

	@Override
	public String getName()
	{
		return field.getName();
	}

	@Override
	public Class<?> getType()
	{
		return field.getType();
	}

	@Override
	public void setValue( Object object, Object value )
	{
		try
		{
			field.set( object, value );
		}
		catch( Exception e )
		{
			throw new RuntimeException( e );
		}
	}

	@Override
	public <OUT> OUT getValue( Object object )
	{
		try
		{
			@SuppressWarnings( "unchecked" )
			OUT result = (OUT) field.get( object );
			return result;
		}
		catch( Exception e )
		{
			throw new RuntimeException( e );
		}
	}

	@Override
	public void copyValueTo( Object source, Object destination )
	{
		try
		{
			field.set( destination, field.get( source ) );
		}
		catch( Exception e )
		{
			throw new RuntimeException( e );
		}
	}

	@Override
	public int getModifier()
	{
		return field.getModifiers();
	}

	@Override
	public String toString()
	{
		return "[FieldJre " + field.getType().getName() + " " + field.getName() + "]";
	}
}

class MethodJre implements Method
{
	private java.lang.reflect.Method method;
	private List<Class<?>> parameterTypes;

	public MethodJre( java.lang.reflect.Method method )
	{
		this.method = method;
		if( !method.isAccessible() )
			method.setAccessible( true );
	}

	@Override
	public String getName()
	{
		return method.getName();
	}

	@Override
	public Class<?> getReturnType()
	{
		return method.getReturnType();
	}

	@Override
	public List<Class<?>> getParameterTypes()
	{
		if( parameterTypes != null )
			return parameterTypes;

		Class<?>[] arr = method.getParameterTypes();

		parameterTypes = new ArrayList<>();

		for( Class<?> c : arr )
			parameterTypes.add( c );

		return parameterTypes;
	}

	@Override
	public Object invoke( Object target, Object... parameters )
	{
		try
		{
			return method.invoke( target, parameters );
		}
		catch( Exception e )
		{
			throw new RuntimeException( e );
		}
	}

	@Override
	public String toString()
	{
		return "[MethodJre " + method.getName() + "]";
	}
}