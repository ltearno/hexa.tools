package fr.lteconsulting.hexa.rebind;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.comm.DataProxyFast;
import fr.lteconsulting.hexa.client.comm.GenericJSO;
import com.google.gwt.core.client.JsArray;

public class ServerImpl
{
	public interface Proxy<T>
	{
		public T create( GenericJSO jso );
	}

	@SuppressWarnings( "unused" )
	private <T> ArrayList<T> deserializeArray( JsArray<GenericJSO> jsoss, Proxy<T> proxy )
	{
		ArrayList<T> list = new ArrayList<T>();
		for( int i = 0; i < jsoss.length(); i++ )
			list.add( proxy.create( jsoss.get( i ) ) );
		return list;
	}

	/*
	 * EXAMPLE DATA PROXY FAST
	 */
	interface Daat extends DataProxyFast
	{
		int getId();
	}

	/*
	 * class DaatDataProxyFastFactory implements
	 * DataProxyFastFactories.IDataProxyFastFactory { class DaatImpl extends
	 * GenericJSO implements Daat { protected DaatImpl() {}
	 * 
	 * public final int getId() { return getInt( "field_name" ); } }
	 * 
	 * @Override public <T> T getData( JavaScriptObject obj ) { return
	 * (T)((DaatImpl)obj); } }
	 */
}
