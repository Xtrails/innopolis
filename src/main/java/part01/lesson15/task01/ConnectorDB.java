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

    private Logger log = LoggerFactory.getLogger(ConnectorDB.class);

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
    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(CONNECTION_DRIVER);
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
        statement.addBatch("INSERT INTO role_ VALUES (2,'NEW_ROLE2')");
        statement.executeBatch();               //Если необходимо выполнить несколько команд за раз

        statement.execute("select id from role_");  //Используется если не известно является ли строка SQL запросом

        int count = statement.executeUpdate("INSERT INTO role_ VALUES (1,'NEW_ROLE_2')");   //UPDATE, INSERT

        //ResultSet
        ResultSet result = statement.executeQuery("select id from role_");  //Используется для извлечения данных (SELECT)
        result.absolute(3);
        result.afterLast();     //Курсор в конец
        result.beforeFirst();   //Курсор перед началом
        result.deleteRow();     //Удалить запись на которой курсор
        ResultSetMetaData metaData = result.getMetaData();
        metaData.getColumnCount();
        result.getRow();
        result.getStatement();
        result.close();
    }

    private void getTransaction(Connection connection){
        try{
            connection.commit();
            //Уровни транзакций
            connection.getTransactionIsolation();
            connection.setSavepoint("a");

        } catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error(ex.getMessage());
            }
            log.error(e.getMessage());
        }
    }

    private void getMetaData(ResultSet rs, Connection connection){
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            metaData.getColumnType(1);
            metaData.getColumnCount();
            metaData.getColumnName(1);
            metaData.getColumnType(1);

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            databaseMetaData.getDatabaseProductName();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
