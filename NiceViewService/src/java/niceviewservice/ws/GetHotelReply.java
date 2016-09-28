/*
 * @author Eirini
 */
package niceviewservice.ws;


import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "getHotelReply")
public class GetHotelReply {

   
    protected List<Booking> bookingList;

    /**
     * Gets the value of the bookingList property.
     * 
     * @return
     *     possible object is
     *     {@link BookingList }
     *     
     */
    public List<Booking> getBookingList() {
        return bookingList;
    }

    /**
     * Sets the value of the bookingList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingList }
     *     
     */
    public void setBookingList(List<Booking> value) {
        this.bookingList = value;
    }

}
