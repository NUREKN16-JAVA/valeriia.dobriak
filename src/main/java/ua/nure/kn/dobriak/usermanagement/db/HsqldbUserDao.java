package ua.nure.kn.dobriak.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.kn.dobriak.usermanagement.User;;


public class HsqldbUserDao implements UserDao {
	
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname,dateofbirth) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
		
		
	}
	
	
	

	public HsqldbUserDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User create (User user) throws DatabaseException {
		
			try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new  Date (user.getDateofBirthd() .getTime()));
			int n = statement.executeUpdate(); 
			if (n != 1) {
				throw new DatabaseException ("Number of the  inserted rows:" + n);
			}
				CallableStatement callableStatement = connection.prepareCall ("call IDENTITY ()");
				ResultSet keys = callableStatement.executeQuery ();
				if (keys.next()) {
					user.setId(new Long(keys.getLong(1)));
				}
				keys.close();
				callableStatement.close();
				statement.close();
				connection.close();
				return user;
			} catch (DatabaseException e) {
				throw e;
			
			} catch (SQLException e) {
				throw new DatabaseException (e);
			}	
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
