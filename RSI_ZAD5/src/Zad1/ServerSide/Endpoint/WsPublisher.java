package Zad1.ServerSide.Endpoint;

import javax.xml.ws.Endpoint;
import Zad1.ServerSide.WS.ServerInfo;

//Endpoint publisher
public class WsPublisher{

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ws/server", new ServerInfo());

        System.out.println("Service is published!");
    }

}