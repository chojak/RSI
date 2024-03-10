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

        Remote lookup = Naming.lookup("//192.168.0.106:1099/calculatorRegistry");
        ServerListInterface myServer = (ServerListInterface) lookup;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.print("Podaj równanie: ");
            String request = scanner.nextLine();
            String response = myServer.doMath(request);
            System.out.print("Wynik: " + response);

            if (request == "exit")
                break;
        }
    }
}
