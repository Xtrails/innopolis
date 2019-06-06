package part01.lesson15.task01;

import org.slf4j.Logger;

import java.sql.*;

public class ConnectorDB {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String CONNECTION_USER = "postgres";
    private static final String CONNECTION_PASSSWORD = "admin";


    public static Connection getConnection() throws SQLException {

        PropertyLoader propertyLoader = PropertyLoader.getInstance();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }

        Connection connection = DriverManager.getConnection(
                CONNECTION_URL,
                CONNECTION_USER,
                CONNECTION_PASSSWORD);
        return connection;
    }

    private void getDriver(String sql){
        DriverManager.getDrivers();
//        DriverManager.registerDriver();
    }

    private void getUpDATE(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.addBatch("INSERT INTO role_ VALUES (1,'NEW_ROLE')");

        statement.executeBatch();
        statement.execute("select id from role_");
        ResultSet result = statement.executeQuery("select id from role_");
        int count = statement.executeUpdate("INSERT INTO role_ VALUES (1,'NEW_ROLE_2')");

        //ResultSet
        result.absolute(3);
        result.afterLast();
        result.beforeFirst();
    }
}
