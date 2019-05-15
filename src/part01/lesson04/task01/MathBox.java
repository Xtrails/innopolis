package part01.lesson04.task01;

import java.util.*;

/**
 * Класс MathBox, реализующий следующий функционал:
 *
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
public class MathBox<T extends Number> {

    /** Лист */
    private List<T> list = new ArrayList<>();

    /**
     * Конструктор
     * @param arr - массив объектов
     */
    public MathBox(T[] arr) {
        Set<T> set = new HashSet<>(Arrays.asList(arr));
        this.list.addAll(set);
    }

    /**
     * Получить лист
     * @return - лист
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Изменить лист
     * @param list - лист
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * Метод summator, возвращающий сумму всех элементов коллекции
     * @param list - лист объектов
     * @return - сумма объектов коллекции
     */
    public static Double summator(List<? extends Number> list) {
        return list
                .stream()
                .map(Number::doubleValue)
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0D);
    }

    /**
     * Метод получает на вход Integer и если такое значение есть в коллекции, удаляет его
     * @param val - значение, которое необходимо удалить из листа
     */
    public void deleteIntVal(Integer val){
        if(list.contains(val)){
            list.remove(val);
        }
    }

    /**
     * Метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
     * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
     * @param val - делитель
     */
    public void splitter(T val) {
        if (val != null) {
            for (int i = 0; i < list.size(); i++) {
                Double newVal = list.get(i).doubleValue() / val.doubleValue();
                list.set(i, (T) newVal);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (T t : list) {
            result+=t.toString() + " ";
        }
        return "MathBox{" +
                " list=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox<?> mathBox = (MathBox<?>) o;
        return Objects.equals(list, mathBox.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
