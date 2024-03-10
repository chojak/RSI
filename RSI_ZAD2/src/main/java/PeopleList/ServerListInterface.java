package PeopleList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ServerListInterface extends Remote {
    Collection<Person> getAll() throws RemoteException;
    Person getPerson(String name) throws RemoteException;
}
