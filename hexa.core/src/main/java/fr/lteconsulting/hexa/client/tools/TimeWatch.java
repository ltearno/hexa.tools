package fr.lteconsulting.hexa.client.tools;

import java.util.Date;

public class TimeWatch
{
	long start;

	public TimeWatch()
	{
		start = new Date().getTime();
	}

	public long measureAndRestart()
	{
		long measureStart = start;

		start = new Date().getTime();

		return start - measureStart;
	}
}