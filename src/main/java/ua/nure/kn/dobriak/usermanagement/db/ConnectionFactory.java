package ua.nure.kn.dobriak.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {

	Connection createConnection() throws DatabaseException;
	
}
