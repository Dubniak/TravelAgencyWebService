package ws.travelgood.entities;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marios
 */
@XmlRootElement()
public class Itinerary {
    
    private String id;
    
    private String status;
    
    private List<FlightWithStatus> flights;
    
    private List<HotelWithStatus> hotels;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FlightWithStatus> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightWithStatus> flights) {
        this.flights = flights;
    }

    public List<HotelWithStatus> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelWithStatus> hotels) {
        this.hotels = hotels;
    }

}
