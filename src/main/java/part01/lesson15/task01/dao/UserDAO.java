package part01.lesson15.task01.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part01.lesson15.task01.ConnectorDB;
import part01.lesson15.task01.domain.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Класс для работы объекта Role с БД
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class UserDAO {
    private static Logger log = LoggerFactory.getLogger(UserDAO.class);

    private static Connection connection;
    private static CallableStatement cstmt;
    private static ResultSet cursor;

    private static final String SQL_INSERT_USER = "INSERT INTO user_ (id, name, birthday, login_id, city, email, description) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_USER_ROLE = "INSERT INTO user_role (id, user_id, role_id) VALUES (?,?,?)";

    static {
        try {
            connection = ConnectorDB.getConnection();
        } catch (SQLException e) {
            log.error("Ошибка при подключении к БД: " + e.getMessage());
        }
    }

    public static boolean insert(User user){
        boolean result = insert(user, connection);
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;

    }

    public static boolean insert(User user, Connection connection) {
        try {
            cstmt = connection.prepareCall(SQL_INSERT_USER);
            cstmt.setObject(1, user.getId());
            cstmt.setString(2, user.getName());
            cstmt.setDate(3, user.getBirthday());
            cstmt.setObject(4, user.getLogin_id());
            cstmt.setString(5, user.getCity());
            cstmt.setString(6, user.getEmail());
            cstmt.setString(7, user.getDescription());
            cstmt.executeUpdate();

            if(user.getRole()!=null){
                cstmt = connection.prepareCall(SQL_INSERT_USER_ROLE);
                cstmt.setObject(1, UUID.randomUUID());
                cstmt.setObject(2, user.getId());
                cstmt.setObject(3, user.getRole().getId());
                cstmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        return false;
    }

}
