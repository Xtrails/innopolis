package part01.lesson11.util;

import part01.lesson11.domain.Person;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс SortPersonTwoImpl реализует интерфейс SortPerson
 *
 * @version   1.0 17.05.2019
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

        List<Person> personList = Arrays.asList(persons).stream().sorted().collect(Collectors.toList());

        Instant end = Instant.now();
        System.out.println("Время выполения сортировки через компоратор: " + Duration.between(start, end));

        return personList.toArray(new Person[persons.length]);
    }
}
