/**
 *
 * @author Marios - Marie
 */
package holder.ws;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name= "Itinerary")
public class Itinerary {
    protected String itineraryId;
    protected List<FlightAttributes> flights  ;
    protected List<HotelAttributes> hotels ;

    public Itinerary(){
        flights = new ArrayList<>();
        hotels = new ArrayList<>();
    }
    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }
    
    public void setFlights(List<FlightAttributes> value){
        this.flights = value;
    }
    
    public void setHotels(List<HotelAttributes> value){
        this.hotels = value;
    }

    public void addFlight(FlightAttributes flight) {
        this.flights.add(flight);
    }

    public void addHotel(HotelAttributes hotel) {
        this.hotels.add(hotel);
    }

    public String getItineraryId() {
        return itineraryId;
    }

    public List<FlightAttributes> getFlights() {
        return flights;
    }

    public List<HotelAttributes> getHotels() {
        return hotels;
    }
}
