package com.hexa.client.interfaces;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class SimpleAsyncCallback<T> implements AsyncCallback<T>
{
	public void onFailure(Throwable caught) {}
}
