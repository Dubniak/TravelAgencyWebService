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
import niceviewservice.ws.BookHotelFault;
import niceviewservice.ws.Booking;
import niceviewservice.ws.GetHotelReply;
import ws.travelgood.LinkManager;
import ws.travelgood.TravelGoodConstants;
import ws.travelgood.entities.CreditCard;
import ws.travelgood.entities.HotelWithStatus;
import ws.travelgood.entities.Itinerary;
import ws.travelgood.representations.HotelsRepresentation;
import ws.travelgood.representations.Link;
import ws.travelgood.representations.StatusRepresentation;
import ws.travelgood.services.NiceViewService;

/**
 *
 * @author Emanuela-Maria
 */

@Path("itineraries/{id}/hotels")
public class HotelsResource {
    
    
    static Map<Integer, HotelWithStatus> retrievedHotels = new HashMap<Integer, HotelWithStatus>();
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getHotels(
            @PathParam("id") String id,
            @QueryParam("city") String city,
            @QueryParam("arrivalDate") String arrivalDate,
            @QueryParam("departureDate") String departureDate) 
    
    {        
         validateItinerary(id);
        
        GetHotelReply reply = new GetHotelReply();
        reply = NiceViewService.getHotels(city, arrivalDate, departureDate);
        List<Booking> hotels = reply.getBookingList();
        
        List<HotelWithStatus> hotelsWithStatus = new ArrayList();
        for (Booking h: hotels)
        {   
            HotelWithStatus hotelWithStatus = new HotelWithStatus();
            hotelWithStatus.setHotel(h);
            hotelWithStatus.setArrivalDate(arrivalDate);
            hotelsWithStatus.add(hotelWithStatus);
        } 
       
        HotelsRepresentation hotelsRepresentation = new HotelsRepresentation();
        hotelsRepresentation.setHotels(hotelsWithStatus);
        
        setRetrievedHotels(hotelsWithStatus);
        hotelsRepresentation.getLinks().addAll(getGetHotelLinks(id));

        return Response.ok(hotelsRepresentation).build();
        
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addHotel(@PathParam("id") String id, 
            @QueryParam("bookingNo") int bookingNo) throws BookHotelFault
    {
        StatusRepresentation statusRep = new StatusRepresentation();
       
        validateItinerary(id);

        Itinerary itinerary = ItineraryResource.itineraries.get(id);
        HotelWithStatus hotelWithStatus = new HotelWithStatus();
        hotelWithStatus.setHotel(retrievedHotels.get(bookingNo).getHotel());
        hotelWithStatus.setStatus("unconfirmed");
        itinerary.getHotels().add(hotelWithStatus);
       
        statusRep.getLinks().addAll(getGetHotelLinks(id));
        
        statusRep.setStatus("successful");

        return Response.ok(statusRep).build();
    }
    
    
   
    
    public static List<Link> getGetHotelLinks(String id)
    {
         List<Link> links = new ArrayList<Link>();
        
        //get hotels
        links.add(LinkManager.getCompositeLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.HOTEL_RELATION_BASE,
                TravelGoodConstants.SELF_RELATION, id, TravelGoodConstants.HOTELS_RELATION));
        //book hotel
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.HOTEL_RELATION_BASE, id, TravelGoodConstants.BOOK_RELATION));
        //book itinerary
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.ITINERARY_RELATION_BASE, id, TravelGoodConstants.BOOK_RELATION));
        //get flights
        links.add(LinkManager.getCompositeLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.FLIGHT_RELATION_BASE, 
                TravelGoodConstants.SELF_RELATION, id, TravelGoodConstants.FLIGHTS_RELATION));
        //get itinerary
        links.add(LinkManager.getSelfLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.HOTEL_RELATION_BASE, id, TravelGoodConstants.SELF_RELATION));
        //close itinerary
        links.add(LinkManager.getCancelLink(TravelGoodConstants.ITINERARY_BASE_URI, 
                TravelGoodConstants.ITINERARY_RELATION_BASE, TravelGoodConstants.CANCEL_RELATION,id));
            
        //payment
        links.add(LinkManager.getCompositeLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.HOTEL_RELATION_BASE,
                TravelGoodConstants.PAYMENT_RELATION, id, TravelGoodConstants.HOTELS_RELATION));
     
        return links;
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
    
    public void setRetrievedHotels(List<HotelWithStatus> hotels)
    {
        for(HotelWithStatus h: hotels)
        {
            retrievedHotels.put(h.getHotel().getBookingNumber(), h);
        }
    }
    
}
