package ws.travelgood;

/**
 *
 * @author Ema
 */
public class TravelGoodConstants {
    
    //ITINERARY_STATUS
    
    public static final String PLANNING_ITINERARY_STATUS = "PLANNING";
    
    public static final String BOOKED_ITINERARY_STATUS = "BOOKED";
    
    public static final String CANCELLED_ITINERARY_STATUS = "CANCELLED"; 
    
    public static final String UPDATED_ITINERARY_STATUS = "UPDATED"; 
    
//flight and hotel status
    
    public static final String UNCONFIRMED_STATUS = "unconfirmed";
    
    public static final String CONFIRMED_STATUS = "confirmed";
    
    public static final String CANCELLED_STATUS = "cancelled";
    
    //BASE_URIs
    
    public static final String INITIAL_STATE = "http://localhost:8080/TravelGoodREST";
    
    public static final String ITINERARY_BASE_URI = "http://localhost:8080/op/webresources/itineraries";
    
    public static final String FLIGHTS_BASE_URI = "http://localhost:8080/TravelGoodREST/webresources/flights";
    
    public static final String HOTELS_BASE_URI = "http://localhost:8080/TravelGoodREST/webresources/hotels";
    
    //RELATION_BASES
    
    public static final String ITINERARY_RELATION_BASE = "http://travelgood.ws/itineraries/relations/";
    
    public static final String FLIGHT_RELATION_BASE = "http://travelgood.ws/flights/relations/";
    
    public static final String HOTEL_RELATION_BASE = "http://travelgood.ws/hotels/relations/";
    
    //RELATIONS
    
    public static final String STATUS_RELATION = "status";
    
    public static final String BOOK_RELATION = "book";
    
    public static final String CANCEL_RELATION = "cancel";
    
    public static final String SELF_RELATION = "self";
    
    public static final String GET_RELATION = "get";
    
    public static final String PAYMENT_RELATION = "payment";
    
    public static final String UPDATE_RELATION = "update";
    
    public static final String CLOSE_RELATION = "close";
    
    public static final String FLIGHTS_RELATION = "flights";
    
    public static final String HOTELS_RELATION = "hotels";
    
    //MEDIATYPES
    
    public static final String ITINERARY_MEDIATYPE = "application/itinerary+xml";
    
    public static final String FLIGHT_MEDIATYPE = "application/flight+xml";
    
    public static final String HOTEL_MEDIATYPE = "application/hotel+xml";
    
    //STATUS 
    
    public static final String STATUS_UPDATED = "updated";
    
    public static final String STATUS_BOOKING_CONFIRMED = "booked";
    
    public static final String STATUS_BOOKING_CANCELLED = "cancelled";
    
    public static final String STATUS_BOOKING_TERMINATED = "terminated";
}
