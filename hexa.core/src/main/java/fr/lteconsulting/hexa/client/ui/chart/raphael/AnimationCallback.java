package fr.lteconsulting.hexa.client.ui.chart.raphael;

public abstract class AnimationCallback
{
	public abstract void onComplete();

	static public void fire( AnimationCallback c )
	{
		c.onComplete();
	}
}
