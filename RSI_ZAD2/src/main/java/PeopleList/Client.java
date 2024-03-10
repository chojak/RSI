package PeopleList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        System.setProperty("java.security.policy", "security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

//        Remote lookup = Naming.lookup("//192.168.0.106:1099/calculatorRegistry");
        Remote lookup = Naming.lookup("//localhost/peopleList");
        ServerListInterface myServer = (ServerListInterface) lookup;
        Scanner scanner = new Scanner(System.in);

        System.out.print("PeopleList: ");
        while (true) {
            System.out.println();
            System.out.println("Choose option: 0 - get all people, 1 - get person");
            String option = scanner.nextLine();
            String response = "";
            switch (option) {
                case "0":
                    Collection<Person> allPeople = myServer.getAll();
                    StringBuilder sb = new StringBuilder();
                    for (Person prsn : allPeople) {
                        sb.append(MessageFormat.format("{0} age: {1}\n", prsn.name, prsn.age));
                    }
                    System.out.print(sb);
                    break;
                case "1":
                    System.out.print("Enter person name: ");
                    String requestName = scanner.nextLine();
                    Person foundPerson = myServer.getPerson(requestName);
                    System.out.print(MessageFormat.format("{0} {1}, wiek: {2}\n", foundPerson.name, foundPerson.surname, foundPerson.age));
                    break;
                case "exit":
                    break;
                default:
                    System.out.print("Unhandled option.");
                    break;
            }
        }
    }
}
