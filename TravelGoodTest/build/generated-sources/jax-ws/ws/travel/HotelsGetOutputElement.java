
package ws.travel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import niceviewservice.ws.GetHotelsResponse;


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
 *         &lt;element ref="{http://ws.niceviewservice/}getHotelsResponse"/>
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
    "getHotelsResponse"
})
@XmlRootElement(name = "hotelsGetOutputElement")
public class HotelsGetOutputElement {

    @XmlElement(namespace = "http://ws.niceviewservice/", required = true)
    protected GetHotelsResponse getHotelsResponse;

    /**
     * Gets the value of the getHotelsResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GetHotelsResponse }
     *     
     */
    public GetHotelsResponse getGetHotelsResponse() {
        return getHotelsResponse;
    }

    /**
     * Sets the value of the getHotelsResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetHotelsResponse }
     *     
     */
    public void setGetHotelsResponse(GetHotelsResponse value) {
        this.getHotelsResponse = value;
    }

}
