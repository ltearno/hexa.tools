package com.hexa.client.databinding;

import com.hexa.client.classinfo.ClassInfo;
import com.hexa.client.classinfo.Clazz;
import com.hexa.client.classinfo.Method;
import com.hexa.client.databinding.DataBinding.DataAdapter;
import com.hexa.client.tools.Action1;

public class CompositeObjectAdapter implements DataAdapter
{
	private final Object source;
	private final String property;
	
	public CompositeObjectAdapter( Object source, String property )
	{
		this.source = source;
		this.property = property;
	}
	
	class PropertyChangedManager implements Action1<DataBinding.DataAdapter>
	{
		PropertyChangedManager[] managers;
		
		Action1<DataAdapter> callback;
		
		String[] path;
		int position;
		
		ObjectAdapter adapter;
		Object handler;
		
		public PropertyChangedManager( String[] path, int position, Action1<DataAdapter> callback, PropertyChangedManager[] managers )
		{
			this.callback = callback;
			this.path = path;
			this.position = position;
			this.managers = managers;
		}
		
		public void register( Object source )
		{
			unregister();
			
			adapter = new ObjectAdapter( source, path[position] );
			
			handler = adapter.registerPropertyChanged( this );
			
			//handler = adapter.registerPropertyChanged( new PropertyChangedManager( path, i, adapters, handlers, managers ) );
		}
		
		public void unregister()
		{
			if( adapter != null && handler != null )
				adapter.removePropertyChangedHandler( handler );
			
			adapter = null;
			handler = null;
		}
		
		// a change occurs on the watched property
		@Override
		public void exec( DataAdapter param )
		{
			// is that the end ?
			if( position == path.length-1 )
			{
				callback.exec( CompositeObjectAdapter.this );
				return; // that's it
			}
			
			// get the current value, if not null, register on it
			Object currentValue = getValue( position );
			if( currentValue == null )
			{
				for( int r=position+1; r<managers.length; r++ )
					if( managers[r] != null )
						managers[r].unregister();
				return;
			}
			
			if( managers[position+1] == null )
				managers[position+1] = new PropertyChangedManager( path, position+1, callback, managers );
			
			managers[position+1].register( currentValue );
			
			managers[position+1].exec( param );
		}
	}
	
	@Override
	public Object registerPropertyChanged( final Action1<DataAdapter> callback )
	{
		String[] path = property.split( "\\." );
		if( path==null || path.length<=0 )
			path = new String[] { property };
		
		PropertyChangedManager[] managers = new PropertyChangedManager[path.length];
		
		managers[0] = new PropertyChangedManager( path, 0, callback, managers );
		managers[0].register( source );
		
		return managers;
	}

	@Override
	public void removePropertyChangedHandler( Object handler )
	{
		PropertyChangedManager[] managers = (PropertyChangedManager[]) handler;
		for( int i=0; i<managers.length; i++ )
		{
			managers[i].unregister();
			managers[i] = null;
		}
	}
	
	private Object getValue( int level )
	{
		String[] path = property.split( "\\." );
		
		Object cur = source;
		for( int i=0; i<=level; i++)
		{
			if( cur == null )
				return null;
			
			Clazz<?> s = ClassInfo.Clazz( cur.getClass() );

			String getterName = "get" + canon(path[i]);
			Method getter = s.getMethod( getterName );
			assert getter != null : "ObjectAdapter : getter " + getterName + " not found !";
			
			cur = getter.call( cur, null );
		}
		
		return cur;
	}

	@Override
	public Object getValue()
	{
		String[] path = property.split( "\\." );
		
		return getValue(path.length-1);
	}

	@Override
	public void setValue( Object object )
	{
		String[] path = property.split( "\\." );
		
		Object cur = source;
		for( int i=0; i<path.length-1; i++)
		{
			if( cur == null )
				return;
			
			Clazz<?> s = ClassInfo.Clazz( cur.getClass() );

			String getterName = "get" + canon(path[i]);
			Method getter = s.getMethod( getterName );
			assert getter != null : "ObjectAdapter : getter " + getterName + " not found !";
			
			cur = getter.call( cur, null );
		}
		
		Clazz<?> s = ClassInfo.Clazz( cur.getClass() );

		String setterName = "set" + canon(path[path.length-1]);
		Method setter = s.getMethod( setterName );
		assert setter != null : "ObjectAdapter : setter " + setterName + " not found !";
		
		setter.call( cur, new Object[] { object } );
	}
	
	private static String canon(String s)
	{
		return s.substring( 0, 1 ).toUpperCase() + s.substring( 1 );
	}
}
