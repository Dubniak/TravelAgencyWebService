
package lameduck.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;


/**
 * <p>Java class for cancelFlightRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancelFlightRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="creditCardDetails" type="{http://types.fastmoney.imm.dtu.dk}creditCardInfoType" minOccurs="0"/>
 *         &lt;element name="flightPrice" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelFlightRequest", propOrder = {
    "bookingNo",
    "creditCardDetails",
    "flightPrice"
})
public class CancelFlightRequest {

    protected int bookingNo;
    protected CreditCardInfoType creditCardDetails;
    protected int flightPrice;

    /**
     * Gets the value of the bookingNo property.
     * 
     */
    public int getBookingNo() {
        return bookingNo;
    }

    /**
     * Sets the value of the bookingNo property.
     * 
     */
    public void setBookingNo(int value) {
        this.bookingNo = value;
    }

    /**
     * Gets the value of the creditCardDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardInfoType }
     *     
     */
    public CreditCardInfoType getCreditCardDetails() {
        return creditCardDetails;
    }

    /**
     * Sets the value of the creditCardDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardInfoType }
     *     
     */
    public void setCreditCardDetails(CreditCardInfoType value) {
        this.creditCardDetails = value;
    }

    /**
     * Gets the value of the flightPrice property.
     * 
     */
    public int getFlightPrice() {
        return flightPrice;
    }

    /**
     * Sets the value of the flightPrice property.
     * 
     */
    public void setFlightPrice(int value) {
        this.flightPrice = value;
    }

}
