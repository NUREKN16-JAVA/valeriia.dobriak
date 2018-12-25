package ua.nure.kn.dobriak.usermanagement.web;

import ua.nure.kn.dobriak.usermanagement.User;
import ua.nure.kn.dobriak.usermanagement.db.ManageUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EditServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            ok(req, resp);
        } else if (req.getParameter("cancelButton") != null) {
            cancel(req, resp);
        } else {
            showPage(req, resp);
        }
    }

    private void ok(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = getUser(req);
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            showPage(req, resp);
        }
        processUser(user.getId(), user);
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    private void cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse.jsp").forward(req, resp);
    }

    private User getUser(HttpServletRequest req) throws ValidationException {
        User user = new User();
        String idStr = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateOfBirth = req.getParameter("dateOfBirth");

        if (firstName == null) {
            throw new ValidationException("First Name could not be Empty");
        }

        if (lastName == null) {
            throw new ValidationException("Last Name could not be Empty");
        }

        if (dateOfBirth == null) {
            throw new ValidationException("Date of Birth could not be Empty");
        }

        if (idStr != null) {
            user.setId(new Integer(idStr));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            user.setDateOfBirth(LocalDate.parse(dateOfBirth));
        } catch(DateTimeParseException e) {
            throw new ValidationException("Date Format is incorrect");
        }
        return null;
    }

    protected void processUser(Integer id, User user) {
        ManageUsers userManager = new ManageUsers();
        userManager.update(id, user);
    }
}
