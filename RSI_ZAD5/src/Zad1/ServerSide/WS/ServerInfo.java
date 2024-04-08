package Zad1.ServerSide.WS;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@HandlerChain(file= "Zad1/ServerSide/handler-chain.xml")
public class ServerInfo{

    @WebMethod
    public String getServerName() {

        return "Lukasz server";

    }

}