/**
 *
 * @author catalin
 */
package lameduck.ws;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "cancelFlightRequest")
public class CancelFlightRequest {

    protected int bookingNo;
    protected int flightPrice;
   
    protected CreditCardInfoType creditCardDetails;

   
    public int getBookingNo() {
        return bookingNo;
    }

   
    public void setBookingNo(int value) {
        this.bookingNo = value;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(int value) {
        this.flightPrice = value;
    }

    public CreditCardInfoType getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(CreditCardInfoType value) {
        this.creditCardDetails = value;
    }

}
