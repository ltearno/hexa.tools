package fr.lteconsulting.hexa.client.data;

import fr.lteconsulting.hexa.client.comm.DataProxy;
import fr.lteconsulting.hexa.client.comm.FieldName;

/*
 * CETTE CLASSE DOIT ETRE RETIRER DU FRAMEWORK HEXA !!!
 */

public interface User extends DataProxy
{
	@FieldName( fieldName = "users.id" )
	int getId();

	@FieldName( fieldName = "users.login" )
	String getLogin();

	// @FieldName( fieldName="users.password_md5" )
	// String getPasswordMd5();

	@FieldName( fieldName = "users.first" )
	String getFirst();

	@FieldName( fieldName = "users.last" )
	String getLast();

	@FieldName( fieldName = "users.configuration_stored_object_id" )
	int getConfigurationStoredObjectId();
}
