package com.hexa.server.database;

import java.sql.Connection;

public interface DatabaseConnectionFactory
{
	Connection getConnection();
}
