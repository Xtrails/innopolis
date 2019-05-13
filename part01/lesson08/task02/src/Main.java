/**
 * Урок 7. Задание 1.
 *
 * Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 *
 * Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы)
 *
 * @version   1.0 13.05.2019
 * @author    Pavel Anisimov
 */
public class Main {

    public static void main(String[] args) {
        Home home = new Home("Truda 2");
        Person person = new Person("Pasha",20,"Sasha", home);
        SerializableMgr.serialize(person,"person.txt");
        Person person2 = (Person) SerializableMgr.deSerialize("person.txt");
        System.out.println(person2.toString());
    }
}
