
package Zad1.Client.WS;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ServerInfo", targetNamespace = "http://WS.ServerSide.Zad1/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServerInfo {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getServerName", targetNamespace = "http://WS.ServerSide.Zad1/", className = "Zad1.Client.WS.GetServerName")
    @ResponseWrapper(localName = "getServerNameResponse", targetNamespace = "http://WS.ServerSide.Zad1/", className = "Zad1.Client.WS.GetServerNameResponse")
    public String getServerName();

}
