package ua.nure.kn.dobriak.usermanagement.web;

import ua.nure.kn.dobriak.usermanagement.User;

import java.time.LocalDate;

public class EditServletTest extends MockServletTestCase {
    private Integer userId;

    protected void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit() {
        LocalDate date = LocalDate.of(1999, 9, 23);
        User user = new User("Vladimir", "Kuchinskiy", date);
        userId = Math.toIntExact(getUserManager().create(user));

        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Vladimir");
        addRequestParameter("lastName", "Kuchinskiy");
        addRequestParameter("dateOfBirth", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    public void testEditEmptyFirstName() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("lastName", "Kuchinskiy");
        addRequestParameter("dateOfBirth", LocalDate.of(1999, 9, 23).toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyLastName() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Vladimir");
        addRequestParameter("dateOfBirth", LocalDate.of(1999, 9, 23).toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyDateOfBirth() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Vladimir");
        addRequestParameter("lastName", "Kuchinskiy");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyDateOfBirthIncorrect() {
        addRequestParameter("id", userId.toString());
        addRequestParameter("firstName", "Vladimir");
        addRequestParameter("lastName", "Kuchinskiy");
        addRequestParameter("dateOfBirth", "sadasdghghf");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}
