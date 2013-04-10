package com.hexa.server.qpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.server.tools.Trace;
import com.hexa.server.qpath.DatabaseDescription.FieldDescription;
import com.hexa.server.qpath.DatabaseDescription.FieldReference;
import com.hexa.server.qpath.DatabaseDescription.TableDescription;

public class DatabaseDescriptionInspector
{
	// returns an array containing the description of the database schema
	public DatabaseDescription getDatabaseDescription( Database db, DatabaseHelper dbh )
	{
		DatabaseDescription dbDesc = new DatabaseDescription( db.getCurrentDatabase() );

		ArrayList<String> tables = dbh.getTables();
		for( String table : tables )
		{
			TableDescription tableDesc = dbDesc.addTable( table );

			DBResults fields = db.sql( "DESCRIBE " + table );
			int fieldNameColumn = fields.getColumnIndex( "Field" );
			int typeColumn = fields.getColumnIndex( "Type" );
			int canNullColumn = fields.getColumnIndex( "Null" );
			int defaultColumn = fields.getColumnIndex( "Default" );
			int extraColumn = fields.getColumnIndex( "Extra" );
			int keyColumn = fields.getColumnIndex( "Key" );

			while( fields.next() )
			{
				String fieldName = fields.getString( fieldNameColumn );
				if( fieldName.startsWith( "synchro_server" ) )
					continue;

				String type = fields.getString( typeColumn );
				String canNull = fields.getString( canNullColumn );
				String defaultValue = fields.getString( defaultColumn );
				String extra = fields.getString( extraColumn );
				String key = fields.getString( keyColumn );

				FieldDescription fieldDesc = tableDesc.addField( fieldName, type, canNull, defaultValue, extra, key );

				DBResults info = db.sql( "SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='" + dbDesc.name + "' AND TABLE_NAME='" + table
						+ "' AND COLUMN_NAME='" + fieldName + "'" );
				if( info.getRowCount() != 1 )
				{
					System.out.println( "BIG PROBLEM, column has no or multiple definitions !!!" );
					return null;
				}
				int commentColumn = info.getColumnIndex( "COLUMN_COMMENT" );
				if( commentColumn > 0 )
				{
					info.next(); // seek to the first and only result row
					fieldDesc.comment = info.getString( commentColumn );
				}
			}
		}

		// Show constraints
		DBResults constraints = db.sql( "SELECT * FROM information_schema.KEY_COLUMN_USAGE WHERE TABLE_SCHEMA='" + dbDesc.name + "'" );
		int tableColumn = constraints.getColumnIndex( "TABLE_NAME" );
		int columnNameColumn = constraints.getColumnIndex( "COLUMN_NAME" );
		int constraintNameColumn = constraints.getColumnIndex( "CONSTRAINT_NAME" );
		int referencedTableColumn = constraints.getColumnIndex( "REFERENCED_TABLE_NAME" );
		int referencedFieldColumn = constraints.getColumnIndex( "REFERENCED_COLUMN_NAME" );

		while( constraints.next() )
		{
			String table = constraints.getString( tableColumn );
			String fieldName = constraints.getString( columnNameColumn );
			String constraintName = constraints.getString( constraintNameColumn );

			if( constraintName.equalsIgnoreCase( "PRIMARY" ) )
			{
				dbDesc.tables.get( table ).fields.get( fieldName ).primaryKey = true;
			}
			else
			{
				String refTable = constraints.getString( referencedTableColumn );
				String refField = constraints.getString( referencedFieldColumn );

				if( refTable == null || refField == null )
					continue; // What is that ?

				dbDesc.tables.get( table ).fields.get( fieldName ).addReferenceField( refTable, refField, constraintName );
			}
		}

		return dbDesc;
	}

	class SetComparison
	{
		HashSet<String> newItems = new HashSet<String>();
		HashSet<String> removedItems = new HashSet<String>();
		HashSet<String> maybeModifiedItems = new HashSet<String>();

		public SetComparison()
		{
		}

