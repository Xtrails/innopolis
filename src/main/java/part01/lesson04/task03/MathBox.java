package part01.lesson04.task03;

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
public class MathBox<T extends Number> extends ObjectBox<T> {

    public MathBox(Object o) {
        if(o instanceof Object){
            try {
                throw new MathBoxException("Объект типа Object не может быть помещен в конструктор MathBox");
            } catch (MathBoxException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Конструктор
     * @param arr - массив
     */
    public MathBox(T[] arr) {
        super(arr);
    }

    /**
     * Метод summator, возвращающий сумму всех элементов коллекции
     * @param set - сет объектов
     * @return - сумма объектов коллекции
     */
    public static Double summator(Set<? extends Number> set) {
        return set
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

        if(this.getSet().contains(val)){
            this.getSet().remove(val);
        }
    }

    /**
     * Метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
     * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
     * @param val - делитель
     */
    public void splitter(T val) {
        if (val != null) {
            Set<T> splitSet = new HashSet<>();
            for (T t : this.getSet()) {
                Double newVal = t.doubleValue() / val.doubleValue();
                splitSet.add((T) newVal);
            }
            this.getSet().clear();
            this.getSet().addAll(splitSet);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (T t : this.getSet()) {
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
        return Objects.equals(this.getSet(), mathBox.getSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getSet());
    }
}
