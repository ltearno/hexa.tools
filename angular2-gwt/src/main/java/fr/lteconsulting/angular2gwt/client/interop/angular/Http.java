package fr.lteconsulting.angular2gwt.client.interop.angular;

import fr.lteconsulting.angular2gwt.client.JsObject;
import fr.lteconsulting.angular2gwt.client.interop.angular.rxjs.Observable;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.http", name = "Http" )
public class Http
{
	public native Observable<Response> get( String url );

	public native Observable<Response> post( String url );

	public native Observable<Response> post( String url, String data, JsObject options );

	public native Observable<Response> put( String url );

	public native Observable<Response> delete( String url );
}
