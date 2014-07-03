package fr.lteconsulting.hexa.server.tools;

import java.util.Stack;

public class Trace
{
	private static final ThreadLocal<Stack<Trace>> instances = new ThreadLocal<Stack<Trace>>();

	private final StringBuilder sb = new StringBuilder();

	public static void push()
	{
		Stack<Trace> traces = ensureInstances();

		traces.push( new Trace() );
	}

	public static String pop()
	{
		Stack<Trace> traces = instances.get();
		if( traces == null || traces.isEmpty() )
			return "(empty)";
		Trace trace = traces.pop();

		return trace.sb.toString();
	}

	public static String peek()
	{
		return getInstance().sb.toString();
	}

	public static void it( String message )
	{
		getInstance().traceImpl( message );
	}

	public static void throwable( Throwable exception )
	{
		getInstance().throwableImpl( exception );
	}

	private final static Trace getInstance()
	{
		Stack<Trace> traces = ensureInstances();

		if( traces.isEmpty() )
			traces.push( new Trace() );

		Trace trace = traces.peek();

		return trace;
	}

	private static Stack<Trace> ensureInstances()
	{
		Stack<Trace> traces = instances.get();
		if( traces == null )
		{
			traces = new Stack<Trace>();
			instances.set( traces );
		}

		return traces;
	}

	private void traceImpl( String message )
	{
		sb.append( message );
		sb.append( "\r\n" );
	}

	private void throwableImpl( Throwable exception )
	{
		sb.append( "Throwable " + exception.getClass().getName() + "\r\n" );
		sb.append( "   message: " + exception.getMessage() + "\r\n" );
		sb.append( "   stack trace:" + "\r\n" );

		StackTraceElement[] stackTrace = exception.getStackTrace();
		for( int i = 0; i < stackTrace.length; i++ )
		{
			StackTraceElement trace = stackTrace[i];

			sb.append( "       - " + trace.getFileName() + ", line " + trace.getLineNumber() + " in " + trace.getClassName() + "." + trace.getMethodName() + "\r\n" );
		}
	}
}
