package Zad1.Client.Client;

import Zad1.Client.WS.ServerInfo;
import Zad1.Client.WS.ServerInfoService;

public class WsClient{

    public static void main(String[] args) throws Exception {

        ServerInfoService sis = new ServerInfoService();
        ServerInfo si = sis.getServerInfoPort();

        System.out.println(si.getServerName());

    }

}