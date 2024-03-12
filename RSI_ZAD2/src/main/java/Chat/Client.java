package Chat;

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

        Remote lookup = Naming.lookup("//localhost/chatRMI");
        IServerChat myServer = (IServerChat) lookup;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Connecting...");
        ClientChatUser currentUser = new ClientChatUser(name);
//        myServer.checkConnection();
        myServer.connect(currentUser);

        while (true) {
//            System.out.print(MessageFormat.format("[{0}]: ", currentUser.getName()));
            String message = scanner.nextLine();
//            System.out.print("\r");
            if (message.equals("exit")) {
                System.out.println(myServer.disconnect(currentUser));
                System.exit(0);
            } else {
                myServer.sendMessage(currentUser, message);
            }
        }
    }
}
