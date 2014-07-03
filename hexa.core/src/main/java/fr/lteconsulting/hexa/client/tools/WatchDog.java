package fr.lteconsulting.hexa.client.tools;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

// when pinging 3 times consecutively, declare us as online

public class WatchDog
{
	public Action1<AsyncCallback<String>> pingAction = new Action1<AsyncCallback<String>>()
	{
		@Override
		public void exec( AsyncCallback<String> param )
		{
		}
	};

	Action _onOnlineAction;

	boolean fOnline = true;
	int timesToPing = 0;

	boolean retryPlanned = false;

	public void setOnOnlineAction( Action onOnlineAction )
	{
		_onOnlineAction = onOnlineAction;
	}

	public void startWatching()
	{
		// consider us as offline, otherwise we wouldn't be watching...
		fOnline = false;
		timesToPing = 3;

		// start pinging
		pingAction.exec( pingCallback );
	}

	AsyncCallback<String> pingCallback = new AsyncCallback<String>()
	{
		@Override
		public void onSuccess( String result )
		{
			timesToPing--;

			if( timesToPing <= 0 )
			{
				// we are now online !
				fOnline = true;
				timesToPing = 0;

				// signal that we are online
				_onOnlineAction.exec();
			}
			else
			{
				// retry in a few moments
				planRetry();
			}
		}

		@Override
		public void onFailure( Throwable caught )
		{
			// continue offline
			fOnline = false;
			timesToPing = 3;

			// retry in a few moments
			planRetry();
		}
	};

	void planRetry()
	{
		if( retryPlanned )
			return;

		retryPlanned = true;
		Scheduler.get().scheduleFixedDelay( retryCommand, 1000 );
	}

	RepeatingCommand retryCommand = new RepeatingCommand()
	{
		@Override
		public boolean execute()
		{
			retryPlanned = false;

			pingAction.exec( pingCallback );

			return false;
		}
	};
}
