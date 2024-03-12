package Chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientChatUser extends UnicastRemoteObject implements IClientChatUser {
    String name;
    public ClientChatUser(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public boolean checkConnection() throws RemoteException {
        return false;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
