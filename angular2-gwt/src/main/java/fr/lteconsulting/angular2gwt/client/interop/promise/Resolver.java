package fr.lteconsulting.angular2gwt.client.interop.promise;

public interface Resolver<T> {
	void resolve();

	void resolve(T value);

	void resolve(Promise<T> value);
}
