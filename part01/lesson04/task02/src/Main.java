import java.util.ArrayList;
import java.util.List;

/**
 * Урок 4. Задание 2.
 *
 * Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 *
 * @version   1.0 23.04.2019
 * @author    Pavel Anisimov
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        ObjectBox<Integer> objectBox = new ObjectBox<>(list);
        objectBox.dump();

        objectBox.addObject(5);
        objectBox.dump();

        objectBox.deleteObject(1);
        objectBox.dump();
    }
}
