package Chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        System.setProperty("java.security.policy", "security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

//        Remote lookup = Naming.lookup("//192.168.0.106:1099/calculatorRegistry");
        Remote lookup = Naming.lookup("//localhost/chatRMI");
        ServerChatInterface myServer = (ServerChatInterface) lookup;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Connecting...");
        ChatUser currentUser = new ChatUser(name);
        myServer.checkConnection();
        System.out.println(myServer.connect(currentUser));

        while (true) {
            String message = scanner.nextLine();
            myServer.sendMessage(currentUser, message);
        }
    }
}
