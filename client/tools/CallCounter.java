package com.hexa.client.tools;

public abstract class CallCounter
{
	public abstract void finalCall();
	
	int c = 0;
	
	public void reset()
	{
		c = 0;
	}
	
	public void add()
	{
		c++;
	}
	
	public void rem()
	{
		c--;
		check();
	}
	
	public void check()
	{
		if( c == 0 )
		{
			finalCall();
			c = -1;
		}
	}
}