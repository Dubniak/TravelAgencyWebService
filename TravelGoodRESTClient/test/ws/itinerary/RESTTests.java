package ws.itinerary;

import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ws.travelgood.TravelGoodConstants;
import ws.travelgood.entities.CreditCard;
import ws.travelgood.entities.FlightWithStatus;
import ws.travelgood.entities.HotelWithStatus;
import ws.travelgood.entities.Itinerary;
import ws.travelgood.representations.FlightsRepresentation;
import ws.travelgood.representations.HotelsRepresentation;
import ws.travelgood.representations.ItineraryRepresentation;
import ws.travelgood.representations.Link;
import ws.travelgood.representations.StatusRepresentation;


public class RESTTests {
    
    Client client;
    WebTarget itineraries;
    WebTarget flights;
    WebTarget hotels;
    static int itineraryId = 0;
    
    public RESTTests() {
        client = ClientBuilder.newClient();
        itineraries = client.target(TravelGoodConstants.ITINERARY_BASE_URI);
        flights = client.target(TravelGoodConstants.FLIGHTS_BASE_URI);
        hotels = client.target(TravelGoodConstants.HOTELS_BASE_URI);
    }
    
    @Before()
    public void resetItineraries() {
        itineraries.path("reset")
                .request()
                .put(Entity.entity("", MediaType.TEXT_PLAIN));
    }
    
    @Before()
    public void resetFlights() {
        flights.path("reset")
                .request()
                .put(Entity.entity("", MediaType.TEXT_PLAIN));
    }
    
    @Before()
    public void resetHotels() {
        hotels.path("reset")
                .request()
                .put(Entity.entity("", MediaType.TEXT_PLAIN));
    }
    
    public String newItineraryId() {
        return "0";
    }
    
    /**
    *
    * @author Ema
    */
    @Test
    public void P1()
    {   
        Itinerary itinerary = new Itinerary();
        itinerary.setId(newItineraryId());
        itinerary.setFlights(new ArrayList<FlightWithStatus>());
        itinerary.setHotels(new ArrayList<HotelWithStatus>());
        
        //create itinerary
        StatusRepresentation itineraryStatus = createItinerary(itinerary);
        assertEquals("PLANNING", itineraryStatus.getStatus());
        
        Link link = itineraryStatus.
                getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        //get itinerary
        ItineraryRepresentation itineraryResult = getItinerary(link);

        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        //get all flights
        FlightsRepresentation allFlights = getFlights(itinerary, "Torino", "London", "07-06-2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add first flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        int bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        StatusRepresentation flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        

        //get hotels
        HotelsRepresentation allHotels = getHotels(itinerary, "Copenhagen", "10-12-2015", "14-12-2015");
        assertEquals(2, allHotels.getHotels().size());
     
        //add first hotel
        int bookingNoHotel = allHotels.getHotels().get(0).getHotel().getBookingNumber();
        StatusRepresentation hotelStatus = addHotel(itinerary, bookingNoHotel);
        
        assertEquals("successful", hotelStatus.getStatus());
        
        //get itinerary and check if the status of the hotel is unconfirmed
        
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getHotels().size());
        
        //check that the status of the hotel is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getHotels().
              get(0).getStatus());
       
        
        //get all flights
        allFlights = getFlights(itinerary, "Copenhagen", "Bucharest", "03/01/2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add second flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(2, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
        assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        
        //get all flights
        allFlights = getFlights(itinerary, "Munchen", "Madrid", "08/11/2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add third flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(3, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
        assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        
        //get hotels
        allHotels = getHotels(itinerary, "Copenhagen", "10-12-2015", "14-12-2015");
  
        assertEquals(2, allHotels.getHotels().size());
     
       //add second hotel
        bookingNoHotel = allHotels.getHotels().get(1).getHotel().getBookingNumber();
        hotelStatus = addHotel(itinerary, bookingNoHotel);
        
        assertEquals("successful", hotelStatus.getStatus());
        
        //get itinerary and check if the status of the hotel is unconfirmed
        
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(2, itineraryResult.getItinerary().getHotels().size());
        
        //check that the status of the hotel is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getHotels().
              get(0).getStatus());
     
       
       //book itinerary
        
        link = allHotels.getLinkByRelation("http://travelgood.ws/hotels/relations/payment");
        assertNotNull(link);
        
        CreditCard creditCard = new CreditCard();
        creditCard.setName("Tick Joachim");
        creditCard.setNumber("50408824");
        creditCard.setExpirationMonth("2");
        creditCard.setExpirationYear("11");
       
        StatusRepresentation statusBook = bookItinerary(itinerary, creditCard);
        
        assertEquals("booked", statusBook.getStatus());
        
        link = statusBook.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        link.setUri(String.format("%s/%s", 
                TravelGoodConstants.ITINERARY_BASE_URI,
                itinerary.getId()));
        itineraryResult = getItinerary(link);
        
        assertEquals("booked", itineraryResult.getItinerary().getStatus());
        
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        for(FlightWithStatus f : itineraryResult.getItinerary().getFlights())
        {
            assertEquals(f.getStatus(), "confirmed");
        }
       
        for(HotelWithStatus h : itineraryResult.getItinerary().getHotels())
        {
            assertEquals(h.getStatus(), "confirmed");
        }
      
    }
    
