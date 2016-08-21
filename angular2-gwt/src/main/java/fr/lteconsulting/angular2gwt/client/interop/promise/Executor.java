package fr.lteconsulting.angular2gwt.client.interop.promise;

import jsinterop.annotations.JsFunction;

@FunctionalInterface
@JsFunction
@SuppressWarnings( "unusable-by-js" )
public interface Executor<V>
{
	void execute( Resolver<V> resolver, Rejector rejecter );
}
