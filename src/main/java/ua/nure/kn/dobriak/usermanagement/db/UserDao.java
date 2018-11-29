package ua.nure.kn.dobriak.usermanagement.db;

import  ua.nure.kn.dobriak.usermanagement.User;

public interface UserDao {
	User create(User user) throws DatabaseException;
	
	void update(User user) throws DatabaseException;
	
	void delete(User user) throws DatabaseException;
	
	User find(Long id) throws DatabaseException;
	
	Collection findAll () throws DatabaseException;
	
	
	
	

}
