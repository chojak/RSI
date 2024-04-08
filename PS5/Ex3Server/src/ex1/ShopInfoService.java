package ex1;

import javax.xml.ws.Endpoint;

public class ShopInfoService {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8087/ws/server", new ShopInfo());

        System.out.println("Service is published!");
    }
}
