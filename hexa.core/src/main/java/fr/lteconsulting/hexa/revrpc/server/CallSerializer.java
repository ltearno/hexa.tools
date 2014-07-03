package fr.lteconsulting.hexa.revrpc.server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class CallSerializer
{
	public interface SerializedCallReceiver
	{
		void newCall( Class<?> interfaceClass, JSONObject obj );
	}

	public CallSerializer( SerializedCallReceiver callReceiver )
	{
		this.callReceiver = callReceiver;
	}

	private SerializedCallReceiver callReceiver;
	HashMap<Class<?>, Object> broadcastProxies = new HashMap<Class<?>, Object>();

	public <T> T makeProxy( Class<T> implementedInterface, SerializedCallReceiver callback )
	{
		SerializerInvocationHandler handler = new SerializerInvocationHandler( implementedInterface, callback );

		@SuppressWarnings( "unchecked" )
		T proxy = (T) Proxy.newProxyInstance( implementedInterface.getClassLoader(), new Class<?>[] { implementedInterface }, handler );

		return proxy;
	}

	public <T> T queryBroadcastInterface( Class<T> implementedInterface )
	{
		@SuppressWarnings( "unchecked" )
		T proxy = (T) broadcastProxies.get( implementedInterface );
		if( proxy != null )
			return proxy;

		proxy = makeProxy( implementedInterface, callReceiver );

		broadcastProxies.put( implementedInterface, proxy );

		return proxy;
	}

	class SerializerInvocationHandler implements InvocationHandler
	{
		private Class<?> proxiedClass = null;
		private SerializedCallReceiver callback = null;

		public SerializerInvocationHandler( Class<?> proxiedClass, SerializedCallReceiver callback )
		{
			this.proxiedClass = proxiedClass;
			this.callback = callback;
		}

		public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
		{
			JSONObject json = new JSONObject();
			json.put( "method", (String) method.getName() );
			JSONArray jsonArgs = new JSONArray();
			for( int i = 0; i < args.length; i++ )
			{
				JSONObject jsonArg = new JSONObject();
				jsonArg.put( "type", (String) args[i].getClass().getCanonicalName() );
				Object jsonArgValue = serializeToJSON( args[i] );
				jsonArg.put( "value", jsonArgValue );

				jsonArgs.set( i, jsonArg );
			}
			json.put( "args", jsonArgs );

			// emit the call
			callback.newCall( proxiedClass, json );

			return null;
		}
	}

	private Object serializeToJSON( Object obj )
	{
		if( obj instanceof String )
			return (String) obj;
		if( obj instanceof Boolean )
			return (Boolean) obj;
		if( obj instanceof Integer )
			return (Integer) obj;

		if( obj.getClass().isEnum() )
		{
			Object values[] = obj.getClass().getEnumConstants();
			for( int e = 0; e < values.length; e++ )
			{
				if( obj == values[e] )
				{
					// we found our enum
					Method nameMethod = null;
					try
					{
						nameMethod = obj.getClass().getMethod( "name" );
					}
					catch( SecurityException e1 )
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch( NoSuchMethodException e1 )
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String enumName = null;
					try
					{
						enumName = (String) nameMethod.invoke( obj );
					}
					catch( IllegalArgumentException e1 )
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch( IllegalAccessException e1 )
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch( InvocationTargetException e1 )
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					return enumName;
				}
			}
		}

		JSONObject json = new JSONObject();
		Field fields[] = obj.getClass().getFields();
		for( int i = 0; i < fields.length; i++ )
		{
			Field field = fields[i];
			// TODO : not serialize field if final or transient

			Object fieldValue = null;
			try
			{
				fieldValue = field.get( obj );
			}
			catch( IllegalArgumentException e )
			{
				e.printStackTrace();
			}
			catch( IllegalAccessException e )
			{
				e.printStackTrace();
			}

			json.put( field.getName(), serializeToJSON( fieldValue ) );
		}

		return json;
	}
}
