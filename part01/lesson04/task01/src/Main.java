/**
 * Урок 4. Задание 1.
 *
 * Написать класс MathBox, реализующий следующий функционал:
 * Конструктор на вход получает массив Number. Элементы не могут повторяться.
 * Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
 * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать
 * MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap).
 * Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 *
 * @version   1.0 23.04.2019
 * @author    Pavel Anisimov
 */
public class Main {

    public static void main(String[] args) {
        Number[] arr = new Number[]{1,1.5,5.5F,2D,1,10};
        MathBox<Number> box = new MathBox<>(arr);
        System.out.println(box.toString());

        Double sum = MathBox.summator(box.getList());
        System.out.println(sum);

        box.deleteIntVal(10);
        System.out.println(box.toString());

        box.splitter(2);
        System.out.println(box.toString());
    }
}
