package fr.lteconsulting.hexa.server.data;

import fr.lteconsulting.hexa.client.comm.GenericJSO;
import fr.lteconsulting.hexa.client.data.User;

/*
 * CETTE CLASSE DOIT ETRE RETIRER DU FRAMEWORK HEXA !!!
 */

public class UserDTO implements User
{
	public int id;
	public String first;
	public String last;
	public String login;
	public Integer configurationStoredObjectId;

	@Override
	public void init( GenericJSO jso )
	{
	}

	@Override
	public String getLogin()
	{
		return login;
	}

	@Override
	public String getLast()
	{
		return last;
	}

	@Override
	public int getId()
	{
		return id;
	}

	@Override
	public String getFirst()
	{
		return first;
	}

	@Override
	public int getConfigurationStoredObjectId()
	{
		if( configurationStoredObjectId == null )
			return 0;
		return configurationStoredObjectId;
	}
}