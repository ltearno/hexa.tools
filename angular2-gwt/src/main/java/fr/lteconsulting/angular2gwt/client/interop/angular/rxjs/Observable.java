package fr.lteconsulting.angular2gwt.client.interop.angular.rxjs;

import fr.lteconsulting.angular2gwt.client.interop.promise.Promise;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = "ng.rxjs", name = "Observable" )
public class Observable<T>
{
	public native Subscription subscribe( Observer<T> observer );

	public native <U> Observable<U> map( MapFunction<T, U> mapFunction );

	public native Promise<T> toPromise();
}