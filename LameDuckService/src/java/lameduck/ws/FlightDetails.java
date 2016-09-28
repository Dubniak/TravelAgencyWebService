/**
 *
 * @author catalin
 */
package lameduck.ws;


import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FlightDetails")
public class FlightDetails {
 
    protected int bookingNo;
    protected String startLocation;
    protected String endLocation;
    protected Date departureTime;
    protected Date arrivalTime;
    protected int price;
    protected String airlineCompany;

    
    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String value) {
        this.startLocation = value;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String value) {
        this.endLocation = value;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date value) {
        this.departureTime = value;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

  
    public void setArrivalTime(Date value) {
        this.arrivalTime = value;
    }

   
    public int getBookingNo() {
        return bookingNo;
    }

    
    public void setBookingNo(int value) {
        this.bookingNo = value;
    }

   
    public int getPrice() {
        return price;
    }

   
    public void setPrice(int value) {
        this.price = value;
    }

    
    public String getAirlineCompany() {
        return airlineCompany;
    }

    
    public void setAirlineCompany(String value) {
        this.airlineCompany = value;
    }

}

    
    
    
 
