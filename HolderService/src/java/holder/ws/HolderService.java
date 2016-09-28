/**
 *
 * @author Marios - Marie
 */
package holder.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "HolderService")
public class HolderService {
    
   static List<Client> clients = new ArrayList<Client>();
    
    /**
     * storeFlight
     * @param flight 
     * @param flightId 
     */
   
   @WebMethod(operationName = "destroy")
   public void destroy(@WebParam (name = "destroyOrder") String cId){
       System.out.println("Destroying");
       int i=0;
       int index=-1;
       for(Client client : clients){
           if(client.getClientId().equalsIgnoreCase(cId)){
               index=i;
           }
       }
       if(index>=0){
            clients.remove(index);
       }
       
   }
   
   @WebMethod(operationName = "getDeadLine")
   public Date getDeadLine(@WebParam (name = "client") String cId, @WebParam (name = "itinerary") String itID) throws ParseException{
       System.out.println("Returning itinerary deadline");
       Date deadLine = new Date();
       for (Client client:clients){
            if (client.getClientId().equalsIgnoreCase(cId)){
                for (Itinerary it : client.getItinerary()){
                    deadLine = it.getFlights().get(0).getDepartureTime();
                    for (FlightAttributes flight:it.getFlights()){
                        if(flight.getDepartureTime().before(deadLine)){
                            deadLine=flight.getDepartureTime();
                        }                                                
                    }
                    for (HotelAttributes ha: it.getHotels()){
                        if(ha.getDate().before(deadLine)){
                            deadLine = ha.getDate();
                        }
                    }
                }
            }
        }
       return deadLine;
   }
   
    @WebMethod(operationName = "storeClient") //OK
    public boolean storeClient(@WebParam(name = "clientId") String cId) {
        System.out.println(cId);
        
        Client client = new Client();
        client.setClientId(cId);
        clients.add(client);
        if (clients.size()>0)
            return true;
        return false;
       
    }
    
    @WebMethod (operationName="setHotelStatus")
    public void setHotelStatus(@WebParam(name="ClientId") String cId,@WebParam(name="ItineraryId") String iId,
            @WebParam(name="HotelReservation") int book,@WebParam(name="HotelStatus") String status){
       for (Client client : clients) {
           if (client.getClientId().equalsIgnoreCase(cId)){
               for (Itinerary itinerary : client.getItinerary()) {
                   if (itinerary.getItineraryId().equalsIgnoreCase(iId)){
                       for (HotelAttributes hotel : itinerary.getHotels()) {
                           //System.out.println("SHS:Hotel " + hotel.getReservationService() + "??" + hres);
                           if (hotel.getBookingNo()==book){
                               System.out.println("SHS: set to : " + status);
                               hotel.setStatus(status);
                           }
                       }
                   }
               }
           }
       }
    }
    
    @WebMethod (operationName="setFlightStatus")
    public void setFlightStatus(@WebParam(name="ClientId") String cId,@WebParam(name="ItineraryId") String iId,
            @WebParam(name="FlightBookingNo") int no,@WebParam(name="FlightStatus") String status){
       for (Client client : clients) {
           if (client.getClientId().equalsIgnoreCase(cId)){
               for (Itinerary itinerary : client.getItinerary()) {
                   if (itinerary.getItineraryId().equalsIgnoreCase(iId)){
                       for(FlightAttributes flight : itinerary.getFlights()) {
                           if (flight.getBookingNo() == no){
                               System.out.println("SFS:SETTING STATUS :" + status);
                               flight.setStatus(status);
                           }
                       }
                   }
               }
           }
       }
    }
    
    @WebMethod(operationName = "storeItinerary") //OK
    public boolean createItinerary(@WebParam(name="ClientId") String cId,@WebParam(name="ItineraryId") String iId){
        Itinerary it = new Itinerary();
        it.setItineraryId(iId);
        if (it.getFlights() == null || it.getHotels()==null)
            System.out.println("Itinerary is null");
       
        for (Client client: clients){
            System.out.println("Searching.Client= "+client.getClientId());
            if (client.getClientId().equalsIgnoreCase(cId)){
                client.addItinerary(it);
                return true;
            }
        }
        return false;
    }
    
