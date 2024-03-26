package soap.Server;

import soap.Server.IServerHelloWorld;

import javax.jws.WebService;
@WebService(endpointInterface = "soap.Server.IServerHelloWorld")

public class ServerHelloWorld implements IServerHelloWorld {
    @Override
    public String getHelloWorldAsString(String name) {
        System.out.println("Klient wysłał request o treści: " + name);
        return "Witaj świecie JAX-WS: " + name;
    }
}
