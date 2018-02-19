package app.servlets;

import app.entities.User;
import app.model.CurrentUser;
import app.utils.DBUtils;
import app.utils.PostgreSQLConnUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
        String message = null;
        String next = null;
        try {
            Connection con = PostgreSQLConnUtils.getPostgreSQLConnection();
            User user = DBUtils.findUser(con, login, password);
            if (user != null && user.getLogin().equals(login) && user.getPassword().equals(password)) {
                CurrentUser.setId(user.getId());
                next = "next page";
                req.setAttribute("next",next);
            } else {
                message = "User is not exist";
                req.setAttribute("message", message);
            }
            con.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}
