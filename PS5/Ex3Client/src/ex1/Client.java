package ex1;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    private static final String WS_URL = "http://localhost:8087/ws/server?wsdl";

    public static void main (String[] args) throws Exception {
        URL url = new URL(WS_URL);

        QName qName = new QName("http://ex1/", "ShopInfoService");

        Service service = Service.create(url, qName);

        ShopInfo hello = service.getPort(ShopInfo.class);

        try {
            System.out.println(hello.getShopInfo("Konrad"));
        } catch (Exception e) {
            System.out.println("Exception");
        }

    }
}
