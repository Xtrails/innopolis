package util;

import domain.Person;
import domain.Sex;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс Util вспомогательные методы
 *
 * @version   1.0 22.04.2019
 * @author    Pavel Anisimov
 */
public class Util {

    /**
     * Создает массив объектов Person размером count
     * @param count - размер массива
     * @return - массив объектов Person размером count
     */
    public static Person[] createPersons(int count) {
        if (count > 0) {
            List<Person> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Person person = new Person();
                SecureRandom rnd = new SecureRandom();
                int age = rnd.nextInt(100);
                person.setAge(age);
                person.setName(UUID.randomUUID().toString());
                person.setSex(rnd.nextBoolean()? Sex.MAN:Sex.WOMAN);
                list.add(person);
            }
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
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }
}
