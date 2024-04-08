package ex1.endpoint;

import ex1.ws.ServerInfo;

import javax.xml.ws.Endpoint;

public class WsPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ws/server", new ServerInfo());
        System.out.println("Service is published!");
    }
}
