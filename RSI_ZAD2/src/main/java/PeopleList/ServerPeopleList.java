package PeopleList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

public class ServerPeopleList extends UnicastRemoteObject implements IServerPeopleList {
    protected ServerPeopleList() throws RemoteException {
        super();
    }

    private static final Collection<ClientPerson> peopleList = new ArrayList<ClientPerson>() {
        {
            add(new ClientPerson("Albert", "Carvowsky", 24));
            add(new ClientPerson("Jan", "Paul", 80));
            add(new ClientPerson("Andrzej", "Devil", 49));
            add(new ClientPerson("Tomasz", "Grater", 88));
        }
    };

    @Override
    public Collection<ClientPerson> getAll() throws RemoteException {
        return peopleList;
    }

    @Override
    public ClientPerson getPerson(String name) throws RemoteException {
        return peopleList.stream()
                .filter(e -> e.name.equals(name))
                .findFirst().get();
//        return ""
    }
}