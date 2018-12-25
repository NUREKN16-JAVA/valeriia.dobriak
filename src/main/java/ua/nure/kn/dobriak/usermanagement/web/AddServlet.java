package ua.nure.kn.dobriak.usermanagement.web;

import ua.nure.kn.dobriak.usermanagement.User;
import ua.nure.kn.dobriak.usermanagement.db.DatabaseException;
import ua.nure.kn.dobriak.usermanagement.db.ManageUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends EditServlet {
    protected void processUser(User user) throws DatabaseException {
        ManageUsers manageUser = new ManageUsers();
        manageUser.create(user);
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);

    }
}
