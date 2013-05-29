package com.hexa.server.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.hexa.server.qpath.DatabaseDescription;
import com.hexa.server.qpath.DatabaseDescriptionInspector;
import com.hexa.server.tools.Logger;

public class DatabaseSchema
{
	private static Logger log = Logger.getLogger( DatabaseSchema.class );

	public static boolean updateDatabaseSchemaFromFile( File file, DatabaseContext ctx, boolean fDoDelete, boolean fReallyExecute )
	{
		log.log( "Updating database schema from file " + file.getAbsolutePath() );

		try
		{
			StringBuilder targetJson = new StringBuilder();
			Scanner scanner = new Scanner( new FileInputStream( file ) );
			try
			{
				while( scanner.hasNextLine() )
					targetJson.append( scanner.nextLine() );
			}
			finally
			{
				scanner.close();
			}

			Gson gson = new Gson();
			DatabaseDescription targetDatabase = gson.fromJson( targetJson.toString(), DatabaseDescription.class );
			if( targetDatabase == null )
			{
				log.err( "Cannot parse " + file.getAbsolutePath() + " to update DB, aborting schema update !" );
				return false;
			}

			DatabaseDescriptionInspector inspector = new DatabaseDescriptionInspector();
			DatabaseDescription dbDesc = inspector.getDatabaseDescription( ctx.db, ctx.dbh );

			ArrayList<String> sqls = inspector.getSqlForUpdateDb( dbDesc, targetDatabase, fDoDelete );
			if( sqls != null && !sqls.isEmpty() )
			{
				log.log( " ... Needed to update database schema:" );
				if( fReallyExecute )
				{
					for( String sql : sqls )
					{
						log.log( " ...  Executing " + sql );
						ctx.db.sqlUpdate( sql );
						log.log( " --- ok" );
					}
				}
				else
				{
					for( String sql : sqls )
						log.log( sql );
				}
			}

			log.log( " ... Your database schema is up to date" );

			return true;
		}
		catch( FileNotFoundException exception )
		{
			log.log( " ... " + file.getAbsolutePath() + " does not exist to update the database schema !" );

			return false;
		}
	}

	public static boolean dumpDatabaseSchemaToFile( DatabaseContext ctx, File file )
	{
		log.log( "Dumping database schema to file " + file.getAbsolutePath() );

		DatabaseDescriptionInspector inspector = new DatabaseDescriptionInspector();
		DatabaseDescription dbDesc = inspector.getDatabaseDescription( ctx.db, ctx.dbh );

		Gson gson = new Gson();
		String json = gson.toJson( dbDesc );
		try
		{
			PrintWriter writer;
			writer = new PrintWriter( file );
			writer.print( json );
			writer.close();

			return true;
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();

			return false;
		}
	}
}
