package fr.lteconsulting.hexa.client.comm;

public interface HexaUser // extends DataProxy
{
	// @FieldName( fieldName="users.id" )
	int getId();

	// @FieldName( fieldName="users.login" )
	String getLogin();

	// @FieldName( fieldName="users.password_md5" )
	String getPasswordMd5();
}
