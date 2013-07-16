package com.hexa.client.databinding.propertyadapters;

import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.tools.Action2;

public class CompositePropertyAdapter implements PropertyAdapter
{
	public final static String HASVALUE_TOKEN = "$HasValue";
	public final static String DTOMAP_TOKEN = "$DTOMap";

	private final Object source;
	private final String[] path;

	public CompositePropertyAdapter( Object source, String property )
	{
		this.source = source;
		path = property.split( "\\." );
	}

	@Override
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		PropertyChangedManager[] managers = new PropertyChangedManager[path.length];

		// initiate watching on properties (first, the first one on the path)
		managers[0] = new PropertyChangedManager( 0, callback, managers );
		managers[0].register( source );

		return managers;
	}

	@Override
	public void removePropertyChangedHandler( Object handler )
	{
		PropertyChangedManager[] managers = (PropertyChangedManager[]) handler;
		for( int i=0; i<managers.length; i++ )
		{
			if( managers[i] == null )
				continue;

			managers[i].unregister();
			managers[i] = null;
		}
	}

	private Object getValue( int level )
	{
		Object cur = source;

		for( int i=0; i<=level; i++)
		{
			if( cur == null )
				return null;

			cur = ObjectPropertiesUtils.GetProperty( cur, path[i] );
		}

		return cur;
	}

	@Override
	public Object getValue()
	{
		return getValue(path.length-1);
	}

	@Override
	public void setValue( Object object )
	{
		Object cur = source;

		if( path.length > 1 )
			cur = getValue( path.length - 2 );

		if( cur == null )
			return;

		ObjectPropertiesUtils.SetProperty( cur, path[path.length-1], object );
	}

	class PropertyChangedManager implements Action2<PropertyAdapter, Object>
	{
		PropertyChangedManager[] managers;
		Action2<PropertyAdapter, Object> callback;

		int position;

		PropertyAdapter adapter;
		Object handler;

		public PropertyChangedManager( int position, Action2<PropertyAdapter, Object> callback, PropertyChangedManager[] managers )
		{
			this.callback = callback;
			this.position = position;
			this.managers = managers;
		}

		@SuppressWarnings( "rawtypes" )
		public void register( Object source )
		{
			unregister();

			String pptyName = path[position];

			if( HASVALUE_TOKEN.equals( pptyName ) )
			{
				assert (source instanceof HasValue) : "CompositeObjectAdapter : source is not HasValue : " + source.getClass().getName();
				adapter = new WidgetPropertyAdapter( (HasValue) source );
			}
			else if( DTOMAP_TOKEN.equals( pptyName ) )
			{
				adapter = new DTOMapperPropertyAdapter( source );
			}
			else
			{
				adapter = new ObjectPropertyAdapter( source, path[position] );
			}

			handler = adapter.registerPropertyChanged( this, null );
			if( handler == null )
			{
				// exec() will never be called, so try to call it now !
				exec( CompositePropertyAdapter.this, null );
			}
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
		public void exec( PropertyAdapter param, Object cookie )
		{
			// is that the end ?
			if( position == path.length-1 )
			{
				callback.exec( CompositePropertyAdapter.this, null );
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
				managers[position+1] = new PropertyChangedManager( position+1, callback, managers );

			managers[position+1].register( currentValue );

			managers[position+1].exec( param, null );
		}
	}
}
