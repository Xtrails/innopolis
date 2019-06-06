package part01.lesson01.task03.util;

import part01.lesson01.task03.domain.Person;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс SortPersonOneImpl реализует интерфейс SortPerson
 *
 * @version   1.0 19.04.2019
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

        List<Person> personArr = new ArrayList<>();
        for (Person person : persons) {
            personArr.add(person);
        }
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
        for (int i = arr.size()-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1))==1) {
                    Person a = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, a);
                }
            }
        }
    }
}
