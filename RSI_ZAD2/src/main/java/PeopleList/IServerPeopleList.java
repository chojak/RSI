package PeopleList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface IServerPeopleList extends Remote {
    Collection<ClientPerson> getAll() throws RemoteException;
    ClientPerson getPerson(String name) throws RemoteException;
}
