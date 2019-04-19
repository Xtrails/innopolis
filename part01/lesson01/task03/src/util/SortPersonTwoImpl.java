package util;

import domain.Person;

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
        return new Person[0];
    }
}
