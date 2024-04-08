
package ex1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ex1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetShopInfo_QNAME = new QName("http://ex1/", "getShopInfo");
    private final static QName _InvalidInputException_QNAME = new QName("http://ex1/", "InvalidInputException");
    private final static QName _GetShopInfoResponse_QNAME = new QName("http://ex1/", "getShopInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ex1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidInputException }
     * 
     */
    public InvalidInputException createInvalidInputException() {
        return new InvalidInputException();
    }

    /**
     * Create an instance of {@link GetShopInfoResponse }
     * 
     */
    public GetShopInfoResponse createGetShopInfoResponse() {
        return new GetShopInfoResponse();
    }

    /**
     * Create an instance of {@link GetShopInfo }
     * 
     */
    public GetShopInfo createGetShopInfo() {
        return new GetShopInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex1/", name = "getShopInfo")
    public JAXBElement<GetShopInfo> createGetShopInfo(GetShopInfo value) {
        return new JAXBElement<GetShopInfo>(_GetShopInfo_QNAME, GetShopInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidInputException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex1/", name = "InvalidInputException")
    public JAXBElement<InvalidInputException> createInvalidInputException(InvalidInputException value) {
        return new JAXBElement<InvalidInputException>(_InvalidInputException_QNAME, InvalidInputException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ex1/", name = "getShopInfoResponse")
    public JAXBElement<GetShopInfoResponse> createGetShopInfoResponse(GetShopInfoResponse value) {
        return new JAXBElement<GetShopInfoResponse>(_GetShopInfoResponse_QNAME, GetShopInfoResponse.class, null, value);
    }

}
