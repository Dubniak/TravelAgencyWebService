package ws.travelgood.resources;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lameduck.ws.LameDuckServiceFault;
import niceviewservice.ws.BookHotelFault;
import niceviewservice.ws.CancelHotelFault;
import niceviewservice.ws.CreditCardFaultMessage;
import ws.travelgood.LinkManager;
import ws.travelgood.representations.Link;
import ws.travelgood.entities.Itinerary;
import ws.travelgood.representations.ItineraryRepresentation;
import ws.travelgood.representations.StatusRepresentation;
import ws.travelgood.TravelGoodConstants;
import ws.travelgood.Utils;
import ws.travelgood.entities.CreditCard;
import ws.travelgood.entities.FlightWithStatus;
import ws.travelgood.entities.HotelWithStatus;
import ws.travelgood.services.LameDuckService;
import ws.travelgood.services.NiceViewService;

/**
 *
 * @author Emanuela-Maria
 */

@Path("itineraries/{id}")
public class ItineraryResource {

    static Map<String, Itinerary> itineraries = new HashMap<String, Itinerary>();

    static void reset() {
        itineraries = new HashMap<String, Itinerary>();
    }

    @GET
    @Produces(TravelGoodConstants.ITINERARY_MEDIATYPE)
    public ItineraryRepresentation getItinerary(
            @PathParam("id") String id) {

        Itinerary itinerary = itineraries.get(id);
        //releaseItinerary(itinerary);
 
        if (itinerary == null) {
            Response r = Response.
                    status(Response.Status.NOT_FOUND).
                    entity("Itinerary not found").
                    build();
            throw new NotFoundException(r);
        }
        
        
        ItineraryRepresentation itineraryRepresentation = new ItineraryRepresentation();
        itineraryRepresentation.setItinerary(itinerary);
        itineraryRepresentation.getLinks().addAll(getGetItineraryLinks(id, itinerary.getStatus()));
        return itineraryRepresentation;
    }

