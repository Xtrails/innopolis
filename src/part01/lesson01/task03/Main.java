package part01.lesson01.task03;

import part01.lesson01.task03.domain.Person;
import part01.lesson01.task03.util.SortPerson;
import part01.lesson01.task03.util.SortPersonOneImpl;
import part01.lesson01.task03.util.SortPersonTwoImpl;
import part01.lesson01.task03.util.Util;


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

        Person[] persons = Util.createPersons(15000);
        SortPerson sortPerson = new SortPersonOneImpl();
        persons = sortPerson.sort(persons);
//        Util.printArr(persons);

        persons = Util.createPersons(15000);
        sortPerson = new SortPersonTwoImpl();
        persons = sortPerson.sort(persons);
//        Util.printArr(persons);
    }
}
