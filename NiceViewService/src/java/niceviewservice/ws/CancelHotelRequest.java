/*
 * @author Ema
 */
package niceviewservice.ws;


import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "cancelHotelRequest")
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
