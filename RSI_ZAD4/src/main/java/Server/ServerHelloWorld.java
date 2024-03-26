package Server;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebService(endpointInterface = "Server.IServerHelloWorld")

public class ServerHelloWorld implements IServerHelloWorld {
    @Override
    public String getHelloWorldAsString(String name) {
        System.out.println("Klient wysłał request o treści: " + name);
        return "Witaj świecie JAX-WS: " + name;
    }

    @Override
    public Collection<Product> getProducts() {
        return new ArrayList<Product>() {
            {
                add(new Product("Płyn do mycia szyb", "Bardzo ładnie pachnie", 21));
                add(new Product("Kanapka", "Z szynką", 69));
                add(new Product("Placki ziemiaczane", "Mniam", 420));
                add(new Product("Słuchawki", "Gamingowe", 666));
            }
        };
    }
}
