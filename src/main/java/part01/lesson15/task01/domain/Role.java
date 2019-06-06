package part01.lesson15.task01.domain;

import java.util.UUID;

/**
 * Модель таблицы role_
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class Role {

    private UUID id;
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
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
}
