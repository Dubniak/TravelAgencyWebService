
package ws.travel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import niceviewservice.ws.GetHotels;


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
 *         &lt;element ref="{http://ws.niceviewservice/}getHotels"/>
 *         &lt;element name="itineraryID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getHotels",
    "itineraryID"
})
@XmlRootElement(name = "hotelsGetInputElement")
public class HotelsGetInputElement {

    @XmlElement(namespace = "http://ws.niceviewservice/", required = true)
    protected GetHotels getHotels;
    @XmlElement(required = true)
    protected String itineraryID;

    /**
     * Gets the value of the getHotels property.
     * 
     * @return
     *     possible object is
     *     {@link GetHotels }
     *     
     */
    public GetHotels getGetHotels() {
        return getHotels;
    }

    /**
     * Sets the value of the getHotels property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetHotels }
     *     
     */
    public void setGetHotels(GetHotels value) {
        this.getHotels = value;
    }

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

}
