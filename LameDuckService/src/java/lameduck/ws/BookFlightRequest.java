/**
 *
 * @author catalin
 */
package lameduck.ws;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "bookFlightRequest")
public class BookFlightRequest {

    protected int bookingNo;
    protected CreditCardInfoType creditCardDetails;

   
    public int getBookingNo() {
        return bookingNo;
    }

   
    public void setBookingNo(int value) {
        this.bookingNo = value;
    }

    
    public CreditCardInfoType getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(CreditCardInfoType value) {
        this.creditCardDetails = value;
    }

}

