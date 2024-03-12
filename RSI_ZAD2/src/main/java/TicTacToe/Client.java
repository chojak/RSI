package TicTacToe;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        System.setProperty("java.security.policy", "security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Remote lookup = Naming.lookup("//localhost/TicTacToe");
        ServerGameInterface myServer = (ServerGameInterface) lookup;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Connecting...");
        ClientPlayer currentPlayer = new ClientPlayer(name);
//        myServer.checkConnection();
        System.out.println(myServer.connect(currentPlayer));

        while (true) { }
    }
}
