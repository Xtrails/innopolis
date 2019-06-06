package part01.lesson11.util;

import part01.lesson11.domain.Person;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Класс SortPersonOneImpl реализует интерфейс SortPerson
 *
 * @version   1.0 17.05.2019
 * @author    Pavel Anisimov
 */
public class SortPersonOneImpl implements SortPerson {

    /**
     * Метод сортировки "пузырьковый"
     * Первые идут мужчины выше в списке тот, кто более старший имена сортируются по алфавиту
     * @param persons - не отсортированный массив Person
     * @return - отсортированный массив Person
     */
    @Override
    public Person[] sort(Person[] persons) {
        Instant start = Instant.now();

        List<Person> personArr = Arrays.asList(persons);
        bubbleSortAge(personArr);

        Instant end = Instant.now();
        System.out.println("Время выполения сортировки пузырьковым методом: " + Duration.between(start, end));

        return personArr.toArray(new Person[persons.length]);
    }

    /**
     * Сортировка массива пузырьковым способом по возрасту
     * @param arr - не отсортированный массив Person
     */
    private static void bubbleSortAge(List<Person> arr) {
        IntStream.range(0, arr.size()).map(i -> arr.size() - i - 1).forEach(i ->
                IntStream.range(0, i).sorted().forEach(j -> {
                    if (arr.get(j).compareTo(arr.get(j + 1)) == 1) {
                        Person a = arr.get(j);
                        arr.set(j, arr.get(j + 1));
                        arr.set(j + 1, a);
                    }
                })
        );
    }
}
