package app.servlets;

import app.entities.User;
import app.model.ModelUsers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/registration.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ModelUsers list = ModelUsers.getInstance();
        String message = null;
        int id = list.list().size();
        boolean flag = true;
        for (User u : list.list()) {
            if (u.getLogin().equals(login)) {
                flag = false;
            }
        }
        if (flag) {
            User user = new User(login, password);
            id++;
            user.setId(id);
            list.add(user);
            message = " added!";
            req.setAttribute("message", message);
        } else {
            message = " already exists, try again...";
            req.setAttribute("message", message);
        }
        req.setAttribute("users", login);
        doGet(req, resp);
    }
}
