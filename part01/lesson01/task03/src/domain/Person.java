package domain;

/**
 * Класс Person характеризуется полями
 * age (возраст, целое число 0-100),
 * sex (пол – объект класса domain.Sex со строковыми константами внутри MAN, WOMAN),
 * name (имя - строка).
 *
 * @version   1.0 19.04.2019
 * @author    Pavel Anisimov
 */
public class Person {

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


}
