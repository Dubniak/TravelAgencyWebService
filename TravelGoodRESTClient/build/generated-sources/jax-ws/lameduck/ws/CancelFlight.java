
package lameduck.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cancelFlight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancelFlight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancelReq" type="{http://ws.lameduck/}cancelFlightRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelFlight", propOrder = {
    "cancelReq"
})
public class CancelFlight {

    protected CancelFlightRequest cancelReq;

    /**
     * Gets the value of the cancelReq property.
     * 
     * @return
     *     possible object is
     *     {@link CancelFlightRequest }
     *     
     */
    public CancelFlightRequest getCancelReq() {
        return cancelReq;
    }

    /**
     * Sets the value of the cancelReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelFlightRequest }
     *     
     */
    public void setCancelReq(CancelFlightRequest value) {
        this.cancelReq = value;
    }

}
