import ServerFromWSDL.HelloServlet;
import ServerFromWSDL.HelloServletService;

import javax.swing.*;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.MTOMFeature;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.setProperty("javax.net.ssl.trustStore", "RSI_ZAD6/Client/client_cacerts.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        HelloServletService client = new HelloServletService();
        HelloServlet port = client.getHelloServletPort(new MTOMFeature());

        BindingProvider bindProv = (BindingProvider) port;
        Map<String, Object> context = bindProv.getRequestContext();
        context.put("javax.xml.ws.security.auth.username", "user");
        context.put("javax.xml.ws.security.auth.password", "user123");
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://localhost:443//RSI_ZAD6//HelloServlet");

        byte[] bytes = port.downloadImage("pan_ninja.jpg");

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        JLabel label = new JLabel(new ImageIcon(bytes));
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}