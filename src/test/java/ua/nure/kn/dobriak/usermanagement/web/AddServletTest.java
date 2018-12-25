package ua.nure.kn.dobriak.usermanagement.web;

import ua.nure.kn.dobriak.usermanagement.User;

import java.time.LocalDate;

public class AddServletTest extends MockServletTestCase {
    private Integer userId;

    protected void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {
        LocalDate date = LocalDate.of(1999, 9, 23);
        User user = new User("Valeria", "Dobriak", date);
        userId = Math.toIntExact(getUserManager().create(user));

        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Valeria");
        addRequestParameter("lastName", "Dobriak");
        addRequestParameter("dateOfBirth", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    public void testAddEmptyFirstName() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("lastName", "Dobriak");
        addRequestParameter("dateOfBirth", LocalDate.of(1999, 9, 23).toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyLastName() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Valeria");
        addRequestParameter("dateOfBirth", LocalDate.of(1999, 9, 23).toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDateOfBirth() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Valeria");
        addRequestParameter("lastName", "Dobriak");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDateOfBirthIncorrect() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Valeria");
        addRequestParameter("lastName", "Dobriak");
        addRequestParameter("dateOfBirth", "sadasdghghf");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}