   /**
    *
    * @author Eirini
    */
    @Test
    public void P2()
    {   
        Itinerary itinerary = new Itinerary();
        itinerary.setId(newItineraryId());
        itinerary.setFlights(new ArrayList<FlightWithStatus>());
        itinerary.setHotels(new ArrayList<HotelWithStatus>());
        
        //create itinerary
        StatusRepresentation itineraryStatus = createItinerary(itinerary);
        assertEquals("PLANNING", itineraryStatus.getStatus());
        
        Link link = itineraryStatus.
                getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        //get itinerary
        ItineraryRepresentation itineraryResult = getItinerary(link);

        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        //get all flights
        FlightsRepresentation allFlights = getFlights(itinerary, "Torino", "London", "07-06-2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add first flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        int bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        StatusRepresentation flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
        assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        
        link.setUri(String.format("%s/%s/cancelPlanning", 
                TravelGoodConstants.ITINERARY_BASE_URI,
                itinerary.getId()));
    
        StatusRepresentation status = client.target(link.getUri())
                .request()
                .accept(MediaType.APPLICATION_XML)
                .put(Entity.entity(itinerary, MediaType.APPLICATION_XML),
                        StatusRepresentation.class);
        
        assertEquals("cancelled", status.getStatus());
    }
    
    
    /**
    *
    * @author Marios
    */
    @Test
    public void B()
    {   
        Itinerary itinerary = new Itinerary();
        itinerary.setId(newItineraryId());
        itinerary.setFlights(new ArrayList<FlightWithStatus>());
        itinerary.setHotels(new ArrayList<HotelWithStatus>());
        
        //create itinerary
        StatusRepresentation itineraryStatus = createItinerary(itinerary);
        assertEquals("PLANNING", itineraryStatus.getStatus());
        
        Link link = itineraryStatus.
                getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        //get itinerary
        ItineraryRepresentation itineraryResult = getItinerary(link);

        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        //get all flights
        FlightsRepresentation allFlights = getFlights(itinerary, "Torino", "London", "07-06-2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add first flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        int bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        StatusRepresentation flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        
        //get hotels
        HotelsRepresentation allHotels = getHotels(itinerary, "Copenhagen", "10-12-2015", "14-12-2015");
  
        assertEquals(2, allHotels.getHotels().size());
     
        //add first hotel
        int bookingNoHotel = allHotels.getHotels().get(0).getHotel().getBookingNumber();
        StatusRepresentation hotelStatus = addHotel(itinerary, bookingNoHotel);
        
        assertEquals("successful", hotelStatus.getStatus());
        
        //get itinerary and check if the status of the hotel is unconfirmed
        
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getHotels().size());
        
        //check that the status of the hotel is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getHotels().
              get(0).getStatus());
       
        
        //get all flights
        allFlights = getFlights(itinerary, "Curitiba", "Tokyo", "08/05/2016");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add second flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(2, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
        assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        
        //book itinerary
        
        link = allHotels.getLinkByRelation("http://travelgood.ws/hotels/relations/payment");
        assertNotNull(link);
        
        CreditCard creditCard = new CreditCard();
        creditCard.setName("Tick Joachim");
        creditCard.setNumber("50408824");
        creditCard.setExpirationMonth("2");
        creditCard.setExpirationYear("11");
       
        StatusRepresentation statusBook = bookItinerary(itinerary, creditCard);
        
        assertEquals("bookFailed", statusBook.getStatus());
  
        link.setUri(String.format("%s/%s", 
                TravelGoodConstants.ITINERARY_BASE_URI,
                itinerary.getId()));
        itineraryResult = getItinerary(link);
        
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        int i=1;
        for(FlightWithStatus f : itineraryResult.getItinerary().getFlights())
        {
            if(i==1)
                assertEquals(f.getStatus(), "cancelled");    
            else
                assertEquals(f.getStatus(), "unconfirmed");
            i++;   
        }
       
        for(HotelWithStatus h : itineraryResult.getItinerary().getHotels())
        {
            assertEquals(h.getStatus(), "unconfirmed");
        }
    
    }
     
    
    /**
    *
    * @author Marie
    */
    @Test
    public void C1()
    {
        Itinerary itinerary = new Itinerary();
        itinerary.setId(newItineraryId());
        itinerary.setFlights(new ArrayList<FlightWithStatus>());
        itinerary.setHotels(new ArrayList<HotelWithStatus>());
        
        //create itinerary
        StatusRepresentation itineraryStatus = createItinerary(itinerary);
        assertEquals("PLANNING", itineraryStatus.getStatus());
        
        Link link = itineraryStatus.
                getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        //get itinerary
        ItineraryRepresentation itineraryResult = getItinerary(link);

        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        //get all flights
        FlightsRepresentation allFlights = getFlights(itinerary, "Torino", "London", "07-06-2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add first flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        int bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        StatusRepresentation flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        

        //get hotels
        HotelsRepresentation allHotels = getHotels(itinerary, "Copenhagen", "10-12-2015", "14-12-2015");
  
        assertEquals(2, allHotels.getHotels().size());
     
        //add first hotel
        int bookingNoHotel = allHotels.getHotels().get(0).getHotel().getBookingNumber();
        StatusRepresentation hotelStatus = addHotel(itinerary, bookingNoHotel);
        
        assertEquals("successful", hotelStatus.getStatus());
        
        //get itinerary and check if the status of the hotel is unconfirmed
        
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getHotels().size());
        
        //check that the status of the hotel is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getHotels().
              get(0).getStatus());
       
        
        //get all flights
        allFlights = getFlights(itinerary, "Copenhagen", "Bucharest", "03/01/2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add second flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(2, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
        assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        
        CreditCard creditCard = new CreditCard();
        creditCard.setName("Tick Joachim");
        creditCard.setNumber("50408824");
        creditCard.setExpirationMonth("2");
        creditCard.setExpirationYear("11");
       
        StatusRepresentation statusBook = bookItinerary(itinerary, creditCard);
        
        assertEquals("booked", statusBook.getStatus());
        
        link = statusBook.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        link.setUri(String.format("%s/%s", 
                TravelGoodConstants.ITINERARY_BASE_URI,
                itinerary.getId()));
        itineraryResult = getItinerary(link);
        
        assertEquals("booked", itineraryResult.getItinerary().getStatus());
        
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        for(FlightWithStatus f : itineraryResult.getItinerary().getFlights())
        {
            assertEquals(f.getStatus(), "confirmed");
        }
       
        for(HotelWithStatus h : itineraryResult.getItinerary().getHotels())
        {
            assertEquals(h.getStatus(), "confirmed");
        }       
        
        //cancel itinerary
        link = statusBook.getLinkByRelation("http://travelgood.ws/itineraries/relations/cancel");
        assertNotNull(link);
        
        StatusRepresentation statusCancel = cancelItinerary(itinerary, creditCard);
        
        assertEquals("cancelled", statusCancel.getStatus());
        
        link = itineraryStatus.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        itineraryResult = getItinerary(link);
        
        assertEquals("cancelled", itineraryResult.getItinerary().getStatus());
        
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        for(FlightWithStatus f : itineraryResult.getItinerary().getFlights())
        {
            assertEquals(f.getStatus(), "cancelled");
        }
        
        for(HotelWithStatus h : itineraryResult.getItinerary().getHotels())
        {
            assertEquals(h.getStatus(), "cancelled");
        }
     
    }
    
    /**
    *
    * @author Catalin
    */
    @Test
    public void C2()
    {
        Itinerary itinerary = new Itinerary();
        itinerary.setId(newItineraryId());
        itinerary.setFlights(new ArrayList<FlightWithStatus>());
        itinerary.setHotels(new ArrayList<HotelWithStatus>());
        
        //create itinerary
        StatusRepresentation itineraryStatus = createItinerary(itinerary);
        assertEquals("PLANNING", itineraryStatus.getStatus());
        
        Link link = itineraryStatus.
                getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        //get itinerary
        ItineraryRepresentation itineraryResult = getItinerary(link);

        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        //get all flights
        FlightsRepresentation allFlights = getFlights(itinerary, "Torino", "London", "07-06-2015");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add first flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        int bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        StatusRepresentation flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        

        //get hotels
        HotelsRepresentation allHotels = getHotels(itinerary, "Copenhagen", "10-12-2015", "14-12-2015");
  
        assertEquals(2, allHotels.getHotels().size());
     
        //add first hotel
        int bookingNoHotel = allHotels.getHotels().get(0).getHotel().getBookingNumber();
        StatusRepresentation hotelStatus = addHotel(itinerary, bookingNoHotel);
        
        assertEquals("successful", hotelStatus.getStatus());
        
        //get itinerary and check if the status of the hotel is unconfirmed
        
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(1, itineraryResult.getItinerary().getHotels().size());
        
        //check that the status of the hotel is unconfirmed
       assertEquals("unconfirmed", itineraryResult.getItinerary().getHotels().
              get(0).getStatus());
       
        
        //get all flights
        allFlights = getFlights(itinerary, "Rio", "NY", "08/11/2016");
       
        assertEquals(1, allFlights.getFlightDetails().size());
        
        //add second flight
        
        link = allFlights.getLinkByRelation("http://travelgood.ws/flights/relations/payment");
        assertNotNull(link);
        
        bookingNoFlight = allFlights.getFlightDetails().get(0).getFlight().getBookingNo();
        
        flightStatus = addFlight(link, bookingNoFlight, itinerary);
        
        assertEquals("successful", flightStatus.getStatus());
        
        //get itinerary and check if the status of the flight is unconfirmed
        link = itineraryResult.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);

        itineraryResult = getItinerary(link);
        
        assertNotNull(itineraryResult.getItinerary());
        assertEquals("PLANNING", itineraryResult.getItinerary().getStatus());
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        assertEquals(2, itineraryResult.getItinerary().getFlights().size());
       
        //check that the status of the flight is unconfirmed
        assertEquals("unconfirmed", itineraryResult.getItinerary().getFlights().
              get(0).getStatus());
        
        CreditCard creditCard = new CreditCard();
        creditCard.setName("Tick Joachim");
        creditCard.setNumber("50408824");
        creditCard.setExpirationMonth("2");
        creditCard.setExpirationYear("11");
       
        StatusRepresentation statusBook = bookItinerary(itinerary, creditCard);
        
        assertEquals("booked", statusBook.getStatus());
        
        link = statusBook.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        link.setUri(String.format("%s/%s", 
                TravelGoodConstants.ITINERARY_BASE_URI,
                itinerary.getId()));
        itineraryResult = getItinerary(link);
        
        assertEquals("booked", itineraryResult.getItinerary().getStatus());
        
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        
        for(FlightWithStatus f : itineraryResult.getItinerary().getFlights())
        {
            assertEquals(f.getStatus(), "confirmed");
        }
       
        for(HotelWithStatus h : itineraryResult.getItinerary().getHotels())
        {
            assertEquals(h.getStatus(), "confirmed");
        }       
        
        //cancel itinerary
        link = statusBook.getLinkByRelation("http://travelgood.ws/itineraries/relations/cancel");
        assertNotNull(link);
        
        StatusRepresentation statusCancel = cancelItinerary(itinerary, creditCard);
        
        assertEquals("cancelled", statusCancel.getStatus());
        
        link = itineraryStatus.getLinkByRelation("http://travelgood.ws/itineraries/relations/self");
        assertNotNull(link);
        
        itineraryResult = getItinerary(link);
        
        assertEquals("cancelled", itineraryResult.getItinerary().getStatus());
        
        assertEquals(itinerary.getId(), itineraryResult.getItinerary().getId());
        int i = 1;
        for(FlightWithStatus f : itineraryResult.getItinerary().getFlights())
        {   if (i==1)
                 assertEquals(f.getStatus(), "cancelled");
            else
                 assertEquals(f.getStatus(), "confirmed");
            i++;
        }
        
        for(HotelWithStatus h : itineraryResult.getItinerary().getHotels())
        {
            assertEquals(h.getStatus(), "cancelled");
        }
        
        
    }
    
