package part01.lesson11.domain;

import part01.lesson11.exception.PersonDuplicateException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Класс Person характеризуется полями
 * age (возраст, целое число 0-100),
 * sex (пол – объект класса domain.Sex со строковыми константами внутри MAN, WOMAN),
 * name (имя - строка).
 *
 * @version   1.0 17.05.2019
 * @author    Pavel Anisimov
 */
public class Person implements Comparable<Person> {

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
        } else if (age1 != age2) {
            if (age1 != age2) {
                if (age1 < age2) {
                    return 1;
                } else if (age1 > age2) {
                    return -1;
                }
            }
        } else if (name1 != name2){
            return name2.compareTo(name1);
        } else if(name1.equals(name2) && age1==age2) {
            try {
                throw new PersonDuplicateException("При сортировке найдены дуюлирующиеся объекты Person");
            } catch (PersonDuplicateException e) {
                e.getMessage();
            }
        }
        return 0;
    }

    /**
     * Создает массив объектов Person размером count
     * @param count - размер массива
     * @return - массив объектов Person размером count
     */
    public static Person[] createPersons(int count) {
        if (count > 0) {
            List<Person> list = new ArrayList<>();
            IntStream.range(0, count).sorted().forEach((i)->{
                Person person = new Person();
                SecureRandom rnd = new SecureRandom();
                int age = rnd.nextInt(100);
                person.setAge(age);
                person.setName(UUID.randomUUID().toString());
                person.setSex(rnd.nextBoolean()? Sex.MAN: Sex.WOMAN);
                list.add(person);
            });
            Person[] persons = list.toArray(new Person[count]);
            return persons;
        }
        return null;
    }


    /**
     * Вывод массива Person на экран
     * @param persons - массив для вывода на экран
     */
    public static void printArr(Person[] persons){
        Arrays.asList(persons).stream().forEach(person -> System.out.println(person.toString()));
    }
}
