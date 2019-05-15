package part01.lesson09.task01;

import java.io.Serializable;

/**
 * Реализация Worker
 */
public class SomeClass implements Worker, Serializable {

    @Override
    public void doWork() {System.out.println("doWork(): 1.0");}
}
