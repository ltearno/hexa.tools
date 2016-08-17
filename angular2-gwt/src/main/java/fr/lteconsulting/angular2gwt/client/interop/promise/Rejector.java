package fr.lteconsulting.angular2gwt.client.interop.promise;

public interface Rejector {
	void reject();

	void reject(Object reason);
}
