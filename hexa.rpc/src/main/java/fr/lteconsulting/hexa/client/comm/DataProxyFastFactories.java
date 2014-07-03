package fr.lteconsulting.hexa.client.comm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class DataProxyFastFactories
{
	HashMap<Class<?>, IDataProxyFastFactory> factories = null;
	HashMap<Class<?>, IterableFactory> iterableFactories = new HashMap<Class<?>, IterableFactory>();

	public void init( HashMap<Class<?>, IDataProxyFastFactory> factories )
	{
		this.factories = factories;

		for( Entry<Class<?>, IDataProxyFastFactory> e : factories.entrySet() )
			iterableFactories.put( e.getKey(), new IterableFactory( e.getValue() ) );
	}

	public <T> T getData( Class<T> clazz, JavaScriptObject obj )
	{
		IDataProxyFastFactory factory = factories.get( clazz );
		assert (factory != null) : "Factory not null";
		if( factory == null )
			return null;

		return factory.getData( obj );
	}

	public <T> Iterable<T> getList( Class<T> clazz, JsArray<JavaScriptObject> obj )
	{
		IterableFactory factoryIterable = iterableFactories.get( clazz );
		if( factoryIterable == null )
		{
			IDataProxyFastFactory factory = factories.get( clazz );
			assert (factory != null) : "Factory is null for class " + clazz.toString();
			if( factory == null )
				return null;

			factoryIterable = new IterableFactory( factory );
			iterableFactories.put( clazz, factoryIterable );
		}

		return factoryIterable.getIterable( obj );
	}

	class IterableFactory
	{
		IDataProxyFastFactory factory;

		public IterableFactory( IDataProxyFastFactory factory )
		{
			this.factory = factory;
		}

		public <T> Iterable<T> getIterable( JsArray<JavaScriptObject> obj )
		{
			return new It<T>( obj );
		}

		class It<T> implements Iterable<T>
		{
			JsArray<JavaScriptObject> obj;

			public It( JsArray<JavaScriptObject> obj )
			{
				this.obj = obj;
			}

			public Iterator<T> iterator()
			{
				return new Iterator<T>()
				{
					int idx = 0;

					public boolean hasNext()
					{
						return idx < obj.length();
					}

					public T next()
					{
						return factory.getData( obj.get( idx++ ) );
					}

					public void remove()
					{
						assert false : "remove forbidden";
					}
				};
			}
		}
	}
}