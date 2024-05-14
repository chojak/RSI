package Endpoint;

import WS.WebService;

import javax.xml.ws.Endpoint;

public class WsPublisher {
    public static void main(String[] args) {
        WebService ws = new WebService();
        Endpoint.publish("http://localhost:8888/ws/server", ws);
        System.out.println("Service is published!");
    }
}
