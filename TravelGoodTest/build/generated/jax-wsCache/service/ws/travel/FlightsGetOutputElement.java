
package ws.travel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lameduck.ws.GetFlightsResponse;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ws.lameduck/}getFlightsResponse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getFlightsResponse"
})
@XmlRootElement(name = "flightsGetOutputElement")
public class FlightsGetOutputElement {

    @XmlElement(namespace = "http://ws.lameduck/", required = true)
    protected GetFlightsResponse getFlightsResponse;

    /**
     * Gets the value of the getFlightsResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GetFlightsResponse }
     *     
     */
    public GetFlightsResponse getGetFlightsResponse() {
        return getFlightsResponse;
    }

    /**
     * Sets the value of the getFlightsResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetFlightsResponse }
     *     
     */
    public void setGetFlightsResponse(GetFlightsResponse value) {
        this.getFlightsResponse = value;
    }

}
