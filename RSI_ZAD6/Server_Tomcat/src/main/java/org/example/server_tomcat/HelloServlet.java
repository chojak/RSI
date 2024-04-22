package org.example.server_tomcat;

import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.awt.*;
import java.io.IOException;

@MTOM
@WebService
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class HelloServlet {

    @WebMethod
    public String echo(String text) {
        return "Echo: " + text;
    }

    @WebMethod
    public Image downloadImage(String name) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
