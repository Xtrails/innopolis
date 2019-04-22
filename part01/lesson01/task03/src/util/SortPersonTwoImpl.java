package util;

import domain.Person;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Класс SortPersonTwoImpl реализует интерфейс SortPerson
 *
 * @version   1.0 19.04.2019
 * @author    Pavel Anisimov
 */
public class SortPersonTwoImpl implements SortPerson {

    /**
     * Метод сортировки ""
     * Первые идут мужчины выше в списке тот, кто более старший имена сортируются по алфавиту
     * @param persons - не отсортированный массив Person
     * @return - отсортированный массив Person
     */
    @Override
    public Person[] sort(Person[] persons) {
        Instant start = Instant.now();

        List<Person> personList = new ArrayList();
        for (Person person : persons)
            personList.add(person);
        Collections.sort(personList);

        Instant end = Instant.now();
        System.out.println("Время выполения сортировки через компоратор: " + Duration.between(start, end));

        return personList.toArray(new Person[persons.length]);
    }
}
