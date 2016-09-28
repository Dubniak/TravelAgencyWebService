
package ws.travel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lameduck.ws.GetFlights;


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
 *         &lt;element name="itineraryID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://ws.lameduck/}getFlights"/>
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
    "itineraryID",
    "getFlights"
})
@XmlRootElement(name = "flightsGetInputElement")
public class FlightsGetInputElement {

    @XmlElement(required = true)
    protected String itineraryID;
    @XmlElement(namespace = "http://ws.lameduck/", required = true)
    protected GetFlights getFlights;

    /**
     * Gets the value of the itineraryID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItineraryID() {
        return itineraryID;
    }

    /**
     * Sets the value of the itineraryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItineraryID(String value) {
        this.itineraryID = value;
    }

    /**
     * Gets the value of the getFlights property.
     * 
     * @return
     *     possible object is
     *     {@link GetFlights }
     *     
     */
    public GetFlights getGetFlights() {
        return getFlights;
    }

    /**
     * Sets the value of the getFlights property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetFlights }
     *     
     */
    public void setGetFlights(GetFlights value) {
        this.getFlights = value;
    }

}
