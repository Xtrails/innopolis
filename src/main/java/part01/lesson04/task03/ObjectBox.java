package part01.lesson04.task03;

import java.util.*;

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
    private Set<T> set = new HashSet<>();

    /** Пустой конструктор */
    public ObjectBox() {
    }

    /**
     * Конструктор
     * @param arr - массив
     */
    public ObjectBox(T[] arr) {
        set = new HashSet<>(Arrays.asList(arr));
    }

    /**
     * Получить сет
     * @return - сет
     */
    public Set<T> getSet() {
        return set;
    }

    /**
     * Изменить сет
     * @param set - сет
     */
    public void setSet(Set<T> set) {
        this.set = set;
    }

    /**
     * Добавяет объект в коллекцию
     * @param obj - добавляемый объект
     */
    public void addObject(T obj){
        set.add(obj);
    }

    /**
     * Проверяет наличие объекта в коллекции и при наличии удаляет его
     * @param obj - удаляемый объект
     */
    public void deleteObject(T obj){
        if(set.contains(obj)){
            set.remove(obj);
        }
    }

    /**
     * Выводит содержимое коллекции в строку
     */
    public void dump(){
        for (T t : set) {
            System.out.print(t.toString() + " ");
        }
        System.out.println();
    }
}
