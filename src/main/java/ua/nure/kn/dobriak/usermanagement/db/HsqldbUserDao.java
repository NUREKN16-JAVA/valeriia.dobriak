package ua.nure.kn.dobriak.usermanagement.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.kn.dobriak.usermanagement.User;;


public class HsqldbUserDao implements UserDao {
	
	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
		
		
	}
	
	
	

	public HsqldbUserDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User create (User user) throws DatabaseException {
		
	
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO users (firstname, lastname,dateofbirth) VALUES (?, ?, ?)");
			
			return null;
		
	}

	@Override
	public void update(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
