package fr.lteconsulting.hexa.client.comm;

public interface Stack<T>
{
	final static int NB_OBJS = 50;

	T alloc();
}
