package part01.lesson04.task02;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс ObjectBox, который хранит коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 *
 * @version   1.0 23.04.2019
 * @author    Pavel Anisimov
 */
public class ObjectBox<T> {

    /** Коллекция */
    private List<T> list = new ArrayList<>();

    /**
     * Конструктор
     * @param list - лист
     */
    public ObjectBox(List<T> list) {
        this.list = list;
    }

    /**
     * Добавяет объект в коллекцию
     * @param obj - добавляемый объект
     */
    public void addObject(T obj){
        list.add(obj);
    }

    /**
     * Проверяет наличие объекта в коллекции и при наличии удаляет его
     * @param obj - удаляемый объект
     */
    public void deleteObject(T obj){
        if(list.contains(obj)){
            list.remove(obj);
        }
    }

    /**
     * Выводит содержимое коллекции в строку
     */
    public void dump(){
        for (T t : list) {
            System.out.print(t.toString() + " ");
        }
        System.out.println();
    }
}
