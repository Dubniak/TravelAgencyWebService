package ws.travelgood.entities;

import javax.xml.bind.annotation.XmlRootElement;
import lameduck.ws.FlightDetails;

/**
 *
 * @author eirini
 */
@XmlRootElement()
public class FlightWithStatus {
   
    private FlightDetails flight;

    public FlightDetails getFlight() {
        return flight;
    }

    public void setFlight(FlightDetails flight) {
        this.flight = flight;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    private String status;
    
}
