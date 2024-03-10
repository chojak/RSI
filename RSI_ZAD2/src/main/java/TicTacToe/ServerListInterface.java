package TicTacToe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerListInterface extends Remote {
    String doMath(String input)
        throws RemoteException;
}
