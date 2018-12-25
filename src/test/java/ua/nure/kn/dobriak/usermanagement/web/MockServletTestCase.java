package ua.nure.kn.dobriak.usermanagement.web;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import ua.nure.kn.dobriak.usermanagement.db.ManageUsers;


public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {
    private ManageUsers userManager;

    protected void setUp() throws Exception {
        super.setUp();
        userManager = new ManageUsers();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected ManageUsers getUserManager() {
        return userManager;
    }
}
