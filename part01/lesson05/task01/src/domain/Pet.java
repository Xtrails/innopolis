package domain;

import exception.PetDuplicateException;

import java.util.Objects;
import java.util.UUID;

/** Класс животное.
 *  Есть уникальный идентификационный номер, кличка, хозяин, вес.
 *
 * @version   1.0 26.04.2019
 * @author    Pavel Anisimov
 */

public class Pet implements Comparable<Pet> {

    /** Идентификационный номер */
    private UUID id;

    /** Кличка */
    private String name;

    /** Хозяин */
    private Person owner;

    /** Вес */
    private int weight;

    /** Пустой конструктор */
    public Pet() {
    }

    /**
     * Конструктор
     * @param name - Кличка
     * @param owner - Хозяин
     * @param weight - Вес
     */
    public Pet(String name, Person owner, int weight) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.weight = weight;
    }

    /**
     * Конструктор с UUID
     * @param id - Идентификатор
     * @param name - Кличка
     * @param owner - Хозяин
     * @param weight - Вес
     */
    public Pet(UUID id, String name, Person owner, int weight) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.weight = weight;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "owner=" + owner.toString() +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ",id=" + id +
                '}';
    }

    @Override
    public int compareTo(Pet o) {
        Person owner1 = this.getOwner();
        Person owner2 = o.getOwner();
        String name1 = this.getName();
        String name2 = o.getName();
        Integer weight1 = this.getWeight();
        Integer weight2 = o.getWeight();
        if (!owner1.equals(owner2)) {
            return owner1.compareTo(owner2);
        } else if (name1 != name2) {
            return name1.compareTo(name2);
        } else if (weight2 != weight1) {
            if (weight1 != weight2) {
                if (weight1 < weight2) {
                    return 1;
                } else if (weight1 > weight2) {
                    return -1;
                }
            }
        } else if (owner1.equals(owner2) && name1 == name2 && weight1 == weight2) {
            try {
                throw new PetDuplicateException("При сортировке найдены дуюлирующиеся объекты Pet");
            } catch (PetDuplicateException e) {
                e.getMessage();
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return weight == pet.weight &&
                id.equals(pet.id) &&
                name.equals(pet.name) &&
                owner.equals(pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, weight);
    }
}
