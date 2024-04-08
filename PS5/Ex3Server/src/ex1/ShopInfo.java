package ex1;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ShopInfo {
    @WebMethod
    public String getShopInfo(String name) throws InvalidInputException {
        String outputText = "";
        if(name.equals("Konrad")) {
            outputText = "Akceptuję " + name;
        } else {
            throw new InvalidInputException("Niewłaściwe dane wejściowe", name);
        }
        return outputText;
    }
}
