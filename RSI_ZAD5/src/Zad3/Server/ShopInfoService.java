package Zad3.Server;

import javax.xml.ws.Endpoint;

public class ShopInfoService {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ws/server", new ShopInfo());
        System.out.println("Service is published!");
    }
}
