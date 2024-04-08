package Zad3.Client;

import Zad3.Client.WS.IShopInfo;
import Zad3.Client.WS.ShopInfoService;

public class Client {
    private static final String WS_URL = "http://localhost:8888/ws/server?wsdl";

    public static void main (String[] args) throws Exception {
//        URL url = new URL(WS_URL);
//        QName qName = new QName("http://Server.Zad3/", "ShopInfoService");
//        Service service = Service.create(url, qName);
//        ShopInfo hello = service.getPort(ShopInfo.class);

        ShopInfoService sis = new ShopInfoService();
        IShopInfo si = sis.getShopInfoPort();

        try {
            System.out.println(si.getShopInfo("Lukasz"));
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