    @PUT
    @Consumes(TravelGoodConstants.ITINERARY_MEDIATYPE)
    @Produces(TravelGoodConstants.ITINERARY_MEDIATYPE)
    public StatusRepresentation createItinerary(
            @PathParam("id") String id, Itinerary itinerary) {

        StatusRepresentation statusRep = new StatusRepresentation();

        statusRep.setStatus(TravelGoodConstants.PLANNING_ITINERARY_STATUS);
        if (itineraries.containsKey(id)) {
            Itinerary oldItinerary = itineraries.get(id);
            if (oldItinerary.getStatus().equals(TravelGoodConstants.BOOKED_ITINERARY_STATUS)) {
                Response r = Response.
                        status(Response.Status.BAD_REQUEST).
                        entity("Can't update a booked itinerary").
                        build();
                throw new BadRequestException(r);
            } else {
                statusRep.setStatus(TravelGoodConstants.UPDATED_ITINERARY_STATUS);
            }
        }
        itinerary.setStatus(TravelGoodConstants.PLANNING_ITINERARY_STATUS);
        
        itinerary.setFlights(new ArrayList<FlightWithStatus>());
        itinerary.setHotels(new ArrayList<HotelWithStatus>());
        itineraries.put(id, itinerary);

        statusRep.getLinks().addAll(getCreateLinks(id));

        return statusRep;
    }
    
    
    @PUT
    @Path("book")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response bookItinerary(
            @PathParam("id") String id, CreditCard creditCard) throws CreditCardFaultMessage {
        
        validateItinerary(id);
       // releaseItinerary(itineraries.get(id));
        
        CreditCardInfoType cardInfoType = getCreditCardInfoType(creditCard);
        
        StatusRepresentation status = new StatusRepresentation();
        
        boolean hasFailed = false;
        
        for (FlightWithStatus f: itineraries.get(id).getFlights())
        {
            try 
            {
                if(LameDuckService.bookFlight(f.getFlight().getBookingNo(), cardInfoType))
                {
                    f.setStatus("confirmed");
                }
            }
            catch(LameDuckServiceFault l)
            {   
                hasFailed = true;
                l.printStackTrace();
                status.setStatus("Flight booking failed");
                break;
                
            }
            catch(lameduck.ws.CreditCardFaultMessage c)
            {   
                hasFailed = true;
                c.printStackTrace();
                status.setStatus("Card failed");
                break;
            }
        }    
        
        if(!hasFailed)
        {   
            for (HotelWithStatus h : itineraries.get(id).getHotels())
            {  

                try 
                {
                    if(NiceViewService.bookHotel(h.getHotel().getBookingNumber(), cardInfoType))
                    {
                        h.setStatus("confirmed");
                    }
                }
                catch(BookHotelFault b)
                {  
                    try 
                    {
                        if(NiceViewService.bookHotel(h.getHotel().getBookingNumber()+2, cardInfoType))
                        {
                            h.setStatus("confirmed");
                        }
                    }
                    catch(BookHotelFault bb)
                    {
                        hasFailed = true;
                        b.printStackTrace();
                        status.setStatus("Hotel booking failed");
                        break;
                    }
                }
                catch(CreditCardFaultMessage c)
                {   hasFailed = true;
                    c.printStackTrace();
                    status.setStatus("Card failed");
                    break;
                }
            }  
        }    
        
        if(!hasFailed)
        {   
            itineraries.get(id).setStatus("booked");
            status.setStatus("booked");  
            status.getLinks().addAll(getCreateLinks(id));
        }
        else
        {  
            for (FlightWithStatus f: itineraries.get(id).getFlights())
            {
                if (f.getStatus().equals("confirmed"))
                    f.setStatus("cancelled");
            } 
            
            for (HotelWithStatus h : itineraries.get(id).getHotels())
            {   
                if (h.getStatus().equals("confirmed"))
                    h.setStatus("cancelled");
            }  
            status.setStatus("bookFailed");
       
  
        }

        return Response.ok(status).build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response cancelItinerary(@PathParam("id") String id,
            CreditCard creditCard)
    {
        Itinerary itinerary = itineraries.get(id);
        //releaseItinerary(itinerary);
        if(itinerary == null)
        {
            Response r = Response.
                        status(Response.Status.BAD_REQUEST)
                        .entity("The itinerary does not exist.")
                        .build();
            throw new BadRequestException(r);
        }
        else 
        {
            if(!itinerary.getStatus().equals("booked"))
            {
                Response r = Response.
                            status(Response.Status.BAD_REQUEST)
                            .entity("The itinerary has to be booked.")
                            .build();
                throw new BadRequestException(r);
            }
        }
        
        StatusRepresentation statusRepresentation = new StatusRepresentation();
        CreditCardInfoType creditCardInfoType = getCreditCardInfoType(creditCard);
        
        
        for (FlightWithStatus f: itinerary.getFlights())
        {
            try 
            {
                LameDuckService.cancelFlight(f.getFlight().getBookingNo(), 
                    f.getFlight().getPrice(), creditCardInfoType);
               
                f.setStatus("cancelled");
            }
            catch(LameDuckServiceFault l)
            {
                l.printStackTrace();
                statusRepresentation.setStatus("Flight cancelling failed");
            }           
        }  
        
        for (HotelWithStatus h : itinerary.getHotels())
        {
            try 
            {
                NiceViewService.cancelHotel(h.getHotel().getBookingNumber());
                h.setStatus("cancelled");
            }
            catch(CancelHotelFault c)
            {
                c.printStackTrace();
                statusRepresentation.setStatus("Hotel cancelling failed");
            }
        } 
        
        itinerary.setStatus("cancelled");
        statusRepresentation.setStatus("cancelled");
        statusRepresentation.getLinks().addAll(getCreateLinks(id));

        
        return Response.ok(statusRepresentation).build();
    }
    
    
    @PUT
    @Path("cancelPlanning")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response cancelPlanningPhase(@PathParam("id") String id)
    {
        Itinerary itinerary = itineraries.get(id);
        //releaseItinerary(itinerary);
        
        validateItinerary(id);
        itineraries.remove(itineraries.get(id));
        StatusRepresentation statusRepresentation = new StatusRepresentation();
        statusRepresentation.setStatus("cancelled");
        statusRepresentation.getLinks().addAll(getCreateLinks(id));

        return Response.ok(statusRepresentation).build();
    }
    
   
     static List<Link> getCreateLinks(String id)
    {
        List<Link> links = new ArrayList<Link>();
        
        //get flights
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.FLIGHTS_RELATION, id));
        //get hotels
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.HOTELS_RELATION, id));
        //self
        links.add(LinkManager.getSelfLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.SELF_RELATION, id));
        //close
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.CLOSE_RELATION, id));
        //status
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.STATUS_RELATION, id));
        //update
            links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.UPDATE_RELATION, id));            
        //cancel
        links.add(LinkManager.getCancelLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.CANCEL_RELATION, id));
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //payment
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.PAYMENT_RELATION, id));       
        
        return links;
    }
     
    public static List<Link> getGetItineraryLinks(String id, String status)
    {
         List<Link> links = new ArrayList<Link>();
        
        //self
        links.add(LinkManager.getSelfLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.SELF_RELATION, id));
        //status
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.STATUS_RELATION, id));
        //payment
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.PAYMENT_RELATION, id));
        //cancel
        links.add(LinkManager.getLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.CANCEL_RELATION, id));
        if (!status.equals(TravelGoodConstants.BOOKED_ITINERARY_STATUS)) {
            //update
            links.add(LinkManager.getCancelLink(TravelGoodConstants.ITINERARY_BASE_URI,
                TravelGoodConstants.ITINERARY_RELATION_BASE,
                TravelGoodConstants.UPDATE_RELATION, id));
        }
        
        return links;
    }
    
    public CreditCardInfoType getCreditCardInfoType(CreditCard creditCard)
    {
        CreditCardInfoType cardInfoType = new CreditCardInfoType();
        cardInfoType.setName(creditCard.getName());
        cardInfoType.setNumber(creditCard.getNumber());
        CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();
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
    
   
    public void releaseItinerary(Itinerary itinerary)
    {
        if(isTerminatable(itinerary))
              itineraries.remove(itinerary.getId()); 
        return;
    }
    
    
    public boolean isTerminatable(Itinerary itinerary)
    {
        boolean isTerminatable = false;
        GregorianCalendar today = new GregorianCalendar();
        
        for(FlightWithStatus f : itinerary.getFlights())
        {
            GregorianCalendar departureDate = f.getFlight().getDepartureTime().toGregorianCalendar();
            
            if(departureDate.before(today))
            {
                isTerminatable = true;
                break;
            }
        }
        
        if(!isTerminatable)
        {
            for(HotelWithStatus h : itinerary.getHotels())
            {
                GregorianCalendar arrivalDate = Utils.toXMLGregorianCalendarDate(h.getArrivalDate()).toGregorianCalendar();

                if(arrivalDate.before(today))
                {
                    isTerminatable = true;
                    break;
                }
            }
        }
        
        return isTerminatable;
    }
}
