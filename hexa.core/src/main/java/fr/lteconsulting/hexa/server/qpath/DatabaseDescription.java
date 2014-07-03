package fr.lteconsulting.hexa.server.qpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DatabaseDescription
{
	public class TableDescription
	{
		public String name;

		HashMap<String, FieldDescription> fields = new HashMap<String, FieldDescription>();

		HashMap<String, List<String>> unicityConstraints = new HashMap<String, List<String>>();

		public TableDescription( String name )
		{
			this.name = name;
		}

		public FieldDescription addField( String name, String type, String canNull, String defaultValue, String extra, String key )
		{
			FieldDescription fieldDesc = new FieldDescription( name, type, canNull, defaultValue, extra, key );
			fields.put( fieldDesc.name, fieldDesc );
			return fieldDesc;
		}

		public List<String> addUnicityConstraint( String name )
		{
			List<String> columnNames = new ArrayList<String>();

			unicityConstraints.put( name, columnNames );

			return columnNames;
		}
	}

	public class FieldDescription
	{
		public String name;
		public String type;
		public String canNull;
		public String defaultValue;
		public String extra;
		public String key;

		public boolean primaryKey;
		public boolean uniqueKey;
		public boolean multipleIndex;

		public String comment;

		ArrayList<FieldReference> fieldReferences = new ArrayList<FieldReference>();

		public FieldDescription( String name, String type, String canNull, String defaultValue, String extra, String key )
		{
			this.name = name;
			this.type = type;
			this.canNull = canNull;
			this.defaultValue = defaultValue;
			this.extra = extra;
			this.key = key;

			if( key.equalsIgnoreCase( "PRI" ) )
				primaryKey = true;
			else if( key.equalsIgnoreCase( "UNI" ) )
				uniqueKey = true;
			else if( key.equalsIgnoreCase( "MUL" ) )
				multipleIndex = true;
		}

		public void addReferenceField( String table, String field, String constraintName )
		{
			FieldReference referenceToAdd = new FieldReference( table, field );

			// skip if already declared
			for( FieldReference ref : fieldReferences )
			{
				if( ref.equals( referenceToAdd ) )
				{
					ref.addConstraintName( constraintName );
					return;
				}
			}

			referenceToAdd.addConstraintName( constraintName );

			fieldReferences.add( referenceToAdd );
		}

		public boolean hasReference( String table, String field )
		{
			for( FieldReference ref : fieldReferences )
			{
				if( ref.table.equalsIgnoreCase( table ) && ref.field.equalsIgnoreCase( field ) )
					return true;
			}

			return false;
		}
	}

	public class FieldReference
	{
		public String table;
		public String field;

		HashSet<String> constraintNames = new HashSet<String>();

		public FieldReference( String table, String field )
		{
			this.table = table;
			this.field = field;
		}

		public void addConstraintName( String constraintName )
		{
			constraintNames.add( constraintName );
		}

		public boolean equals( FieldReference other )
		{
			return table.equalsIgnoreCase( other.table ) && field.equalsIgnoreCase( other.field );
		}
	}

	public HashMap<String, TableDescription> tables = new HashMap<String, TableDescription>();

	public String name;

	public DatabaseDescription( String name )
	{
		this.name = name;
	}

	public TableDescription addTable( String tableName )
	{
		TableDescription tableDesc = new TableDescription( tableName );
		tables.put( tableDesc.name, tableDesc );
		return tableDesc;
	}
}
