package TicTacToe;

import java.io.IOException;
import java.net.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            String host = getMachineAddress();
            System.setProperty("java.rmi.server.hostname", host);
            System.out.println(host);

            ServerGame obj1 = new ServerGame();

            LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/TicTacToe", obj1);

            System.out.println("Server started");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static String getMachineAddress() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));
            return socket.getLocalAddress().getHostAddress();
        } catch (IOException ex) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return "localhost";
            }
        }
    }
}