package fr.lteconsulting.hexa.server.qpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.lteconsulting.hexa.server.qpath.DatabaseDescription.FieldDescription;
import fr.lteconsulting.hexa.server.qpath.DatabaseDescription.FieldReference;
import fr.lteconsulting.hexa.server.qpath.DatabaseDescription.TableDescription;
import fr.lteconsulting.hexa.server.tools.Trace;

public class DatabaseDescriptionInspector
{
	DatabaseMySQLDialect dialect = new DatabaseMySQLDialect();

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

				DBResults info = db.sql( "SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='" + dbDesc.name + "' AND TABLE_NAME='" + table + "' AND COLUMN_NAME='" + fieldName + "'" );
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

			// Inspect unicity constraints
			String sql = "select CONSTRAINT_NAME, COLUMN_NAME from information_schema.KEY_COLUMN_USAGE WHERE TABLE_SCHEMA='" + dbDesc.name + "' AND TABLE_NAME='" + table
					+ "' AND REFERENCED_TABLE_NAME IS NULL AND CONSTRAINT_NAME != 'PRIMARY' ORDER BY CONSTRAINT_NAME, ORDINAL_POSITION";
			DBResults constraintsRecords = db.sql( sql );
			int constraintNameColumn = constraintsRecords.getColumnIndex( "CONSTRAINT_NAME" );
			int columnNameColumn = constraintsRecords.getColumnIndex( "COLUMN_NAME" );
			String currentConstraintName = null;
			List<String> currentConstraintColumnNames = null;
			while( constraintsRecords.next() )
			{
				String recordConstraintName = constraintsRecords.getString( constraintNameColumn );
				if( currentConstraintName == null || !currentConstraintName.equalsIgnoreCase( recordConstraintName ) )
				{
					// initialize a new constraint description
					currentConstraintName = recordConstraintName;
					currentConstraintColumnNames = tableDesc.addUnicityConstraint( recordConstraintName );
				}

				// register that referenced column into the constraint
				currentConstraintColumnNames.add( constraintsRecords.getString( columnNameColumn ) );
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
	// currentDB is a kind of a hack : for the deletion of a constraint in the
	// target database, we need to query the db for all the constraint names to
	// delete
	// them
	public ArrayList<String> getSqlForUpdateDb( DatabaseDescription currentDbDesc, DatabaseDescription targetDbDesc, boolean fDoDelete, boolean fTableNameUpperCase )
	{
		Trace.push();
		Trace.it( "Database comparison" );

		ArrayList<String> sqls = new ArrayList<String>();
		ArrayList<String> sqlRefs = new ArrayList<String>();
		ArrayList<String> sqlConstraints = new ArrayList<String>();

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

			String sql = dialect.getSqlForCreateTable( fTableNameUpperCase, newTableName, desc.fields.values() );

			sqls.add( sql );

			for( FieldDescription fieldDesc : desc.fields.values() )
				checkReferences( fTableNameUpperCase, newTableName, null, fieldDesc, sqlRefs );
		}

		// removed tables
		for( String removedTableName : removedTables )
		{
			if( fDoDelete )
			{
				Trace.it( "Removed table " + removedTableName );

				String sql = dialect.getSqlForDropTable( fTableNameUpperCase, removedTableName );

				sqls.add( sql );
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

				String sql = dialect.getSqlForAddColumn( fTableNameUpperCase, maybeModifiedTable, newField );

				sqls.add( sql );
			}

			// removed fields
			for( String removedFieldName : fieldsComparison.removedItems )
			{
				if( fDoDelete )
				{
					Trace.it( "Removed field " + removedFieldName );

					String sql = dialect.getSqlForDropColumn( fTableNameUpperCase, maybeModifiedTable, removedFieldName );

					sqls.add( sql );
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

				// modified field
				String targetColumnSql = dialect.getColumnSql( tgtField );
				String curColumnSql = dialect.getColumnSql( curField );
				if( !targetColumnSql.equalsIgnoreCase( curColumnSql ) )
				{
					Trace.it( "Modified field " + fieldName );

					String sql = dialect.getSqlForChangeColumn( fTableNameUpperCase, maybeModifiedTable, fieldName, tgtField );

					sqls.add( sql );
				}

				// modified references
				checkReferences( fTableNameUpperCase, maybeModifiedTable, curField, tgtField, sqlRefs );
			}

			// unicity constraints
			SetComparison constraintsComparison = new SetComparison();
			constraintsComparison.compareSets( cur.unicityConstraints.keySet(), tgt.unicityConstraints.keySet() );
			// new constraints
			for( String constraintName : constraintsComparison.newItems )
			{
				List<String> constraintFields = tgt.unicityConstraints.get( constraintName );
				String sql = dialect.getSqlForCreateConstraint( fTableNameUpperCase, tgt.name, constraintName, constraintFields );
				if( sql == null )
					continue;

				sqlConstraints.add( sql );
			}
			// removed constraints
			for( String constraintName : constraintsComparison.removedItems )
			{
				String sql = dialect.getSqlForDropIndex( fTableNameUpperCase, tgt.name, constraintName );
				sqlConstraints.add( sql );
			}
			// maybe modified constraints
			for( String constraintName : constraintsComparison.maybeModifiedItems )
			{
				// Test if constraints are equals
				if( areConstraintsIdentical( cur.unicityConstraints.get( constraintName ), tgt.unicityConstraints.get( constraintName ) ) )
					continue;

				// Delete ...
				String sql = dialect.getSqlForDropIndex( fTableNameUpperCase, tgt.name.toUpperCase(), constraintName );
				sqlConstraints.add( sql );

				// ... and recreate
				List<String> constraintFields = tgt.unicityConstraints.get( constraintName );
				sql = dialect.getSqlForCreateConstraint( fTableNameUpperCase, tgt.name, constraintName, constraintFields );

				sqlConstraints.add( sql );
			}
		}

		// merge sqls and sqlRefs and sqlConstraints
		sqls.addAll( sqlRefs );
		sqls.addAll( sqlConstraints );

		Trace.pop();

		return sqls;
	}

	private boolean areConstraintsIdentical( List<String> set1, List<String> set2 )
	{
		if( set1 == null && set2 == null )
			return true;
		if( set1 == null || set2 == null )
			return false;

		for( String entry1 : set1 )
			if( !set2.contains( entry1 ) )
				return false;

		for( String entry2 : set2 )
			if( !set1.contains( entry2 ) )
				return false;

		return true;
	}

	private void checkReferences( boolean fTableNameUpperCase, String tableName, FieldDescription curField, FieldDescription tgtField, ArrayList<String> sqls )
	{
		ArrayList<FieldReference> curRefs;
		if( curField != null )
			curRefs = curField.fieldReferences;
		else
			curRefs = new ArrayList<FieldReference>();

		ArrayList<FieldReference> tgtRefs = tgtField.fieldReferences;

		for( FieldReference ref : curRefs )
		{
			// if a similar ref is not found in $tgtRefs, that is this reference
			// must be deleted
			if( !tgtField.hasReference( ref.table, ref.field ) )
			{
				for( String constraintName : ref.constraintNames )
				{
					String sql = dialect.getSqlForDropForeignKey( fTableNameUpperCase, tableName, constraintName );
					sqls.add( sql );
				}
			}
		}

		for( FieldReference ref : tgtRefs )
		{
			// if a similar ref is not found in $curRefs, that is this reference
			// is a new to be created
			if( curField == null || !curField.hasReference( ref.table, ref.field ) )
			{
				String sql = dialect.getSqlForCreateForeignKey( fTableNameUpperCase, tableName, tgtField.name, ref.table, ref.field );
				sqls.add( sql );
			}
		}
	}
}
