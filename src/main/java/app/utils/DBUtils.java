package app.utils;

import app.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    /**
     * Достать пользователя по login и password
     *
     * @param conn
     * @param login
     * @param password
     * @return User
     * @throws SQLException
     */
    public static User findUser(Connection conn,
                                String login, String password) throws SQLException {

        String sql = "SELECT u.id,u.loggin, u.password FROM users u " //
                + " WHERE u.loggin = ? AND u.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, login);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            User user = new User();
            int id = rs.getInt("id");
            user.setLogin(login);
            user.setPassword(password);
            user.setId(id);
            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String login) throws SQLException {

        String sql = "SELECT u.loggin, u.password FROM users u "//
                + " WHERE u.loggin = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, login);

        ResultSet rs = pstm.executeQuery();


        if (rs.next()) {
            String password = rs.getString("password");
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            return user;
        }
        return null;
    }

    public static void putAttempts(Connection conn, int id, int attempt) throws SQLException {
        String sql = "INSERT INTO attempts (user_id, number_attemp) VALUES (?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.setInt(2, attempt);
        pstm.executeUpdate();

    }

    public static List<Integer> getAttempts(Connection conn, int id) throws SQLException {
        String sql = "SELECT a.number_attemp FROM attempts a WHERE a.user_id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        List<Integer> list = new ArrayList<>();
        while (rs.next()) {

            Integer numberAttempt = rs.getInt("number_attemp");
            list.add(numberAttempt);
        }
        return list;
    }

    public static List<User> getList(Connection conn) throws SQLException {
        String sql = "SELECT u.id, u.loggin, u.number_average_attemps FROM users u ORDER BY u.number_average_attemps";

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            String loggin = rs.getString("loggin");
            Integer attempts = rs.getInt("number_average_attemps");
            Integer id = rs.getInt("id");
            User user = new User();
            user.setId(id);
            user.setLogin(loggin);
            user.setNumberAttempts(attempts);
            list.add(user);
        }
        return list;
    }

    public static void insertUser(Connection conn, String login, String password) throws SQLException {
        String sql = "INSERT INTO users(loggin, password) VALUES (?,?) ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, login);
        pstm.setString(2, password);
        pstm.executeUpdate();
    }


    public static int countAverage(Connection con, int id) throws SQLException {
        String sql = "WITH a AS (SELECT COUNT(number_attemp) AS COUNT,sum(number_attemp) AS summ FROM attempts WHERE user_id = ? GROUP BY user_id)\n" +
                "  SELECT  avg(summ/count) AS aver FROM a;";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        int aver = 0;
        if (rs.next()) {
            aver = rs.getInt("aver");
        }
        return aver;
    }

    public static void setAverage(Connection con, int id, int aver) throws SQLException {
        String sql = "UPDATE users  SET number_average_attemps = ? WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, aver);
        pstm.setInt(2, id);
        pstm.executeUpdate();
    }
}
