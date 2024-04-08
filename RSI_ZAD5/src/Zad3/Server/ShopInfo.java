package Zad3.Server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (endpointInterface = "Zad3.Server.IShopInfo")
public class ShopInfo {
    public String getShopInfo(String name) throws InvalidInputException {
        String outputText = "";
        if(name.equals("Lukasz")) {
            outputText = "Akceptuję " + name;
        } else {
            throw new InvalidInputException("Niewłaściwe dane wejściowe", name);
        }
        return outputText;
    }
}
