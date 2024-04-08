package Zad3.Server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IShopInfo {
    @WebMethod
    public String getShopInfo(String name) throws InvalidInputException;
}
