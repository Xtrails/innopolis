package part01.lesson15.task01.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part01.lesson15.task01.ConnectorDB;
import part01.lesson15.task01.domain.Role;
import part01.lesson15.task01.domain.User;

import java.sql.*;
import java.util.*;

/**
 * Класс для работы объекта Role с БД
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class UserDAO {

    private static Logger log = LoggerFactory.getLogger(UserDAO.class);
    private static Connection connection = ConnectorDB.getConnection();
    private static CallableStatement cstmt;
    private static final String SQL_INSERT_USER = "INSERT INTO user_ (id, name, birthday, login_id, city, email, description) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_USER_ROLE = "INSERT INTO user_role (id, user_id, role_id) VALUES (?,?,?)";


    /**
     * Запись нового пользователя, с установлением нового соединения к БД
     * @param user - записываемый пользователь
     * @return - true/false
     */
    public static boolean insert(User user) {
        connection = ConnectorDB.getConnection(connection);
        boolean result = false;
        try {
            result = insert(user, connection);
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;

    }

    /**
     * Запись нового пользователя, через существующее соединение к БД
     * @param connection - существующее соединение к БД
     * @param user - записываемый пользователь
     * @return - true/false
     */
    public static boolean insert(User user, Connection connection) throws SQLException {
        connection = ConnectorDB.getConnection(connection);
        cstmt = connection.prepareCall(SQL_INSERT_USER);
        cstmt.setObject(1, user.getId());
        cstmt.setString(2, user.getName());
        cstmt.setDate(3, user.getBirthday());
        cstmt.setObject(4, user.getLogin_id());
        cstmt.setString(5, user.getCity());
        cstmt.setString(6, user.getEmail());
        cstmt.setString(7, user.getDescription());
        cstmt.executeUpdate();

        if (user.getRole() != null && user.getRole().size() > 0) {
            for (Role role : user.getRole()) {
                cstmt = connection.prepareCall(SQL_INSERT_USER_ROLE);
                cstmt.setObject(1, UUID.randomUUID());
                cstmt.setObject(2, user.getId());
                cstmt.setObject(3, role.getId());
                cstmt.executeUpdate();
            }
        }
        return true;
    }

    /**
     * Запись нового пользователя, с установлением нового соединения к БД
     * @param user - записываемый пользователь
     * @return - true/false
     */
    public static boolean insertBatch(User user) {
        connection = ConnectorDB.getConnection(connection);
        boolean result = false;
        try {
            Statement statement = connection.createStatement();
            result = insertBatch(user, statement);
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * Запись нового пользователя, через существующее соединение к БД
     * @param statement - существующее соединение к БД
     * @param user - записываемый пользователь
     * @return - true/false
     */
    private static boolean insertBatch(User user, Statement statement) {
        connection = ConnectorDB.getConnection(connection);
        try {
            statement.addBatch(
                    "INSERT INTO user_ (id, name, birthday, login_id, city, email, description) " +
                            "VALUES (" +
                            "\'" + user.getId() + "\'," +
                            "\'" + user.getName() + "\'," +
                            "\'" + user.getBirthday() + "\'," +
                            "\'" + user.getLogin_id() + "\'," +
                            "\'" + user.getCity() + "\'," +
                            "\'" + user.getEmail() + "\'," +
                            "\'" + user.getDescription() + "\'" +
                            ")"
            );
            if (user.getRole() != null && user.getRole().size() > 0) {
                for (Role role : user.getRole()) {
                    statement.addBatch("INSERT INTO user_role VALUES (" +
                            "\'" + UUID.randomUUID() + "\'," +
                            "\'" + user.getId() + "\'," +
                            "\'" + role.getId() + "\')"
                    );
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Ошибка при закрытии подключения к БД: " + e.getMessage());
            }
        }
        return false;
    }


    /**
     * Получить список пользователей с ligin_id и name
     * @param login_id
     * @param name - имя пользователя
     * @return - список пользователей
     */
    public static Set<User> getByLoginIdAndName(UUID login_id, String name) {
        connection = ConnectorDB.getConnection(connection);
        try {
            Set<User> users = new HashSet<>();
            Map<UUID, List<Role>> roles = new HashMap<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT role_.*, user_.* FROM user_ " +
                    "LEFT JOIN user_role ON user_.id = user_role.user_id " +
                    "LEFT JOIN role_ on user_role.role_id = role_.id " +
                    "WHERE user_.login_id = '" + login_id + "' AND user_.name = '" + name + "'");
            while (rs.next()) {
                Role role = new Role();
                role.setId((UUID) rs.getObject(1));
                role.setName(rs.getString(2));

                User user = new User();
                user.setId((UUID) rs.getObject(3));
                user.setName(rs.getString(4));
                user.setBirthday(rs.getDate(5));
                user.setLogin_id((UUID) rs.getObject(6));
                user.setCity(rs.getString(7));
                user.setEmail(rs.getString(8));
                user.setDescription(rs.getString(9));

                users.add(user);
                if(roles.containsKey(user.getId())){
                    roles.get(user.getId()).add(role);
                } else {
                    List<Role> tmpRoles = new ArrayList<>();
                    tmpRoles.add(role);
                    roles.put(user.getId(),tmpRoles);
                }
            }
            rs.close();
            users.forEach(user -> user.getRole().addAll(roles.get(user.getId())));
            return users;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
