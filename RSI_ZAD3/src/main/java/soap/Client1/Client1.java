package soap.Client1;


import soap.Client1.GeneratedByWsimport.IServerHelloWorld;
import soap.Client1.GeneratedByWsimport.ServerHelloWorldService;

public class Client1 {
    public static void main(String[] args) {
        ServerHelloWorldService serverHelloWorldService = new ServerHelloWorldService();
        IServerHelloWorld serviceHello = serverHelloWorldService.getServerHelloWorldPort();

        String request = "It's me - CLIENT";
        String response = serviceHello.getHelloWorldAsString(request);

        System.out.println("Client sent: " + request);
        System.out.println("Client received: " + response);
    }
}