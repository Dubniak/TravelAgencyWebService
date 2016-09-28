/**
 *
 * @author Marios - Marie
 */
package holder.ws;

import java.util.Date;

public class HotelAttributes {
    
    protected String name;
    protected String reservationService; //id
    protected int bookingNo;
    protected HotelAddress address;
    protected double price;
    protected boolean guarantee;
    protected String status; 
    protected Date startDate;

    /*
    Status possible values = ask ; add ; book; cancel; 
    */
    
    public HotelAttributes(){
        address = new HotelAddress();
    }
    
    public void setBookingNo(int bno){
        bookingNo = bno;
    }
    
    public int getBookingNo(){
        return this.bookingNo;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setReservationService(String reservationService) {
        this.reservationService = reservationService;
    }

    public void setAddress(HotelAddress address) {
        this.address = address;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGuarantee(boolean guarantee) {
        this.guarantee = guarantee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(Date date) {
        startDate = date;
    }
    
    public String getName() {
        return name;
    }

    public String getReservationService() {
        return reservationService;
    }

    public HotelAddress getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public boolean isGuarantee() {
        return guarantee;
    }

    public String getStatus() {
        return status;
    }
    
    public Date getDate() {
        return startDate;
    }
    
    
}
