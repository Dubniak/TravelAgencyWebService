package ws.travelgood.representations;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import ws.travelgood.entities.FlightWithStatus;

/**
 *
 * @author Ema
 */
@XmlRootElement()
public class FlightsRepresentation extends Representation {
    
    private List<FlightWithStatus> flightDetails;

    public List<FlightWithStatus> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(List<FlightWithStatus> flightDetails) {
        this.flightDetails = flightDetails;
    }
}
