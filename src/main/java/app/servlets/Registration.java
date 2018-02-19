package app.servlets;

import app.entities.User;
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
        String message = null;
        try {
            Connection con = PostgreSQLConnUtils.getPostgreSQLConnection();
            User user = DBUtils.findUser(con, login);
            if (user == null) {
                DBUtils.insertUser(con, login, password);
                message = " added!";
                req.setAttribute("message", message);
            } else {
                message = " already exists, try again...";
                req.setAttribute("message", message);
            }
            con.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", login);
        doGet(req, resp);
    }
}
