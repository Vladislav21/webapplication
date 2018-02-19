package app.servlets;

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
import java.util.List;

public class Attempts extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection con = PostgreSQLConnUtils.getPostgreSQLConnection();
            int id = CurrentUser.getId();
            List<Integer> attempts = DBUtils.getAttempts(con, id);
            req.setAttribute("attempts",attempts);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/getAttempts.jsp");
        requestDispatcher.forward(req,resp);
    }
}
