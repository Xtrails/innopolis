package part01.lesson15.task01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String CONNECTION_USER = "postgres";
    private static final String CONNECTION_PASSSWORD = "admin";

    public static Connection getConnection() throws SQLException {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    CONNECTION_URL,
                    CONNECTION_USER,
                    CONNECTION_PASSSWORD
            );
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

//        ResourceBundle resource = ResourceBundle.getBundle("org.postgres.Driver");
//        String url = resource.getString(CONNECTION_URL);
//        String user = resource.getString(CONNECTION_USER);
//        String pass = resource.getString(CONNECTION_PASSSWORD);
        return connection;
    }
}
