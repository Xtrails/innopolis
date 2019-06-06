package part01.lesson11;

import part01.lesson11.domain.Person;
import part01.lesson11.util.SortPerson;
import part01.lesson11.util.SortPersonOneImpl;
import part01.lesson11.util.SortPersonTwoImpl;

import java.util.stream.IntStream;


/**
 * Задание 11.
 *
 * Реализуем Задание 3 с использованием Stream API.
 * Дан массив объектов domain.Person. Класс domain.Person характеризуется полями
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

        Person[] persons = Person.createPersons(100);
        SortPerson sortPerson = new SortPersonOneImpl();
        persons = sortPerson.sort(persons);
        Person.printArr(persons);

        persons = Person.createPersons(100);
        sortPerson = new SortPersonTwoImpl();
        persons = sortPerson.sort(persons);
        Person.printArr(persons);

    }
}
