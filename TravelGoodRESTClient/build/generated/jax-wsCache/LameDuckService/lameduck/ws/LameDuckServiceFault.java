
package lameduck.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "BookFlightFault", targetNamespace = "http://ws.lameduck/")
public class LameDuckServiceFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private int faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public LameDuckServiceFault(String message, int faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public LameDuckServiceFault(String message, int faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: int
     */
    public int getFaultInfo() {
        return faultInfo;
    }

}
