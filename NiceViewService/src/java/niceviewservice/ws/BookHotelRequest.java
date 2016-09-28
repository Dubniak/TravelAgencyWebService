/*
 * @author Catalin
 */
package niceviewservice.ws;


import javax.xml.bind.annotation.XmlRootElement;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;


@XmlRootElement(name = "bookHotelRequest")
public class BookHotelRequest {

    protected int bookingNo;
    
    protected CreditCardInfoType creditCardInfo;

    /**
     * Gets the value of the bookingNo property.
     * 
     * @return 
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
     * Gets the value of the creditCardInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardInfoType }
     *     
     */
    public CreditCardInfoType getCreditCardInfo() {
        return creditCardInfo;
    }

    /**
     * Sets the value of the creditCardInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardInfoType }
     *     
     */
    public void setCreditCardInfo(CreditCardInfoType value) {
        this.creditCardInfo = value;
    }

}
