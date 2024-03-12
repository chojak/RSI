package TicTacToe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerGameInterface extends Remote {
    String connect(ClientPlayerInterface user) throws RemoteException;
    void checkConnection() throws RemoteException;
}
