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

    public static void putAttempts(Connection conn,int id, int attempt) throws SQLException {
        String sql = "INSERT INTO games (id,attempts) VALUES (?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.setInt(2, attempt);
        pstm.executeUpdate();

    }

    public static List<Integer> getAttempts(Connection conn, int id) throws SQLException {
        String sql = "SELECT g.attempts,gl.id_user,gl.game_id FROM games g\n" +
                "  JOIN game_logs gl ON gl.game_id = g.id\n" +
                "  JOIN users u ON gl.id_user = u.id WHERE gl.id_user = ?\n" +
                "GROUP BY g.attempts,gl.id_user,gl.game_id";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        List<Integer> list = new ArrayList<>();
        while (rs.next()) {
            Integer numberAttempt = rs.getInt("attempts");
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


    public static int countAverage(Connection con, int idUser,int idGame) throws SQLException {
        String sql = "WITH a AS (SELECT COUNT(g.attempts) AS COUNT,sum(g.attempts)\n" +
                "AS summ FROM games g JOIN game_logs gl ON gl.game_id = g.id JOIN users u ON gl.id_user = u.id WHERE u.id = ? GROUP BY ?)\n" +
                " SELECT  avg(summ/count) AS aver FROM a;";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, idUser);
        pstm.setInt(2, idGame);
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


    public static int findGame(Connection con, int idUser) throws SQLException {
        String sql = "SELECT gl.game_id FROM game_logs gl WHERE gl.id_user=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, idUser);
        ResultSet rs = pstm.executeQuery();
        int gameId = 0;
        if (rs.next()) {
            gameId = rs.getInt("game_id");
        }
        return gameId;
    }

    public static void updateGame(Connection con, int gameId, int userId, String resultStr) throws SQLException {
        String sql = "INSERT INTO game_logs(game_id,id_user,data) VALUES (?,?,?);";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, gameId);
        pstm.setInt(2, userId);
        pstm.setString(3, resultStr);
        pstm.executeUpdate();
    }

    public static List<String> getData(Connection con, int gameId, int id) throws SQLException {
        String sql = "SELECT data FROM game_logs  WHERE id_user = ? AND game_id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,id);
        pstm.setInt(2,gameId);
        ResultSet rs = pstm.executeQuery();
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            String data = rs.getString("data");
            list.add(data);
        }
        return list;

    }

    public static int getNewIdGame(Connection con) throws SQLException {
        String sql ="SELECT nextval('games_id_seq') AS newID";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int GameId = 0;
        if (rs.next()){
            GameId = rs.getInt("newID");
        }
        return GameId;
    }

}
