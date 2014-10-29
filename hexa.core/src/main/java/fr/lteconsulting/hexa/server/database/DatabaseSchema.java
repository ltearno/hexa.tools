package fr.lteconsulting.hexa.server.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;

import com.google.gson.Gson;

import fr.lteconsulting.hexa.server.qpath.DatabaseDescription;
import fr.lteconsulting.hexa.server.qpath.DatabaseDescriptionInspector;
import fr.lteconsulting.hexa.server.tools.LoggerFactory;

public class DatabaseSchema
{
	private final static Logger log = LoggerFactory.getLogger();

	/**
	 * Updates a database schema with the schema stored in the given file.<br/>
	 * Several options are available.<br/>
	 * Returns the SQL statements that are to be executed to update the db
	 * 
	 * @param file File with JSON information on the database schema
	 * @param ctx Database context. The database referenced by this context is updated.
	 * @param fDoDelete Tells if the drop colomn statements are generated or not
	 * @param fReallyExecute Tells if the SQL statements are executed or not
	 * @return The list of SQL statements needed to update the database schema
	 */
	public static ArrayList<String> updateDatabaseSchemaFromFile( File file, DatabaseContext ctx, boolean fDoDelete, boolean fReallyExecute )
	{
		log.info( "Updating database schema from file " + file.getAbsolutePath() );

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
				log.error( "Cannot parse " + file.getAbsolutePath() + " to update DB, aborting schema update !" );
				return null;
			}

			DatabaseDescriptionInspector inspector = new DatabaseDescriptionInspector();
			DatabaseDescription dbDesc = inspector.getDatabaseDescription( ctx.db, ctx.dbh );

			ArrayList<String> sqls = inspector.getSqlForUpdateDb( dbDesc, targetDatabase, fDoDelete, true/*
																										 * table
																										 * upper
																										 * case
																										 */);
			if( sqls != null && !sqls.isEmpty() )
			{
				log.info( " ... Needed to update database schema:" );
				if( fReallyExecute )
				{
					for( String sql : sqls )
					{
						log.info( " ...  Executing " + sql );
						ctx.db.sqlUpdate( sql );
						log.info( " --- ok" );
					}
				}
				else
				{
					for( String sql : sqls )
						log.info( sql );
				}
			}

			log.info( " ... Your database schema is up to date" );

			return sqls;
		}
		catch( FileNotFoundException exception )
		{
			log.info( " ... " + file.getAbsolutePath() + " does not exist to update the database schema !" );

			return null;
		}
	}

	/**
	 * Writes the current database schema to a file, in JSON format.
	 * This file can be used to update another database.
	 * 
	 * @param ctx Database context
	 * @param file File to be written
	 * @return <code>true</code> if successful
	 */
	public static boolean dumpDatabaseSchemaToFile( DatabaseContext ctx, File file )
	{
		log.info( "Dumping database schema to file " + file.getAbsolutePath() );

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
