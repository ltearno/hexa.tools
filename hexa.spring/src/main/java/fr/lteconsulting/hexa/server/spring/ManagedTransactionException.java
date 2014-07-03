package fr.lteconsulting.hexa.server.spring;

public class ManagedTransactionException extends RuntimeException
{
	private static final long serialVersionUID = 3802165457470607393L;

	public ManagedTransactionException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
