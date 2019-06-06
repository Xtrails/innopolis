package part01.lesson01.task03.util;

import part01.lesson01.task03.domain.Person;

/**
 * Единый интерфейс сортировки для класса Person
 *
 * @version   1.0 19.04.2019
 * @author    Pavel Anisimov
 */
public interface SortPerson {
    /**
     * Сортировка объектов Person
     * @param persons - не отсортированный массив Person
     * @return - отсортированный массив Person
     */
    Person[] sort (Person[] persons);
}
