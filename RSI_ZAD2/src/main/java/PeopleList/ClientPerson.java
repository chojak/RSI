package PeopleList;

import java.io.Serializable;

public class ClientPerson implements Serializable {
    public ClientPerson(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    String name;
    String surname;
    int age;
}