
package lameduck.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LameDuckService", targetNamespace = "http://ws.lameduck/", wsdlLocation = "http://localhost:8080/LameDuckService/LameDuckService?wsdl")
public class LameDuckService
    extends Service
{

    private final static URL LAMEDUCKSERVICE_WSDL_LOCATION;
    private final static WebServiceException LAMEDUCKSERVICE_EXCEPTION;
    private final static QName LAMEDUCKSERVICE_QNAME = new QName("http://ws.lameduck/", "LameDuckService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/LameDuckService/LameDuckService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LAMEDUCKSERVICE_WSDL_LOCATION = url;
        LAMEDUCKSERVICE_EXCEPTION = e;
    }

    public LameDuckService() {
        super(__getWsdlLocation(), LAMEDUCKSERVICE_QNAME);
    }

    public LameDuckService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LAMEDUCKSERVICE_QNAME, features);
    }

    public LameDuckService(URL wsdlLocation) {
        super(wsdlLocation, LAMEDUCKSERVICE_QNAME);
    }

    public LameDuckService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LAMEDUCKSERVICE_QNAME, features);
    }

    public LameDuckService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LameDuckService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LameDuck
     */
    @WebEndpoint(name = "LameDuckPort")
    public LameDuck getLameDuckPort() {
        return super.getPort(new QName("http://ws.lameduck/", "LameDuckPort"), LameDuck.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LameDuck
     */
    @WebEndpoint(name = "LameDuckPort")
    public LameDuck getLameDuckPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.lameduck/", "LameDuckPort"), LameDuck.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LAMEDUCKSERVICE_EXCEPTION!= null) {
            throw LAMEDUCKSERVICE_EXCEPTION;
        }
        return LAMEDUCKSERVICE_WSDL_LOCATION;
    }

}
