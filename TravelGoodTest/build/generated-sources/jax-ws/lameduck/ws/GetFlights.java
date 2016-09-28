
package lameduck.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getFlights complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFlights">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="flightsReq" type="{http://ws.lameduck/}flightRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFlights", propOrder = {
    "flightsReq"
})
public class GetFlights {

    protected FlightRequest flightsReq;

    /**
     * Gets the value of the flightsReq property.
     * 
     * @return
     *     possible object is
     *     {@link FlightRequest }
     *     
     */
    public FlightRequest getFlightsReq() {
        return flightsReq;
    }

    /**
     * Sets the value of the flightsReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightRequest }
     *     
     */
    public void setFlightsReq(FlightRequest value) {
        this.flightsReq = value;
    }

}
