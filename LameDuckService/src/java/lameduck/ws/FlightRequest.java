/**
 *
 * @author catalin
 */
package lameduck.ws;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="FlightRequest")
public class FlightRequest {

 
    protected String startLocation;
    protected String endLocation;
    protected Date flightDate;

    
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

    public Date getFlightDate() {
        return flightDate;
    }

  
    public void setFlightDate(Date value) {
        this.flightDate = value;
    }

}

