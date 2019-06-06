package part01.lesson05.task01.domain;

import java.util.Objects;

/**
 * Класс Person характеризуется полями
 * age (возраст, целое число 0-100),
 * sex (пол – объект класса domain.Sex со строковыми константами внутри MAN, WOMAN),
 * name (имя - строка).
 *
 * @version   1.0 26.04.2019
 * @author    Pavel Anisimov
 */
public class Person implements Comparable<Person>{

    /** Возраст, целое число 0-100 */
    private int age;

    /** Пол – объект класса domain.Sex со строковыми константами внутри MAN, WOMAN */
    private Sex sex;

    /** Имя - строка */
    private String name;

    public Person() {
    }

    /**
     * Конструктор
     * @param age - возраст
     * @param sex - пол
     * @param name - имя
     */
    public Person(int age, Sex sex, String name) {
        if (age >= 0 && age <= 100)
            this.age = age;
        this.sex = sex;
        this.name = name;
    }

    /**
     * Получить возраст
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * Изменить возраст
     * @param age - возраст, целое число 0-100
     */
    public void setAge(int age) {
        if (age >= 0 && age <= 100)
            this.age = age;
    }

    /**
     * Получить пол
     * @return - пол
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Изменить пол
     * @param sex - пол
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * Получить имя
     * @return - имя
     */
    public String getName() {
        return name;
    }

    /**
     * Изменить имя
     * @param name - имя
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex.getTitle() +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        Sex sex1 = this.getSex();
        Sex sex2 = o.getSex();
        Integer age1 = this.getAge();
        Integer age2 = o.getAge();
        String name1 = this.getName();
        String name2 = o.getName();

        if (!sex1.equals(sex2)) {
            if (sex1.equals(Sex.WOMAN))
                return 1;
            else if (sex2.equals(Sex.WOMAN))
                return -1;
        } else if (!age1.equals(age2)) {
            if (!age1.equals(age2)) {
                if (age1 < age2) {
                    return 1;
                } else if (age1 > age2) {
                    return -1;
                }
            }
        } else if (!name1.equals(name2)){
            return name2.compareTo(name1);
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                sex == person.sex &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, sex, name);
    }
}
