package TicTacToe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientPlayerInterface extends Remote {
    boolean checkConnection() throws RemoteException;
    String getName() throws RemoteException;
    ClientPlayerMove makeMove(List<ClientPlayerMove> legalMoves) throws RemoteException;
    void gameStarts() throws RemoteException;
    void printMessage(String message) throws RemoteException;
}