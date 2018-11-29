package ua.nure.kn.dobriak.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

	public ConnectionFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		
		String url = "jdbc;hsqldb;file;db/usermanagement";
		String user = "sa";
		String password = "";
		String driver = "org.hsqldb.jdbcDriver";
		try {
			Class.forName(driver);
		} 
		
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
			
		}
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

}
