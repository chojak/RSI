package Server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import java.util.Collection;
import java.util.List;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL) //optional

public interface IServerHelloWorld {
    @WebMethod
    String getHelloWorldAsString(String name);

    @WebMethod(operationName = "DostanProdukty")
    Collection<Product> getProducts();
}
