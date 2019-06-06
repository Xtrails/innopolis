package part01.lesson15.task01.domain;

import java.sql.Date;
import java.util.UUID;

/**
 * Модель таблицы user_
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class User {
    private UUID id;
    private String name;
    private Date birthday;
    private UUID login_id;
    private String city;
    private String email;
    private String description;
    private Role role;

    public User() {
    }

    public User(String name, Date birthday, UUID login_id, String city, String email, String description, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.birthday = birthday;
        this.login_id = login_id;
        this.city = city;
        this.email = email;
        this.description = description;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UUID getLogin_id() {
        return login_id;
    }

    public void setLogin_id(UUID login_id) {
        this.login_id = login_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
