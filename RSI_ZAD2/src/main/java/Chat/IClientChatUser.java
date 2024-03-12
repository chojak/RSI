package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientChatUser extends Remote {
    boolean checkConnection() throws RemoteException;
    void receiveMessage(String message) throws RemoteException;
    String getName() throws RemoteException;
}
