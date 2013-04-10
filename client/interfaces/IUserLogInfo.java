package com.hexa.client.interfaces;

import com.google.gwt.core.client.JavaScriptObject;

public interface IUserLogInfo
{
	public interface ISessionUse
	{
		String getSessionId();
		int getIteration();
		String getChallengeAnswer();
	}

	public ISessionUse getSessionUse();
	public String getUserMd5Pass();
	public String getUserLogin();
	public void setUserJSO( JavaScriptObject jso );
}
