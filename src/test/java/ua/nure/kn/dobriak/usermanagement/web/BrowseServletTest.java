package ua.nure.kn.dobriak.usermanagement.web;

import ua.nure.kn.dobriak.usermanagement.User;
import ua.nure.kn.dobriak.usermanagement.db.DatabaseException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User user = new User("Valeria", "Dobriak", LocalDate.of(1999, 9, 23));
        List list = Collections.singletonList(user);
        try {
            list = getUserManager().findAll();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }

    public void testEdit() {
        User user = new User("Valeria", "Dobriak", LocalDate.of(1999, 9, 23));
        Integer userId = getUserManager().create(user);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", userId.toString());
        doPost();
        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", user);
        assertSame(user, userInSession);

    }
}
