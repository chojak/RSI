package Zad2.Server.endpoint;

import Zad2.Server.WS.HelloWorldImpl;

import javax.xml.ws.Endpoint;

public class HelloWorldPublisher {
    public static void main (String[] args) {
        Endpoint.publish("http://localhost:8888/ws/hello", new HelloWorldImpl());
        System.out.println("Service is published!");
    }
}
