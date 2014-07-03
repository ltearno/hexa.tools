package fr.lteconsulting.hexa.server.qpath;

import java.util.Collection;
import java.util.List;

import fr.lteconsulting.hexa.server.qpath.DatabaseDescription.FieldDescription;

public class DatabaseMySQLDialect
{
	public String getSqlForCreateTable( boolean fTableNameUpperCase, String tableName, Collection<FieldDescription> columns )
	{
		StringBuilder keys = new StringBuilder();
		int keysComa = 0;

		StringBuilder sql = new StringBuilder();
		sql.append( "CREATE TABLE `" + tableName + "` ( " );
		boolean coma = false;

		for( FieldDescription column : columns )
		{
			if( coma )
				sql.append( ", " );
			else
				coma = true;

			sql.append( getColumnSql( column ) );

			if( column.primaryKey )
				keys.append( (keysComa++ > 0 ? ", " : "") + "PRIMARY KEY (`" + column.name + "`)" );
			else if( column.uniqueKey )
				keys.append( (keysComa++ > 0 ? ", " : "") + "UNIQUE KEY `" + column.name + "` (`" + column.name + "`)" );
			else if( column.multipleIndex )
				keys.append( (keysComa++ > 0 ? ", " : "") + "KEY `" + column.name + "` (`" + column.name + "`)" );
		}

		if( keys.length() > 0 )
			sql.append( ", " + keys.toString() );
		sql.append( " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8" );

		return sql.toString();
	}

	public String getSqlForChangeColumn( boolean fTableNameUpperCase, String tableName, String columnName, FieldDescription column )
	{
		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		return "ALTER TABLE `" + tableName + "` CHANGE `" + columnName + "` " + getColumnSql( column );
	}

	public String getSqlForAddColumn( boolean fTableNameUpperCase, String tableName, FieldDescription column )
	{
		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		return "ALTER TABLE `" + tableName + "` ADD " + getColumnSql( column );
	}

	public String getSqlForDropTable( boolean fTableNameUpperCase, String tableName )
	{
		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		return "DROP TABLE `" + tableName + "`";
	}

	public String getSqlForDropColumn( boolean fTableNameUpperCase, String tableName, String columnName )
	{
		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		return "ALTER TABLE `" + tableName + "` DROP `" + columnName + "` ";
	}

	public String getSqlForDropIndex( boolean fTableNameUpperCase, String tableName, String constraintName )
	{
		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		return "ALTER TABLE `" + tableName + "` DROP INDEX `" + constraintName + "`";
	}

	public String getSqlForCreateConstraint( boolean fTableNameUpperCase, String tableName, String constraintName, List<String> constraintFields )
	{
		if( tableName == null || constraintName == null || constraintFields == null || constraintFields.isEmpty() )
			return null;

		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		String fieldsString = "`" + listToString( constraintFields, "`, `" ) + "`";

		String sql = "ALTER TABLE `" + tableName + "` ADD UNIQUE `" + constraintName + "` ( " + fieldsString + " )";

		return sql;
	}

	public String getSqlForCreateForeignKey( boolean fTableNameUpperCase, String tableName, String foreignKeyName, String referencedTable, String referencedColumn )
	{
		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		return "ALTER TABLE `" + tableName + "` ADD FOREIGN KEY (`" + foreignKeyName + "`) REFERENCES `" + referencedTable + "`(`" + referencedColumn + "`)";
	}

	public String getColumnSql( FieldDescription fieldDesc )
	{
		String defaultValue = "";
		if( fieldDesc.defaultValue != null )
		{
			if( fieldDesc.type.equalsIgnoreCase( "bit(1)" ) )
				defaultValue = fieldDesc.defaultValue;
			else
				defaultValue = "'" + fieldDesc.defaultValue + "'";

			if( defaultValue.equalsIgnoreCase( "CURRENT_TIMESTAMP" ) )
				defaultValue = "DEFAULT CURRENT_TIMESTAMP";
			else if( defaultValue.equalsIgnoreCase( "NULL" ) )
				defaultValue = "DEFAULT NULL";
			else
				defaultValue = "DEFAULT " + defaultValue;
		}

		String comment = "";
		if( fieldDesc.comment != null )
		{
			// TODO : mysql_real_escape_string() sur le commentaire
			comment = "COMMENT '" + fieldDesc.comment + "'";
		}

		return "`" + fieldDesc.name + "` " + fieldDesc.type + " " + (fieldDesc.canNull == "NO" ? "NOT NULL" : "") + " " + defaultValue + " " + fieldDesc.extra + " " + comment;
	}

	public String getSqlForDropForeignKey( boolean fTableNameUpperCase, String tableName, String constraintName )
	{
		if( fTableNameUpperCase )
			tableName = tableName.toUpperCase();

		return "ALTER TABLE `" + tableName + "` DROP FOREIGN KEY `" + constraintName + "`";
	}

	private static String listToString( List<String> list, String concat )
	{
		StringBuilder b = new StringBuilder();
		boolean fAddConcat = false;
		for( String item : list )
		{
			if( concat != null && fAddConcat )
				b.append( concat );
			else
				fAddConcat = true;

			b.append( item );
		}

		return b.toString();
	}
}
