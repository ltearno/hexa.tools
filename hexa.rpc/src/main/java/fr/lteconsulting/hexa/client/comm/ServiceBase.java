package fr.lteconsulting.hexa.client.comm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import fr.lteconsulting.hexa.client.comm.callparams.BooleanMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.DoubleMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.HexaDateMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.HexaDateTimeMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.HexaTimeMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.ITableMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.IntMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.JSOMarshall;
import fr.lteconsulting.hexa.client.comm.callparams.StringMarshall;

public class ServiceBase
{
	protected AcceptsRPCRequests srv = null;
	protected String interfaceChecksum = null;

	// marshalls
	protected static BooleanMarshall booleanMarshall = new BooleanMarshall();
	protected static IntMarshall intMarshall = new IntMarshall();
	protected static StringMarshall stringMarshall = new StringMarshall();
	protected static JSOMarshall jsoMarshall = new JSOMarshall();
	protected static HexaDateTimeMarshall dateTimeMarshall = new HexaDateTimeMarshall();
	protected static HexaDateMarshall dateMarshall = new HexaDateMarshall();
	protected static HexaTimeMarshall timeMarshall = new HexaTimeMarshall();
	protected static DoubleMarshall doubleMarshall = new DoubleMarshall();
	protected static ITableMarshall itableMarshall = new ITableMarshall();

	protected void setConfig( AcceptsRPCRequests srv, String interfaceChecksum )
	{
		this.srv = srv;
		this.interfaceChecksum = interfaceChecksum;
	}

	public String getInterfaceChecksum()
	{
		return interfaceChecksum;
	}

	public AcceptsRPCRequests getCachedServerComm()
	{
		return srv;
	}

	public abstract class Proxy<T>
	{
		abstract public T create( GenericJSO jso );
	}

	class ArrayJSOProxy<T> extends SimplifiedList<T>
	{
		T[] internal;

		Proxy<T> proxy;

		GenericJSO fields;
		int nbFields;

		JsArray<GenericJSO> rows;
		int lenght;

		@SuppressWarnings( "unchecked" )
		public ArrayJSOProxy( GenericJSO jso, Proxy<T> proxy )
		{
			this.proxy = proxy;

			fields = jso.getGenericJSO( "fields" );
			nbFields = fields.length();

			rows = jso.getArray( "rows" );
			lenght = rows.length();

			if( lenght > 0 )
				internal = (T[]) new Object[lenght];
		}

		private native void setJso( JavaScriptObject obj, JsArrayString fields, JavaScriptObject source )
		/*-{
			for( i=0; i<fields.length; i++ )
				obj[fields[i]] = source[i];
		}-*/;

		@Override
		public T get( int index )
		{
			T item = internal[index];
			if( item != null )
				return item;

			GenericJSO row = rows.get( index );

			JavaScriptObject fake = JavaScriptObject.createObject().cast();
			for( int f = 0; f < nbFields; f++ )
				setJso( fake, (JsArrayString) fields.cast(), (JavaScriptObject) row.cast() );

			item = proxy.create( (GenericJSO) fake.cast() );
			internal[index] = item;

			return item;
		}

		@Override
		public boolean isEmpty()
		{
			return lenght == 0;
		}

		@Override
		public Iterator<T> iterator()
		{
			return new It();
		}

		@Override
		public ListIterator<T> listIterator()
		{
			assert false;
			return null;
		}

		@Override
		public ListIterator<T> listIterator( int index )
		{
			assert false;
			return null;
		}

		@Override
		public int size()
		{
			return lenght;
		}

		@Override
		public List<T> subList( int fromIndex, int toIndex )
		{
			assert false;
			return null;
		}

		class It implements Iterator<T>
		{
			int curIndex = 0;

			@Override
			public boolean hasNext()
			{
				return curIndex < lenght;
			}

			@Override
			public T next()
			{
				return get( curIndex++ );
			}

			@Override
			public void remove()
			{
				assert false;
			}

		}
	}

	protected boolean isOptimizedArray( GenericJSO jso )
	{
		try
		{
			String magic = jso.getString( "magic" );
			if( magic != null && magic.equals( "ar2ra" ) )
				return true;
		}
		catch( Exception e )
		{
			return false;
		}

		return false;
	}

	protected <T> List<T> deserializeArray( JsArray<GenericJSO> jsoss, Proxy<T> proxy )
	{
		if( isOptimizedArray( (GenericJSO) jsoss.cast() ) )
		{
			GenericJSO jso = (GenericJSO) jsoss.cast();

			return new ArrayJSOProxy<T>( jso, proxy );

			/*
			 * GenericJSO fields = jso.getGenericJSO( "fields" ); int nbFields =
			 * fields.length();
			 * 
			 * ArrayList<T> result = new ArrayList<T>();
			 * 
			 * JsArray<GenericJSO> rows = jso.getArray( "rows" ); int lenght =
			 * rows.length(); for( int i=0; i<lenght; i++ ) { GenericJSO row =
			 * rows.get( i );
			 * 
			 * // make up a jso ok for the proxy // it is an object with values
			 * indexed by field names GenericJSO item =
			 * JavaScriptObject.createObject().cast();
			 * 
			 * for( int f=0; f<nbFields; f++ ) //item.setObject(
			 * fields.getStringByIdx( f ), row.getGenericJSOByIdx( f ) ); set(
			 * (JavaScriptObject) item.cast(), (JsArrayString) fields.cast(),
			 * (JavaScriptObject) row.cast() );
			 * 
			 * result.add( proxy.create( item ) ); }
			 * 
			 * return result;
			 */
		}
		else
		{
			ArrayList<T> list = new ArrayList<T>();
			int length = jsoss.length();
			for( int i = 0; i < length; i++ )
				list.add( proxy.create( jsoss.get( i ) ) );
			return list;
		}
	}

	private native void set( JavaScriptObject obj, JsArrayString fields, JavaScriptObject source )
	/*-{
		for( i=0; i<fields.length; i++ )
			obj[fields[i]] = source[i];
	}-*/;
}

abstract class SimplifiedList<T> implements List<T>
{
	@Override
	public boolean add( T e )
	{
		assert false;
		return false;
	}

	@Override
	public void add( int index, T element )
	{
		assert false;
	}

	@Override
	public boolean addAll( Collection<? extends T> c )
	{
		assert false;
		return false;
	}

	@Override
	public boolean addAll( int index, Collection<? extends T> c )
	{
		assert false;
		return false;
	}

	@Override
	public void clear()
	{
		assert false;
	}

	@Override
	public boolean contains( Object o )
	{
		assert false;
		return false;
	}

	@Override
	public boolean containsAll( Collection<?> c )
	{
		assert false;
		return false;
	}

	@Override
	public int indexOf( Object o )
	{
		assert false;
		return 0;
	}

	@Override
	public int lastIndexOf( Object o )
	{
		assert false;
		return 0;
	}

	@Override
	public boolean remove( Object o )
	{
		assert false;
		return false;
	}

	@Override
	public T remove( int index )
	{
		assert false;
		return null;
	}

	@Override
	public boolean removeAll( Collection<?> c )
	{
		assert false;
		return false;
	}

	@Override
	public boolean retainAll( Collection<?> c )
	{
		assert false;
		return false;
	}

	@Override
	public T set( int index, T element )
	{
		assert false;
		return null;
	}

	@Override
	public Object[] toArray()
	{
		assert false;
		return null;
	}

	@Override
	public <U> U[] toArray( U[] a )
	{
		assert false;
		return null;
	}
}