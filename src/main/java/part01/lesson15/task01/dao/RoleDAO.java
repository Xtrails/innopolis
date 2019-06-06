package part01.lesson15.task01.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part01.lesson15.task01.ConnectorDB;
import part01.lesson15.task01.domain.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс для работы объекта Role с БД
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class RoleDAO {
    private static Logger log = LoggerFactory.getLogger(UserDAO.class);

    private static Connection connection;
    private static CallableStatement cstmt;

    private static final String SQL_INSERT = "INSERT INTO role_ (ID, NAME) VALUES (?,?)";
    private static final String SQL_SELECT_ALL = "select id, name from role_";

    static {
        try {
            connection = ConnectorDB.getConnection();
        } catch (SQLException e) {
            log.error("Ошибка при подключении к БД: " + e.getMessage());
        }
    }

    /**
     * Запись новой роли, с установлением нового соединения к БД
     * @param role - записываемая роль
     * @return - true/false
     */
    public static boolean insert(Role role){
        boolean result = insert(role, connection);
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * Запись новой роли, через существующее соединение к БД
     * @param role - записываемая роль
     * @param connection - соединение с БД
     * @return - true/false
     */
    public static boolean insert(Role role, Connection connection) {
        try {
            cstmt = connection.prepareCall(SQL_INSERT);
            cstmt.setObject(1, role.getId());
            cstmt.setString(2, role.getName());
            cstmt.executeUpdate();
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

    /**
     * Получение списка ролей, с установлением нового соединения к БД
     * @return - список ролей
     */
    public static List<Role> getRoles() {
        try {
            if(connection.isClosed()){
                connection = ConnectorDB.getConnection();
            }
            return getRoles(connection);
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    /**
     * Получение списка ролей, через существующее соединение к БД
     * @param connection - соединение с БД
     * @return - список ролей
     */
    public static List<Role> getRoles(Connection connection) {
        List<Role> roles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);  //Используется для извлечения данных (SELECT)
            while (rs.next()) {
                Role role = new Role();
                role.setId((UUID) rs.getObject("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return roles;
    }

}
