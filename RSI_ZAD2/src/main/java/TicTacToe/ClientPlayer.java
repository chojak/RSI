package TicTacToe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientPlayer extends UnicastRemoteObject implements ClientPlayerInterface {
    String name;
    public ClientPlayer(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public boolean checkConnection() throws RemoteException {
        return false;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public ClientPlayerMove makeMove(List<ClientPlayerMove> legalMoves) throws RemoteException {
        System.out.print("Make your move. Type position (x y): ");
        Scanner scanner = new Scanner(System.in);
        int inputX = 0;
        int inputY = 0;
        boolean isLegalMove = false;
        while (!isLegalMove) {
            inputX = scanner.nextInt() - 1;
            inputY = scanner.nextInt() - 1;
            int finalInputX = inputX;
            int finalInputY = inputY;
            isLegalMove = legalMoves.stream().anyMatch(legalMove -> legalMove.x == finalInputX && legalMove.y == finalInputY);
            if (!isLegalMove) {
                System.out.println("Wrong move!");
                System.out.print("Make your move. Type position (x y): ");
            }
        }
        return new ClientPlayerMove(inputX, inputY, this.getName());
    }

    @Override
    public void gameStarts() throws RemoteException {
        System.out.println("Game starts \n\n\n");
    }

    @Override
    public void printMessage(String message) throws RemoteException {
        System.out.println(message);
    }

}