    public StatusRepresentation createItinerary(Itinerary itinerary)
    {      
        StatusRepresentation itineraryStatus = client.target(TravelGoodConstants.ITINERARY_BASE_URI)
                .path(itinerary.getId())
                .request()
                .accept(TravelGoodConstants.ITINERARY_MEDIATYPE)
                .put(Entity.entity(itinerary, TravelGoodConstants.ITINERARY_MEDIATYPE)
                        ,StatusRepresentation.class);
        
        return itineraryStatus;
    
    }   
    
    public ItineraryRepresentation getItinerary(Link link)
    {
        ItineraryRepresentation itineraryResult = client.target(link.getUri()).
                request().
                accept(TravelGoodConstants.ITINERARY_MEDIATYPE).
                get(ItineraryRepresentation.class);
        return itineraryResult;
    }     
    
    public HotelsRepresentation getHotels(Itinerary itinerary, String city, 
                String arrivalDate, String departureDate)
    {
        HotelsRepresentation allHotels = client.target(TravelGoodConstants.ITINERARY_BASE_URI)
                .path(itinerary.getId())
                .path("hotels")
                .queryParam("city", city)
                .queryParam("arrivalDate", arrivalDate)
                .queryParam("departureDate", departureDate)
                .request()
                .get(HotelsRepresentation.class);
        return allHotels;
    }   
    
