package ws.travelgood.resources;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 *
 * @author Marios
 */

@Path("itineraries/reset")
public class ItineraryResetResource {

    @GET
    public String test() {
        System.out.println("test reset get");
        return "Reached the servie";
    }
    @PUT
    public void reset() {
        System.out.println("reset");
        ItineraryResource.reset();
    }
}
