package ws.travelgood.resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ws.travelgood.LinkManager;
import ws.travelgood.TravelGoodConstants;
import ws.travelgood.entities.Itinerary;
import ws.travelgood.representations.ItineraryRepresentation;
import ws.travelgood.representations.Link;
import ws.travelgood.representations.StatusRepresentation;

/**
 *
 * @author Eirini
 */

@Path("itineraries/{id}/status")
public class ItineraryStatusResource {

    @GET
    @Produces(TravelGoodConstants.ITINERARY_MEDIATYPE)
    public StatusRepresentation getStatus(@PathParam("id") String id) {
        StatusRepresentation response = new StatusRepresentation();
        Itinerary itinerary = ItineraryResource.itineraries.get(id);
        if (itinerary == null) {
            throw new UnsupportedOperationException();
        }
        response.setStatus(itinerary.getStatus());

        response.getLinks().addAll(getGetStatusLinks(id));

        return response;
    }
    
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(TravelGoodConstants.ITINERARY_MEDIATYPE)
    public ItineraryRepresentation cancel(@PathParam("id") String id, String newStatus) 
    {        
        Itinerary itinerary = ItineraryResource.itineraries.get(id);

        if (itinerary == null) {
            throw new NotFoundException();
        }

        if (!(newStatus.equals("cancelled") ||
                newStatus.equals("shipped"))) {
            throw new BadRequestException();
        }

        itinerary.setStatus(newStatus);

        ItineraryRepresentation response = new ItineraryRepresentation();
        response.getLinks().addAll(getGetStatusLinks(id));
        response.setItinerary(itinerary);

        return response;
    }
    
    public static List<Link> getGetStatusLinks(String id)
    {
        List<Link> links = new ArrayList<Link>();
          
        //self
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.SELF_RELATION, id));
        //payment
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.PAYMENT_RELATION, id));
        //cancel
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.CANCEL_RELATION, id));
        
        return links;
    }
}
