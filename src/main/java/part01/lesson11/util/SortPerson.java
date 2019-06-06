package part01.lesson11.util;

import part01.lesson11.domain.Person;

/**
 * Единый интерфейс сортировки для класса Person
 *
 * @version   1.0 17.05.2019
 * @author    Pavel Anisimov
 */
public interface SortPerson {
    /**
     * Сортировка объектов Person
     * @param persons - не отсортированный массив Person
     * @return - отсортированный массив Person
     */
    Person[] sort(Person[] persons);
}
