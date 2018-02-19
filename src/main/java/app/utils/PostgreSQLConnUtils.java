package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnUtils {

    public static Connection getPostgreSQLConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/app";
        String userName = "postgres";
        String password = "dkflbckfd211095";
        return getPostgreSQLConnection(jdbcUrl, userName, password);

    }

    public static Connection getPostgreSQLConnection(String jdbcUrl, String userName, String password) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(jdbcUrl, userName, password);
    }
}
