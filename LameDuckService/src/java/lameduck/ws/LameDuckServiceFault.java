/**
 *
 * @author catalin
 */
package lameduck.ws;

import javax.xml.ws.WebFault;

//@WebFault(name = "flightBookingFault", targetNamespace = "http://j2ee.netbeans.org/wsdl/LameDuck/java/LameDuck")


@WebFault(name = "BookFlightFault")
public class LameDuckServiceFault extends Exception {

    protected int faultInfo;

    public LameDuckServiceFault(String message, int faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     *
     * @param message
     * @param faultInfo
     * @param cause
     */
    public LameDuckServiceFault(String message, int faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

 
    public int getFaultInfo() {
        return faultInfo;
    }

}
