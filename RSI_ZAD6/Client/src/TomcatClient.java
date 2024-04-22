import ServerFromWSDL_Tomcat.HelloServlet;
import ServerFromWSDL_Tomcat.HelloServletService;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.MTOMFeature;
import java.util.Map;

public class TomcatClient {
    public static void main(String[] args) {

        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
        System.setProperty("javax.net.ssl.trustStore", "RSI_ZAD6/Client/client_cacerts.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        HelloServletService client = new HelloServletService();
        HelloServlet port = client.getHelloServletPort(new MTOMFeature());

        BindingProvider bindProv = (BindingProvider) port;
        Map<String, Object> context = bindProv.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://localhost:8443/RSI_ZAD6_Tomcat/hello-servlet");

        byte[] bytes = port.downloadImage("pan_ninja.jpg");

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        JLabel label = new JLabel(new ImageIcon(bytes));
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}