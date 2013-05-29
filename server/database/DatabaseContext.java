package com.hexa.server.database;

import java.sql.Connection;

import com.hexa.server.qpath.Database;
import com.hexa.server.qpath.DatabaseHelper;
import com.hexa.server.qpath.QPath;

public class DatabaseContext
{
	public Database db;
	public DatabaseHelper dbh;
	public QPath qpath;

	public void init( Connection connection )
	{
		db = new Database();
		db.init( connection );

		dbh = new DatabaseHelper( db );

		qpath = new QPath();
		qpath.init( db, dbh );
	}

	public void term()
	{
		if( qpath != null )
		{
			qpath.term();
			qpath = null;
		}

		if( dbh != null )
		{
			dbh.term();
			dbh = null;
		}

		if( db != null )
		{
			db.term();
			db = null;
		}
	}
}