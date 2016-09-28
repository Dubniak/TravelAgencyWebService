
package lameduck.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookFlight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bookFlight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookReq" type="{http://ws.lameduck/}bookFlightRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookFlight", propOrder = {
    "bookReq"
})
public class BookFlight {

    protected BookFlightRequest bookReq;

    /**
     * Gets the value of the bookReq property.
     * 
     * @return
     *     possible object is
     *     {@link BookFlightRequest }
     *     
     */
    public BookFlightRequest getBookReq() {
        return bookReq;
    }

    /**
     * Sets the value of the bookReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookFlightRequest }
     *     
     */
    public void setBookReq(BookFlightRequest value) {
        this.bookReq = value;
    }

}
