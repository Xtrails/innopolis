import domain.Person;
import domain.Sex;

import javax.jnlp.PersistenceService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Задание 3. Дан массив объектов domain.Person. Класс domain.Person характеризуется полями
 *     age (возраст, целое число 0-100),
 *     sex (пол – объект класса domain.Sex со строковыми константами внутри MAN, WOMAN),
 *     name (имя - строка).
 * Создать два класса, методы которых будут реализовывать сортировку объектов.
 * Предусмотреть единый интерфейс для классов сортировки.
 * Реализовать два различных метода сортировки этого массива по правилам:
 *     первые идут мужчины выше в списке тот, кто более старший
 *     имена сортируются по алфавиту
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 */

public class Main {

    public static void main(String[] args) {
        Person[] persons = createPersons(100);
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }

    /**
     * Создает массив объектов Person размером count
     * @param count - размер массива
     * @return - массив объектов Person размером count
     */
    private static Person[] createPersons(int count) {
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
}
