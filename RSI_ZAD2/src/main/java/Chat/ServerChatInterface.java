package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ServerChatInterface extends Remote {
    String connect(ChatUserInterface user) throws RemoteException;
    String checkConnection() throws RemoteException;
    boolean sendMessage(ChatUserInterface sender, String message) throws RemoteException;
}
