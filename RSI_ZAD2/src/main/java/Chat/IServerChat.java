package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerChat extends Remote {
    String connect(IClientChatUser user) throws RemoteException;
    void checkConnection() throws RemoteException;
    void sendMessage(IClientChatUser sender, String message) throws RemoteException;
    String disconnect(IClientChatUser user) throws RemoteException;
}
