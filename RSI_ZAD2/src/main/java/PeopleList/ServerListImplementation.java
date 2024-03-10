package PeopleList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

public class ServerListImplementation extends UnicastRemoteObject implements ServerListInterface {
    protected ServerListImplementation() throws RemoteException {
        super();
    }

    private static final Collection<Person> peopleList = new ArrayList<Person>() {
        {
            add(new Person("Albert", "Carvowsky", 24));
            add(new Person("Jan", "Paul", 80));
            add(new Person("Andrzej", "Devil", 49));
            add(new Person("Tomasz", "Grater", 88));
        }
    };

    @Override
    public Collection<Person> getAll() throws RemoteException {
        return peopleList;
    }

    @Override
    public Person getPerson(String name) throws RemoteException {
        return peopleList.stream()
                .filter(e -> e.name.equals(name))
                .findFirst().get();
//        return ""
    }
}