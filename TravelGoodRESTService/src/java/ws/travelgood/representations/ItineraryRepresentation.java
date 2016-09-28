package ws.travelgood.representations;

import javax.xml.bind.annotation.XmlRootElement;
import ws.travelgood.entities.Itinerary;

/**
 *
 * @author Emanuela-Maria
 */

@XmlRootElement()
public class ItineraryRepresentation extends Representation {

    private Itinerary itinerary;

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
