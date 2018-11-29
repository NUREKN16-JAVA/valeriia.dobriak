package ua.nure.kn.dobriak.usermanagement.db;

import junit.framework.TestCase;
import ua.nure.kn.dobriak.usermanagement.User;

public class HsqldbUserDaoTest extends TestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao (connectionFactory);
		
	}



	public void testCreate() {
		fail("Not yet implemented");
		
		try {
			User user = new User ();
			user.setFirstName("John");
			user.setLastName("Doe");
			user.setDateofBirth(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
		
	}

}
