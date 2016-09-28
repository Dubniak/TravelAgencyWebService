/*
 * @author Ema
 */
package niceviewservice.ws;


import javax.xml.ws.WebFault;



@WebFault(name = "cancelHotelFault")
public class CancelHotelFault
    extends Exception
{

    
    private String faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public CancelHotelFault(String message, String faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public CancelHotelFault(String message, String faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: java.lang.String
     */
    public String getFaultInfo() {
        return faultInfo;
    }

}
