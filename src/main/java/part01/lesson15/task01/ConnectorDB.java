package part01.lesson15.task01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Класс для работы с БД
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class ConnectorDB {

    private static Logger log = LoggerFactory.getLogger(ConnectorDB.class);

    private static PropertyLoader pl = PropertyLoader.getInstance();
    private static String CONNECTION_URL = pl.properties.getProperty("db.url");
    private static String CONNECTION_USER = pl.properties.getProperty("db.user");
    private static String CONNECTION_PASSSWORD = pl.properties.getProperty("db.password");
    private static String CONNECTION_DRIVER = pl.properties.getProperty("db.driver");

    /**
     * Получить соединение к БД
     * @return - соединение к БД
     * @throws SQLException
     */
    public static Connection getConnection() {
        try {
            Class.forName(CONNECTION_DRIVER);
            Connection connection = DriverManager.getConnection(
                    CONNECTION_URL,
                    CONNECTION_USER,
                    CONNECTION_PASSSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            log.error("Ошибка при подключении к БД: " + e.getMessage());
        } catch (SQLException e) {
            log.error("Ошибка при подключении к БД: " + e.getMessage());
        }
        return null;
    }

    public static Connection getConnection(Connection connection){
        try {
            if(connection == null || connection.isClosed()){
                return getConnection();
            }
        } catch (SQLException e) {
            log.error("Ошибка при подключении к БД: " + e.getMessage());
        }
        return connection;
    }
}
