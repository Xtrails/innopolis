package part01.lesson15.task01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part01.lesson15.task01.dao.RoleDAO;
import part01.lesson15.task01.dao.UserDAO;
import part01.lesson15.task01.domain.Role;
import part01.lesson15.task01.domain.User;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Задание 15.
 *
 * 1) Спроектировать базу
 *      - Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 *      - Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 *      - Таблица USER_ROLE содержит поля id, user_id, role_id
 * Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
 *
 * 2) Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 *      a) Используя параметризированный запрос
 *      b) Используя batch процесс
 *
 * 3) Сделать параметризированную выборку по login_ID и name одновременно
 *
 * 4) Перевести connection в ручное управление транзакциями
 *      a) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 *         между sql операциями установить логическую точку сохранения(SAVEPOINT)
 *      б) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 *         между sql операциями установить точку сохранения (SAVEPOINT A), намеренно ввести некорректные данные
 *         на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        Role role = new Role("new_role");
//        RoleDAO.insert(role);

        List<Role> roles = RoleDAO.getRoles();

        User user = new User(
                "name",
                new Date(321453L),
                UUID.randomUUID(),
                "Ижевск",
                "anisimov.prod@gmail.com",
                "Заметка",
                roles.get(0)
        );

        UserDAO.insert(user);
    }
}