    @WebMethod(operationName = "createFlightAttributes") //OK
    public boolean createFlightAttributes(@WebParam(name="ClientId") String cId,@WebParam(name="ItineraryId") String iId,@WebParam(name="bookingNo") int bno,@WebParam(name="startLocation") String start,
                        @WebParam(name="endLocation") String end, @WebParam(name="departureTime") Date dtime,
                        @WebParam(name="arrivalTime")Date atime,@WebParam(name="airline") String airline,
                        @WebParam(name="price") int price){
        FlightAttributes flight = new FlightAttributes();
        flight.setBookingNo(bno);
        flight.setStartLocation(start);
        flight.setEndLocation(end);
        flight.setDepartureTime(dtime);
        flight.setArrivalTime(atime);
        flight.setAirlineCompany(airline);
        flight.setPrice(price);
        flight.setStatus("ask");
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId)){
                System.out.println("CFA:Found client: " + client.getClientId());
                for (Itinerary itinerary : client.getItinerary()) {
                    if (itinerary.getItineraryId().equalsIgnoreCase(iId)){
                        System.out.println("CFA:Found itiner: " + itinerary.getItineraryId());
                        itinerary.addFlight(flight);
                        System.out.println("CFA Status: " +flight.getStatus());
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    
    @WebMethod(operationName = "createHotelAttributes")
    public boolean createHotelAttributes(@WebParam(name="ClientId") String cId,@WebParam(name="ItineraryId") String iId,@WebParam(name="name") String name,@WebParam(name="reservationService") String reserv,
                        @WebParam(name="price") double pr,@WebParam(name="Guarantee") boolean guar,
                        @WebParam(name="AddCountry") String country,@WebParam(name="AddCity") String city,@WebParam(name="AddStreetName") String sname,
                        @WebParam(name="AddStreetNum") int strnum,@WebParam(name="AddZipCode") int zip,@WebParam(name="BookingNo") int bno, @WebParam(name="start") Date date){
        HotelAttributes hotel = new HotelAttributes();
        HotelAddress add = new HotelAddress();
        add.setCity(city);
        add.setCountry(country);
        add.setNumber(strnum);
        add.setStreetName(sname);
        add.setZipCode(zip);
        hotel.setBookingNo(bno);
        hotel.setAddress(add);
        hotel.setGuarantee(guar);
        hotel.setName(name);
        hotel.setPrice(pr);
        hotel.setReservationService(reserv);
        hotel.setStatus("ask");
        hotel.setDate(date);
        for (Client client : clients){
            if (client.getClientId().equalsIgnoreCase(cId)){
                for (Itinerary it : client.getItinerary()){
                    if (it.getItineraryId().equalsIgnoreCase(iId)){
                        it.addHotel(hotel);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @WebMethod (operationName = "getFlightPrice")
    public int getFlightPrice(@WebParam(name="ClientId") String cId,@WebParam(name="ItineraryId") String iId,@WebParam(name="bookingNo") int bno){
        System.out.println("Asking for flight price");
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId)){
                System.out.println("GFS:Client Found " + client.getClientId());
                for (Itinerary itinerary : client.getItinerary()) {
                    if (itinerary.getItineraryId().equalsIgnoreCase(iId)){
                        System.out.println("GFS:Itinerary Found " + itinerary.getItineraryId());
                        for (FlightAttributes flight : itinerary.getFlights()) {
                            System.out.println("GFS:BOOK NO " + flight.getBookingNo() + "??" + bno);
                            if (flight.getBookingNo() == bno){
                                System.out.println("GFS:status :" + flight.getStatus() );
                                return flight.getPrice();
                            }
                        }
                        
                    }
                }
            }
        }
        return 0;
    }
    
    @WebMethod (operationName = "getHotelStatus")
    public String getHotelStatus(@WebParam(name="ClientId") String cId,@WebParam(name="ItineraryId") String iId,@WebParam(name="reservationServise") String res){
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId)){
                System.out.println("GHS:Client Found " + client.getClientId());
                for (Itinerary itinerary : client.getItinerary()) {
                    if (itinerary.getItineraryId().equalsIgnoreCase(iId)){
                        System.out.println("GHS:Itinerary Found " + itinerary.getItineraryId());
                        int i=0 ;
                        for (HotelAttributes ha:itinerary.getHotels()){
                            System.out.println("GHS"+i+++": " + ha.getName() + ",Status: " + ha.getStatus());
                            System.out.println(ha.getReservationService() + " ?? " + res );
                            if (ha.getReservationService().equalsIgnoreCase(res)){
                                System.out.println("GHS: Returning " + ha.getStatus());
                                
                                return ha.getStatus();
                            }
                        }
                            
                    }
                }
            }
        }
        return null;
    }
    
    @WebMethod (operationName = "getItinerariesId")
    public List<String> getItinerariesId (@WebParam (name = "ClientId") String cId){
        List <String> list = new ArrayList<String>();
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId)){
                for (Itinerary it : client.getItinerary())
                    list.add(it.getItineraryId());
            }
        }
        return list;
    }
    
    @WebMethod (operationName = "getFlightsId")
    public List getFlightsId (@WebParam (name = "ClientId") String cId,@WebParam(name = "ItineraryId") String iId ){
        List list = new ArrayList();
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId))
                for (Itinerary it : client.getItinerary())
                    if (it.getItineraryId().equalsIgnoreCase(iId))
                        for (FlightAttributes fl : it.getFlights())
                            list.add(fl.getBookingNo());
        }
        return list;
    }
    
    @WebMethod (operationName = "getFlightsIdByStatus")
    public List<Integer> getFlightsIdByStatus (@WebParam (name = "ClientId") String cId,
                                      @WebParam(name = "ItineraryId") String iId,@WebParam(name="Status") String status){
        System.out.println("Getting flight ID");
        List<Integer> list = new ArrayList();
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId))
                for (Itinerary it : client.getItinerary())
                    if (it.getItineraryId().equalsIgnoreCase(iId))
                        for (FlightAttributes fl : it.getFlights())
                            if (fl.getStatus().equalsIgnoreCase(status))
                                list.add(fl.getBookingNo());
            
        }
        

        return list;
    }
    
    
    @WebMethod (operationName = "getHotelsBookNo")
    public List<Integer> getHotelsBookNo (@WebParam (name = "ClientId") String cId,@WebParam(name = "ItineraryId") String iId ){
        
        List list = new ArrayList();
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId))
                for (Itinerary it : client.getItinerary())
                    if (it.getItineraryId().equalsIgnoreCase(iId))
                        for (HotelAttributes ha : it.getHotels())
                            list.add(ha.getBookingNo());
        }
        return list;
    }
    
    @WebMethod (operationName = "getHotelsBookNoByStatus")
    public List<Integer> getHotelsBookNoByStatus (@WebParam (name = "ClientId") String cId,
                        @WebParam(name = "ItineraryId") String iId,@WebParam(name="Status") String status){
        System.out.println("Getting hotels booking nb");
        List list = new ArrayList();
        for (Client client: clients){
            if (client.getClientId().equalsIgnoreCase(cId))
                for (Itinerary it : client.getItinerary())
                    if (it.getItineraryId().equalsIgnoreCase(iId))
                        for (HotelAttributes ha : it.getHotels())
                            if (ha.getStatus().equalsIgnoreCase(status))
                                list.add(ha.getBookingNo());
        }
        return list;
    }
    
    @WebMethod (operationName = "getItineraryAsString")
    public List<String> getItineraryAsString (@WebParam (name="ClientId") String cId,
                                                @WebParam (name="ItineraryId") String iId){
        System.out.println("Getting itinerary");
        List<String> list = new ArrayList<String>();
        for (Client client:clients){
            if (client.getClientId().equalsIgnoreCase(cId)){
                list.add("Client: "+client.getClientId());
                for (Itinerary it : client.getItinerary()){
                    list.add("Itinerary: " + it.getItineraryId());
                    for (FlightAttributes flight:it.getFlights()){
                        if(!flight.getStatus().equals("ask")){
                            list.add("Flight Details");
                            list.add("Company: "+flight.getAirlineCompany());
                            list.add("Booking No: "+Integer.toString(flight.getBookingNo()));
                            list.add("From: "+flight.getStartLocation());
                            list.add("To: " +flight.getEndLocation());
                            list.add("Depart at: "+flight.getDepartureTime().toString());
                            list.add("Arrive at: "+flight.getArrivalTime().toString());
                            list.add("Price: "+Double.toString(flight.getPrice()));
                            list.add("Status: "+flight.getStatus());
                            list.add("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        }
                                                
                    }
                    for (HotelAttributes ha: it.getHotels()){
                        if(!ha.getStatus().equals("ask")){
                            list.add("Hotel Details");
                            list.add("Name: "+ha.getName());
                            list.add("Booking No: "+Integer.toString(ha.getBookingNo()));
                            list.add("Reservation Service: "+ha.getReservationService());
                            list.add("Price: "+Double.toString(ha.getPrice()));
                            list.add("Start date: "+ ha.getDate().toString());
                            HotelAddress addr = ha.getAddress();
                            list.add("City: "+addr.getCity());
                            list.add("Country: "+addr.getCountry());
                            list.add("Street: "+addr.getStreetName() + ", "+Integer.toString(addr.getNumber()));
                            list.add("ZIP :"+Integer.toString(addr.getZipCode()));
                            list.add("Status: "+ha.getStatus());
                            list.add("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        }
                    }
                }
            }
        }
        return list;
    }
    
    
}
