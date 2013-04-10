package com.hexa.client.ui;

import com.google.gwt.user.client.ui.CheckBox;

public class CheckBoxEx<COOKIE> extends CheckBox
{
	COOKIE m_cookie;

	public CheckBoxEx( COOKIE cookie )
	{
		super();
		m_cookie = cookie;
	}

	public CheckBoxEx( String value, COOKIE cookie )
	{
		super( value );
		m_cookie = cookie;
	}

	public COOKIE getCookie()
	{
		return m_cookie;
	}

	public void setCookie( COOKIE cookie )
	{
		m_cookie = cookie;
	}
}
