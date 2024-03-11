package TicTacToe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatUserInterface extends Remote {
    boolean checkConnection() throws RemoteException;
    void receiveMessage(String message) throws RemoteException;
    String getName() throws RemoteException;
}
