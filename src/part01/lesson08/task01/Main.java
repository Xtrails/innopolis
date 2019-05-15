package part01.lesson08.task01;

import part01.lesson08.task02.Home;
import part01.lesson08.task02.SerializableMgr;

/**
 * Урок 7. Задание 1.
 *
 * Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 *
 * @version   1.0 13.05.2019
 * @author    Pavel Anisimov
 */
public class Main {

    public static void main(String[] args) {
        Home home = new Home("Truda 2");
        SerializableMgr.serialize(home,"home.txt");
        Home home2 = (Home) SerializableMgr.deSerialize("home.txt");
        System.out.println(home2.getHome());
    }
}
