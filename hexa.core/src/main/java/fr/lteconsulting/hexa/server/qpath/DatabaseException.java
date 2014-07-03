package fr.lteconsulting.hexa.server.qpath;

public class DatabaseException extends RuntimeException
{
	private static final long serialVersionUID = -1618044239637755171L;

	public DatabaseException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
