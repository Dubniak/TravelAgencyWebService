package ws.travelgood.entities;

import javax.xml.bind.annotation.XmlRootElement;
import niceviewservice.ws.Booking;

/**
 *
 * @author eirini
 */
@XmlRootElement()
public class HotelWithStatus {
   
    private Booking hotel;
    
    private String arrivalDate;

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Booking getHotel() {
        return hotel;
    }

    public void setHotel(Booking hotel) {
        this.hotel = hotel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String status;
   
    
}