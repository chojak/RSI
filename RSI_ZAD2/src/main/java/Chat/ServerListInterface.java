package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ServerListInterface extends Remote {
    Collection<Person> getAll() throws RemoteException;
    Person getPerson(String name) throws RemoteException;
}
