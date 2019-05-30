package part01.lesson15.task01;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection cn = ConnectorDB.getConnection();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
