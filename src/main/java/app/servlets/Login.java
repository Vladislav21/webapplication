package app.servlets;

import app.entities.User;
import app.model.ModelUsers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ModelUsers list = ModelUsers.getInstance();
        boolean valid = false;
        String message = null;
        String next = null;
        for (User user : list.list()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                valid = true;
            }
        }
        if (valid) {
            next = "next page";
            req.setAttribute("next",next);
            doGet(req, resp);
        } else {
            message = "User is not exist";
            req.setAttribute("message", message);
            doGet(req, resp);
        }
    }
}
