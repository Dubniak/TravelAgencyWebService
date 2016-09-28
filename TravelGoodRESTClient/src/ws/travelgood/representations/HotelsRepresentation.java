package ws.travelgood.representations;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import niceviewservice.ws.Booking;
import niceviewservice.ws.GetHotelReply;
import ws.travelgood.entities.HotelWithStatus;

/**
 *
 * @author Ema
 */
@XmlRootElement()
public class HotelsRepresentation extends Representation {
    
    private List<HotelWithStatus> hotels;

    public List<HotelWithStatus> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelWithStatus> hotels) {
        this.hotels = hotels;
    }
}