		public void compareSets( Set<String> current, Set<String> target )
		{
			removedItems.addAll( current );

			for( String tgt : target )
			{
				if( current.contains( tgt ) )
				{
					maybeModifiedItems.add( tgt );
					removedItems.remove( tgt );
				}
				else
				{
					newItems.add( tgt );
				}
			}
		}
	}

	// returns the array of sql statements to be executed to update the db
	// currentDB is a kind of a hack : for the deletion of a constraint in the target database, we need to query the db for all the constraint names to delete
	// them
	public ArrayList<String> getSqlForUpdateDb( DatabaseDescription currentDbDesc, DatabaseDescription targetDbDesc, boolean fDoDelete )
	{
		Trace.push();
		Trace.it( "Database comparison" );

		ArrayList<String> sqls = new ArrayList<String>();
		ArrayList<String> sqlRefs = new ArrayList<String>();

		ArrayList<String> newTables = new ArrayList<String>();
		ArrayList<String> maybeModifiedTables = new ArrayList<String>();
		HashSet<String> removedTables = new HashSet<String>();
		for( TableDescription t : currentDbDesc.tables.values() )
			removedTables.add( t.name );

		for( TableDescription table : targetDbDesc.tables.values() )
		{
			TableDescription existingTable = currentDbDesc.tables.get( table.name );
			if( existingTable == null )
			{
				newTables.add( table.name );
			}
			else
			{
				maybeModifiedTables.add( table.name );
				removedTables.remove( table.name );
			}
		}

		// new tables
		for( String newTableName : newTables )
		{
			TableDescription desc = targetDbDesc.tables.get( newTableName );
			Trace.it( "New table " + newTableName );

			StringBuilder keys = new StringBuilder();
			int keysComa = 0;

			StringBuilder sql = new StringBuilder();
			sql.append( "CREATE TABLE `" + newTableName + "` ( " );
			boolean coma = false;

			for( FieldDescription fieldDesc : desc.fields.values() )
			{
				if( coma )
					sql.append( ", " );
				else
					coma = true;

				sql.append( getColumnSql( fieldDesc ) );

				if( fieldDesc.primaryKey )
					keys.append( (keysComa++ > 0 ? ", " : "") + "PRIMARY KEY (`" + fieldDesc.name + "`)" );
				else if( fieldDesc.uniqueKey )
					keys.append( (keysComa++ > 0 ? ", " : "") + "UNIQUE KEY `" + fieldDesc.name + "` (`" + fieldDesc.name + "`)" );
				else if( fieldDesc.multipleIndex )
					keys.append( (keysComa++ > 0 ? ", " : "") + "KEY `" + fieldDesc.name + "` (`" + fieldDesc.name + "`)" );
			}

			if( keys.length() > 0 )
				sql.append( ", " + keys.toString() );
			sql.append( " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8" );

			sqls.add( sql.toString() );

			for( FieldDescription fieldDesc : desc.fields.values() )
				checkReferences( newTableName, null, fieldDesc, sqlRefs );
		}

		// removed tables
		for( String removedTableName : removedTables )
		{
			if( fDoDelete )
			{
				Trace.it( "Removed table " + removedTableName );
				sqls.add( "DROP TABLE `" + removedTableName + "`" );
			}
			else
			{
				Trace.it( "IGNORED - Removed table " + removedTableName );
			}
		}

		// maybe modified tables
		for( String maybeModifiedTable : maybeModifiedTables )
		{
			TableDescription tgt = targetDbDesc.tables.get( maybeModifiedTable );
			TableDescription cur = currentDbDesc.tables.get( maybeModifiedTable );

			assert tgt.name.equalsIgnoreCase( cur.name );

			SetComparison fieldsComparison = new SetComparison();
			fieldsComparison.compareSets( cur.fields.keySet(), tgt.fields.keySet() );

			Trace.it( "Comparing tables " + maybeModifiedTable );

			// new fields
			for( String newFieldName : fieldsComparison.newItems )
			{
				Trace.it( "New field " + newFieldName );

				FieldDescription newField = tgt.fields.get( newFieldName );
				sqls.add( "ALTER TABLE `" + maybeModifiedTable + "` ADD " + getColumnSql( newField ) );
			}

			// removed fields
			for( String removedFieldName : fieldsComparison.removedItems )
			{
				if( fDoDelete )
				{
					Trace.it( "Removed field " + removedFieldName );

					sqls.add( "ALTER TABLE `" + maybeModifiedTable + "` DROP `" + removedFieldName + "` " );
				}
				else
				{
					Trace.it( "IGNORED - Removed field " + removedFieldName );
				}
			}

			// Maybe modified fields
			for( String fieldName : fieldsComparison.maybeModifiedItems )
			{
				FieldDescription curField = cur.fields.get( fieldName );
				FieldDescription tgtField = tgt.fields.get( fieldName );

				// modified references
				checkReferences( maybeModifiedTable, curField, tgtField, sqlRefs );

				// modified comment
				String curComment = curField.comment != null ? curField.comment : "";
				String tgtComment = tgtField.comment != null ? tgtField.comment : "";
				if( !tgtComment.equals( curComment ) )
				{
					Trace.it( "Modified comment to " + tgtComment );
					sqls.add( "ALTER TABLE `" + maybeModifiedTable + "` CHANGE `" + fieldName + "` " + getColumnSql( tgtField ) );
				}
			}
		}

		// merge sqls and sqlRefs
		sqls.addAll( sqlRefs );

		// String trace = Trace.peek();
		// System.out.print( trace );

		Trace.pop();

		return sqls;
	}

