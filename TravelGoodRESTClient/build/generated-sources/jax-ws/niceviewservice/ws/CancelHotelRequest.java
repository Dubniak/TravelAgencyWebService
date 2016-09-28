
package niceviewservice.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cancelHotelRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancelHotelRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelHotelRequest", propOrder = {
    "bookingNo"
})
public class CancelHotelRequest {

    protected int bookingNo;

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

}