    public StatusRepresentation addHotel(Itinerary itinerary, int bookingNo)
    {
        StatusRepresentation status = client.target(TravelGoodConstants.ITINERARY_BASE_URI)
                .path(itinerary.getId())
                .path("hotels")
                .queryParam("bookingNo", bookingNo)
                .request()
                .accept(MediaType.APPLICATION_XML)
                .put(Entity.entity(itinerary, MediaType.APPLICATION_XML),
                        StatusRepresentation.class);
        return status;
    }    
    
    public FlightsRepresentation getFlights(Itinerary itinerary, String departureCity, 
                String arrivalCity, String date)
    {
        FlightsRepresentation allFlights = client.target(TravelGoodConstants.ITINERARY_BASE_URI)
                .path(itinerary.getId())
                .path("flights")
                .queryParam("departureCity", departureCity)
                .queryParam("arrivalCity", arrivalCity)
                .queryParam("date", date)
                .request()
                .get(FlightsRepresentation.class);
        
        return allFlights;
    }
    
    public StatusRepresentation addFlight(Link link, int bookingNo, Itinerary itinerary)
    {
        StatusRepresentation status = client.target(link.getUri())
                .queryParam("bookingNo", bookingNo)
                .request()
                .accept(MediaType.APPLICATION_XML)
                .put(Entity.entity(itinerary, MediaType.APPLICATION_XML),
                        StatusRepresentation.class);
        return status;
    }   
    
    public StatusRepresentation bookItinerary (Itinerary itinerary, CreditCard creditCard)
    {
        StatusRepresentation statusBook = client.target(TravelGoodConstants.ITINERARY_BASE_URI)
                .path(itinerary.getId())
                .path("book")
                .request()
                .accept(MediaType.APPLICATION_XML)
                .put(Entity.entity(creditCard, MediaType.APPLICATION_XML),
                        StatusRepresentation.class);
        return statusBook;
    }  
    
    public StatusRepresentation cancelItinerary (Itinerary itinerary, CreditCard creditCard)
    {
        StatusRepresentation statusCancel = client.target(TravelGoodConstants.ITINERARY_BASE_URI)
                .path(itinerary.getId())
                .request()
                .accept(MediaType.APPLICATION_XML)
                .put(Entity.entity(creditCard, MediaType.APPLICATION_XML),
                        StatusRepresentation.class);
        return statusCancel;      
    }
 
}
