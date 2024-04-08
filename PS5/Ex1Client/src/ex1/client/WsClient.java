package ex1.client;

import ex1.ws.ServerInfo;
import ex1.ws.ServerInfoService;

public class WsClient{

    public static void main(String[] args) throws Exception {

        ServerInfoService sis = new ServerInfoService();
        ServerInfo si = sis.getServerInfoPort();

        System.out.println(si.getServerName());

    }

}
