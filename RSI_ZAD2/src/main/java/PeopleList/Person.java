package PeopleList;

import java.io.Serializable;

public class Person implements Serializable {
    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    String name;
    String surname;
    int age;
}