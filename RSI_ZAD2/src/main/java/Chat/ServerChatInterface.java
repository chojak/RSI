package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ServerChatInterface extends Remote {
    String connect(ChatUserInterface user) throws RemoteException;
    void checkConnection() throws RemoteException;
    void sendMessage(ChatUserInterface sender, String message) throws RemoteException;
    String disconnect(ChatUserInterface user) throws RemoteException;
}
