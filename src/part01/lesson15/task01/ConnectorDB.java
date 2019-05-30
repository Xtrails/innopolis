package part01.lesson15.task01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String CONNECTION_USER = "postgres";
    private static final String CONNECTION_PASSSWORD = "admin";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                CONNECTION_URL,
                CONNECTION_USER,
                CONNECTION_PASSSWORD);
        return connection;
    }
}