	private void checkReferences( String tableName, FieldDescription curField, FieldDescription tgtField, ArrayList<String> sqls )
	{
		ArrayList<FieldReference> curRefs;
		if( curField != null )
			curRefs = curField.fieldReferences;
		else
			curRefs = new ArrayList<FieldReference>();

		ArrayList<FieldReference> tgtRefs = tgtField.fieldReferences;

		for( FieldReference ref : curRefs )
		{
			// if a similar ref is not found in $tgtRefs, that is this reference must be deleted
			if( !tgtField.hasReference( ref.table, ref.field ) )
			{
				// echo "Deleted reference, field $fieldName references ".$ref['table'].".".$ref['field']."<br/>";

				for( String constraintName : ref.constraintNames )
					sqls.add( "ALTER TABLE `" + tableName + "` DROP FOREIGN KEY `" + constraintName + "`" );
			}
		}

		for( FieldReference ref : tgtRefs )
		{
			// if a similar ref is not found in $curRefs, that is this reference is a new to be created
			if( curField == null || !curField.hasReference( ref.table, ref.field ) )
			{
				// echo "New reference, field $fieldName references ".$ref['table'].".".$ref['field']."<br/>";

				sqls.add( "ALTER TABLE `" + tableName + "` ADD FOREIGN KEY (`" + tgtField.name + "`) REFERENCES `" + ref.table + "`(`" + ref.field + "`)" );
			}
		}
	}

	/* SQL formatting helpers */
	private String getColumnSql( FieldDescription fieldDesc )
	{
		String defaultValue = "";
		if( fieldDesc.defaultValue != null )
		{
			defaultValue = fieldDesc.defaultValue;

			if( defaultValue.equalsIgnoreCase( "CURRENT_TIMESTAMP" ) )
				defaultValue = "DEFAULT CURRENT_TIMESTAMP";
			else if( defaultValue.equalsIgnoreCase( "NULL" ) )
				defaultValue = "DEFAULT NULL";
			else
				defaultValue = "DEFAULT '" + defaultValue + "'";
		}

		String comment = "";
		if( fieldDesc.comment != null )
		{
			// TODO : mysql_real_escape_string() sur le commentaire
			comment = "COMMENT '" + fieldDesc.comment + "'";
		}

		return "`" + fieldDesc.name + "` " + fieldDesc.type + " " + (fieldDesc.canNull == "NO" ? "NOT NULL" : "") + " " + defaultValue + " " + fieldDesc.extra
				+ " " + comment;
	}
}
