package ws.travelgood.resources;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lameduck.ws.FlightDetails;
import lameduck.ws.LameDuckServiceFault;
import ws.travelgood.LinkManager;
import ws.travelgood.TravelGoodConstants;
import ws.travelgood.entities.CreditCard;
import ws.travelgood.entities.FlightWithStatus;
import ws.travelgood.entities.Itinerary;
import ws.travelgood.representations.FlightsRepresentation;
import ws.travelgood.representations.Link;
import ws.travelgood.representations.StatusRepresentation;
import ws.travelgood.services.LameDuckService;

/**
 *
 * @author Eirini
 */

@Path("itineraries/{id}/flights")
public class FlightResource {
    
    static Map<Integer, FlightWithStatus> retrievedFlights = new HashMap<Integer, FlightWithStatus>();
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getFlights(
            @PathParam("id") String id,
            @QueryParam("departureCity") String departureCity,
            @QueryParam("arrivalCity") String arrivalCity,
            @QueryParam("date") String date)
    {
        validateItinerary(id);
        
        List<FlightDetails> flights = LameDuckService.
               getFlights(departureCity, arrivalCity, date);
        
        List<FlightWithStatus> flightsWithStatus = new ArrayList();
        for (FlightDetails f: flights)
        {   
            FlightWithStatus flightWithStatus = new FlightWithStatus();
            flightWithStatus.setFlight(f);
            flightsWithStatus.add(flightWithStatus);
        } 
        
        FlightsRepresentation flightRepresentation = new FlightsRepresentation();
        flightRepresentation.setFlightDetails(flightsWithStatus);
        
        flightRepresentation.getLinks().addAll(getGetFlightsLinks(id));
        
        setRetrievedFlights(flightsWithStatus);
        
        return Response.ok(flightRepresentation).build();
    }
   
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addFlight(@PathParam("id") String id, 
            @QueryParam("bookingNo") int bookingNo) throws LameDuckServiceFault
    {
        StatusRepresentation statusRep = new StatusRepresentation();
       
        validateItinerary(id);

        Itinerary itinerary = ItineraryResource.itineraries.get(id);
        FlightWithStatus flightWithStatus = new FlightWithStatus();
        flightWithStatus.setFlight(retrievedFlights.get(bookingNo).getFlight());
        flightWithStatus.setStatus("unconfirmed");
        itinerary.getFlights().add(flightWithStatus);
       
        statusRep.getLinks().addAll(getGetFlightsLinks(id));
        
        statusRep.setStatus("successful");

        return Response.ok(statusRep).build();
    }
    
    
    public static List<Link> getGetFlightsLinks(String id)
    {
         List<Link> links = new ArrayList<Link>();
        
        //get flights
        links.add(LinkManager.getCompositeLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.FLIGHT_RELATION_BASE,
                TravelGoodConstants.SELF_RELATION, id, TravelGoodConstants.FLIGHTS_RELATION));
        //book flight
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.FLIGHT_RELATION_BASE, id, TravelGoodConstants.BOOK_RELATION));
        //book itinerary
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.ITINERARY_RELATION_BASE, id, TravelGoodConstants.BOOK_RELATION));
        //get hotels
        links.add(LinkManager.getCompositeLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.HOTEL_RELATION_BASE, 
                TravelGoodConstants.SELF_RELATION, id, TravelGoodConstants.HOTELS_RELATION));
        //get itinerary
        links.add(LinkManager.getSelfLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.FLIGHT_RELATION_BASE, id, TravelGoodConstants.SELF_RELATION));
        //close itinerary
        links.add(LinkManager.getCancelLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.ITINERARY_RELATION_BASE, TravelGoodConstants.CANCEL_RELATION,id));
            
        //payment
        links.add(LinkManager.getCompositeLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.FLIGHT_RELATION_BASE,
                TravelGoodConstants.PAYMENT_RELATION, id, TravelGoodConstants.FLIGHTS_RELATION));
     
        return links;
    }
    
    public void setRetrievedFlights(List<FlightWithStatus> flights)
    {
        for(FlightWithStatus f: flights)
        {
            retrievedFlights.put(f.getFlight().getBookingNo(), f);
        }
    }
    
    public CreditCardInfoType getCreditCardInfoType(CreditCard creditCard)
    {
        CreditCardInfoType cardInfoType = new CreditCardInfoType();
        cardInfoType.setName(creditCard.getName());
        cardInfoType.setNumber(creditCard.getNumber());
        ExpirationDate expDate = new ExpirationDate();
        expDate.setMonth(Integer.parseInt(creditCard.getExpirationMonth()));
        expDate.setYear(Integer.parseInt(creditCard.getExpirationYear()));
        cardInfoType.setExpirationDate(expDate);
        
        return cardInfoType;
    }
    
    public void validateItinerary(String id)
    {
        if(!ItineraryResource.itineraries.containsKey(id))
        {
            Response r = Response.
                    status(Response.Status.NOT_FOUND).
                    entity("Itinerary not found.").
                    build();
            throw new NotFoundException(r);
        }
        else 
        {
            Itinerary itinerary = ItineraryResource.itineraries.get(id);
            
            if(itinerary.getStatus().equals(TravelGoodConstants.BOOKED_ITINERARY_STATUS))
            {
                Response r = Response.
                        status(Response.Status.BAD_REQUEST)
                        .entity("The itinerary has already been booked.")
                        .build();
                
                throw new BadRequestException(r);
            }
            else
            {
                if(itinerary.getStatus().equals(TravelGoodConstants.CANCELLED_ITINERARY_STATUS))
                {
                    Response r = Response.
                        status(Response.Status.BAD_REQUEST)
                        .entity("The itinerary has been cancelled.")
                        .build();
                    
                    throw new BadRequestException(r);
                }
            }
        }
    }
}
