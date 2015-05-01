package fr.lteconsulting.hexa.client.tools;

/*
 * This class is useful when wanting to wait for multiple callbacks
 */

public abstract class CallCounter
{
	protected abstract void onFinish();

	private int count = 0;

	public void add()
	{
		count++;
	}

	public void rem()
	{
		count--;

		if( count == 0 )
			onFinish();
	}

	public void reset()
	{
		count = 0;
	}
	
	public int count()
	{
		return count;
	}
}
