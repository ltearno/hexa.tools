package fr.lteconsulting.hexa.client.tools;

import com.google.gwt.user.client.Timer;

/*
 * in input, many events,
 * in output, events at a max rate of xxx milliseconds or,
 * if fPostponeEachTime is true => callback called xxx ms after the last event received
 */

public class Delayer
{
	public interface Callback
	{
		void onDelayedEvent();
	}

	Callback callback;
	int milliseconds;
	boolean fTriggered = false;
	boolean fPostponeEachTime;

	public Delayer( int milliseconds, Callback callback, boolean fPostponeEachTime )
	{
		this.milliseconds = milliseconds;
		this.callback = callback;
		this.fPostponeEachTime = fPostponeEachTime;
	}

	public void trigger()
	{
		// if an action is already registered, postpone it
		if( fTriggered )
		{
			if( fPostponeEachTime )
			{
				reallyDoTimer.cancel();
				reallyDoTimer.schedule( milliseconds );
			}
			return;
		}

		// schedule action ...

		fTriggered = true;

		reallyDoTimer.schedule( milliseconds );
	}

	Timer reallyDoTimer = new Timer()
	{
		@Override
		public void run()
		{
			fTriggered = false;
			reallyDoTimer.cancel();

			callback.onDelayedEvent();
			
			reallyDoTimer.cancel();
		}
	};
}
