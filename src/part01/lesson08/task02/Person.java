package part01.lesson08.task02;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;
    private String fatherName;
    private Home home;

    public Person(String name, int age, String fatherName, Home home) {
        this.name = name;
        this.age = age;
        this.fatherName = fatherName;
        this.home = home;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", fatherName='" + fatherName + '\'' +
                ", home=" + home.getHome() +
                '}';
    }
}
