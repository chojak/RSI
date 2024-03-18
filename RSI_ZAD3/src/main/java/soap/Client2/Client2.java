package soap.Client2;


import soap.Client1.GeneratedByWsimport.IServerHelloWorld;
import soap.Client1.GeneratedByWsimport.ServerHelloWorldService;
import soap.Server.ServerHelloWorld;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;


public class Client2 {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:9999/ws/hello?wsdl");
        QName qname = new QName("http://Server.soap/", "ServerHelloWorldService");

        Service service = Service.create(url, qname);
        IServerHelloWorld hello = service.getPort(IServerHelloWorld.class);

        String request = "It's me - CLIENT";
        String response = hello.getHelloWorldAsString(request);

        System.out.println("Client sent: " + request);
        System.out.println("Client received: " + response);
    }
}