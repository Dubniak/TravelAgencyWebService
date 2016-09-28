/**
 *
 * @author Marie , Marios , Catalin
 */
package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import lameduck.ws.FlightDetails;
import lameduck.ws.FlightRequest;
import lameduck.ws.GetFlights;
import niceviewservice.ws.Booking;
import niceviewservice.ws.GetHotelRequest;
import niceviewservice.ws.GetHotels;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.travel.FlightsAddInputElement;
import ws.travel.FlightsGetInputElement;
import ws.travel.FlightsGetOutputElement;
import ws.travel.HotelAddInputElement;
import ws.travel.HotelsGetInputElement;
import ws.travel.HotelsGetOutputElement;
import ws.travel.ItineraryBookFault;
import ws.travel.ItineraryBookInputElement;
import ws.travel.ItineraryGetOutputElement;


public class MandatoryTests {
    
    public MandatoryTests() {
    }

    
    
    @Test
    public void P1() throws ItineraryBookFault{
        //Create itinerary
        String client = "Catalin";
        client = itineraryCreate(client);
        
        //Plan flight
            //Get a list of flights
            FlightsGetInputElement flightGetIn = new FlightsGetInputElement();
            flightGetIn.setItineraryID(client);
            GetFlights gFlights = new GetFlights();
            String deptDate = "07/06/2015";
            FlightRequest fReq = new FlightRequest();
            fReq.setStartLocation("Torino");
            fReq.setEndLocation("London");
            fReq.setFlightDate(toXMLCalendarDate(deptDate));
            gFlights.setFlightsReq(fReq);
            flightGetIn.setGetFlights(gFlights);

            FlightsGetOutputElement flightGetOut = flightsGet(flightGetIn);
            List<FlightDetails> flightList = flightGetOut.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn = new FlightsAddInputElement();
            fAddIn.setBookingNumber(flightList.get(0).getBookingNo());
            fAddIn.setItineraryID(client);
            boolean fAddOut = flightAdd(fAddIn);
            assertEquals(fAddOut, true);
            
        //Plan hotel
            //Ask for a list of hotels
            HotelsGetInputElement hGetIn = new HotelsGetInputElement();
            hGetIn.setItineraryID(client);
            GetHotels hGet = new GetHotels();
            GetHotelRequest hGetReq = new GetHotelRequest();
            hGetReq.setCity("Copenhagen");
            hGetReq.setArrivalDate(toXMLCalendarDate("09/06/2015"));
            hGetReq.setDepartureDate(toXMLCalendarDate("12/06/2015"));
            hGet.setArg0(hGetReq);
            hGetIn.setGetHotels(hGet);

            HotelsGetOutputElement hGetOut = hotelsGet(hGetIn);
            List<Booking> hotelsList = hGetOut.getGetHotelsResponse().getReturn().getBookingList();
            
            //Add hotel to itinerary
            HotelAddInputElement hAddIn = new HotelAddInputElement();
            hAddIn.setItineraryID(client);
            hAddIn.setBookingNb(hotelsList.get(0).getBookingNumber());
            boolean hAddOut = hotelAdd(hAddIn);
            assertEquals(hAddOut, true);   
        
        //Plan second flight
            //Get flight list
            FlightsGetInputElement flightGetIn2 = new FlightsGetInputElement();
            flightGetIn2.setItineraryID(client);
            GetFlights gFlights2 = new GetFlights();
            String deptDate2 = "03/01/2015";
            FlightRequest fReq2 = new FlightRequest();
            fReq2.setStartLocation("Copenhagen");
            fReq2.setEndLocation("Bucharest");
            fReq2.setFlightDate(toXMLCalendarDate(deptDate2));
            gFlights2.setFlightsReq(fReq2);
            flightGetIn2.setGetFlights(gFlights2);

            FlightsGetOutputElement flightGetOut2 = flightsGet(flightGetIn2);
            List<FlightDetails> flightList2 = flightGetOut2.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn2 = new FlightsAddInputElement();
            fAddIn2.setBookingNumber(flightList2.get(0).getBookingNo());
            fAddIn2.setItineraryID(client);
            boolean fAddOut2 = flightAdd(fAddIn2);
            assertEquals(fAddOut2, true);
        
        //Plan third flight
            //Get flight list
            FlightsGetInputElement flightGetIn3 = new FlightsGetInputElement();
            flightGetIn3.setItineraryID(client);
            GetFlights gFlights3 = new GetFlights();
            String deptDate3 = "07/06/2015";
            FlightRequest fReq3 = new FlightRequest();
            fReq3.setStartLocation("Munchen");
            fReq3.setEndLocation("Madrid");
            fReq3.setFlightDate(toXMLCalendarDate(deptDate3));
            gFlights3.setFlightsReq(fReq3);
            flightGetIn3.setGetFlights(gFlights3);

            FlightsGetOutputElement flightGetOut3 = flightsGet(flightGetIn3);
            List<FlightDetails> flightList3 = flightGetOut3.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn3 = new FlightsAddInputElement();
            fAddIn3.setBookingNumber(flightList3.get(0).getBookingNo());
            fAddIn3.setItineraryID(client);
            boolean fAddOut3 = flightAdd(fAddIn3);
            assertEquals(fAddOut3, true);
            
        //Plan second hotel
            //Add second hotel to itinerary
            HotelAddInputElement hAddIn2 = new HotelAddInputElement();
            hAddIn2.setItineraryID(client);
            hAddIn2.setBookingNb(hotelsList.get(1).getBookingNumber());
            boolean hAddOut2 = hotelAdd(hAddIn2);
            assertEquals(hAddOut2, true); 
        
            
        //Get itinerary and check that all status are 'unconfirmed'
            System.out.println("Getting itinerary");
        List<String> itGetOut = itineraryGet(client).getAnswer();
        for(String ligne: itGetOut){
            System.out.println(ligne);
            if(ligne.startsWith("Status")){
                assertTrue(ligne.endsWith("unconfirmed"));
            }
        }
            
        //Book itinerary
        ItineraryBookInputElement bookIn = new ItineraryBookInputElement();
        bookIn.setItineraryID(client);
        int month = 2;
        int year = 11;
        bookIn.setMonth(month);
        bookIn.setYear(year);
        String name = "Tick Joachim";
        String number = "50408824";
        bookIn.setName(name);
        bookIn.setNumber(number);
        
        boolean bookOut = itineraryBook(bookIn);        
        assertTrue(bookOut);
        
        //Get itinerary and check that all status are now confirmed
        itGetOut = itineraryGet(client).getAnswer();
        for(String ligne: itGetOut){
            System.out.println(ligne);
            if(ligne.startsWith("Status")){
                assertTrue(ligne.endsWith("confirmed"));
            }
        }
    }
    
    @Test
    public void P2(){
        //Create itinerary
        String client = "Marios";
        client = itineraryCreate(client);
        
        //Plan flight
            //Get a list of flights
            FlightsGetInputElement flightGetIn = new FlightsGetInputElement();
            flightGetIn.setItineraryID(client);
            GetFlights gFlights = new GetFlights();
            String deptDate = "07/06/2015";
            FlightRequest fReq = new FlightRequest();
            fReq.setStartLocation("Torino");
            fReq.setEndLocation("London");
            fReq.setFlightDate(toXMLCalendarDate(deptDate));
            gFlights.setFlightsReq(fReq);
            flightGetIn.setGetFlights(gFlights);

            FlightsGetOutputElement flightGetOut = flightsGet(flightGetIn);
            List<FlightDetails> flightList = flightGetOut.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn = new FlightsAddInputElement();
            fAddIn.setBookingNumber(flightList.get(0).getBookingNo());
            fAddIn.setItineraryID(client);
            boolean fAddOut = flightAdd(fAddIn);
            assertEquals(fAddOut, true);
            
        //Cancel planning
        boolean cancelOut = itineraryCancel(client);
        assertTrue(cancelOut);
    }

    @Test
    public void C1() throws ItineraryBookFault{
        //Create itinerary
        String client = "Eirine";
        client = itineraryCreate(client);
        
        //Plan flight
            //Get a list of flights
            FlightsGetInputElement flightGetIn = new FlightsGetInputElement();
            flightGetIn.setItineraryID(client);
            GetFlights gFlights = new GetFlights();
            String deptDate = "07/06/2015";
            FlightRequest fReq = new FlightRequest();
            fReq.setStartLocation("Torino");
            fReq.setEndLocation("London");
            fReq.setFlightDate(toXMLCalendarDate(deptDate));
            gFlights.setFlightsReq(fReq);
            flightGetIn.setGetFlights(gFlights);

            FlightsGetOutputElement flightGetOut = flightsGet(flightGetIn);
            List<FlightDetails> flightList = flightGetOut.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn = new FlightsAddInputElement();
            fAddIn.setBookingNumber(flightList.get(0).getBookingNo());
            fAddIn.setItineraryID(client);
            boolean fAddOut = flightAdd(fAddIn);
            assertEquals(fAddOut, true);
            
        //Plan hotel
            //Ask for a list of hotels
            HotelsGetInputElement hGetIn = new HotelsGetInputElement();
            hGetIn.setItineraryID(client);
            GetHotels hGet = new GetHotels();
            GetHotelRequest hGetReq = new GetHotelRequest();
            hGetReq.setCity("Copenhagen");
            hGetReq.setArrivalDate(toXMLCalendarDate("09/06/2015"));
            hGetReq.setDepartureDate(toXMLCalendarDate("12/06/2015"));
            hGet.setArg0(hGetReq);
            hGetIn.setGetHotels(hGet);

            HotelsGetOutputElement hGetOut = hotelsGet(hGetIn);
            List<Booking> hotelsList = hGetOut.getGetHotelsResponse().getReturn().getBookingList();
            
            //Add hotel to itinerary
            HotelAddInputElement hAddIn = new HotelAddInputElement();
            hAddIn.setItineraryID(client);
            hAddIn.setBookingNb(hotelsList.get(0).getBookingNumber());
            boolean hAddOut = hotelAdd(hAddIn);
            assertEquals(hAddOut, true);   
        
        //Plan second flight
            //Get flight list
            FlightsGetInputElement flightGetIn2 = new FlightsGetInputElement();
            flightGetIn2.setItineraryID(client);
            GetFlights gFlights2 = new GetFlights();
            String deptDate2 = "03/01/2015";
            FlightRequest fReq2 = new FlightRequest();
            fReq2.setStartLocation("Copenhagen");
            fReq2.setEndLocation("Bucharest");
            fReq2.setFlightDate(toXMLCalendarDate(deptDate2));
            gFlights2.setFlightsReq(fReq2);
            flightGetIn2.setGetFlights(gFlights2);

            FlightsGetOutputElement flightGetOut2 = flightsGet(flightGetIn2);
            List<FlightDetails> flightList2 = flightGetOut2.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn2 = new FlightsAddInputElement();
            fAddIn2.setBookingNumber(flightList2.get(0).getBookingNo());
            fAddIn2.setItineraryID(client);
            boolean fAddOut2 = flightAdd(fAddIn2);
            assertEquals(fAddOut2, true);
            
        //Book itinerary
        ItineraryBookInputElement bookIn = new ItineraryBookInputElement();
        bookIn.setItineraryID(client);
        int month = 2;
        int year = 11;
        bookIn.setMonth(month);
        bookIn.setYear(year);
        String name = "Tick Joachim";
        String number = "50408824";
        bookIn.setName(name);
        bookIn.setNumber(number);

        boolean bookOut = itineraryBook(bookIn);        
        assertTrue(bookOut);   

        
        //Get itinerary and check that all status are 'confirmed'
        List<String> itGetOut = itineraryGet(client).getAnswer();
        for(String ligne: itGetOut){
            System.out.println(ligne);
            if(ligne.startsWith("Status")){
                assertTrue(ligne.endsWith("confirmed"));
            }
        }
        
        //Cancel Booking
        boolean cancelOut = itineraryCancel(client);
        assertTrue(cancelOut);
        
        //Get itinerary and check that all status are "cancelled"
        List<String> itGetOut2 = itineraryGet(client).getAnswer();
        for(String ligne: itGetOut2){
            System.out.println(ligne);
            if(ligne.startsWith("Status")){
                assertTrue(ligne.endsWith("cancelled"));
            }
        }
        
    }
    
    @Test
    public void C2() throws ItineraryBookFault{
        //Create itinerary
        String client = "Ema";
        client = itineraryCreate(client);
        
        //Plan flight
            //Get a list of flights
            FlightsGetInputElement flightGetIn = new FlightsGetInputElement();
            flightGetIn.setItineraryID(client);
            GetFlights gFlights = new GetFlights();
            String deptDate = "07/06/2015";
            FlightRequest fReq = new FlightRequest();
            fReq.setStartLocation("Torino");
            fReq.setEndLocation("London");
            fReq.setFlightDate(toXMLCalendarDate(deptDate));
            gFlights.setFlightsReq(fReq);
            flightGetIn.setGetFlights(gFlights);

            FlightsGetOutputElement flightGetOut = flightsGet(flightGetIn);
            List<FlightDetails> flightList = flightGetOut.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn = new FlightsAddInputElement();
            fAddIn.setBookingNumber(flightList.get(0).getBookingNo());
            fAddIn.setItineraryID(client);
            boolean fAddOut = flightAdd(fAddIn);
            assertEquals(fAddOut, true);
            
        //Plan second flight: this flight is made so that its cancelling always fails
            //Get flight list
            FlightsGetInputElement flightGetIn2 = new FlightsGetInputElement();
            flightGetIn2.setItineraryID(client);
            GetFlights gFlights2 = new GetFlights();
            String deptDate2 = "08/11/2016";
            FlightRequest fReq2 = new FlightRequest();
            fReq2.setStartLocation("Rio");
            fReq2.setEndLocation("NY");
            fReq2.setFlightDate(toXMLCalendarDate(deptDate2));
            gFlights2.setFlightsReq(fReq2);
            flightGetIn2.setGetFlights(gFlights2);

            FlightsGetOutputElement flightGetOut2 = flightsGet(flightGetIn2);
            List<FlightDetails> flightList2 = flightGetOut2.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn2 = new FlightsAddInputElement();
            fAddIn2.setBookingNumber(flightList2.get(0).getBookingNo());
            fAddIn2.setItineraryID(client);
            boolean fAddOut2 = flightAdd(fAddIn2);
            assertEquals(fAddOut2, true);
            
        //Plan hotel
            //Ask for a list of hotels
            HotelsGetInputElement hGetIn = new HotelsGetInputElement();
            hGetIn.setItineraryID(client);
            GetHotels hGet = new GetHotels();
            GetHotelRequest hGetReq = new GetHotelRequest();
            hGetReq.setCity("Copenhagen");
            hGetReq.setArrivalDate(toXMLCalendarDate("09/06/2015"));
            hGetReq.setDepartureDate(toXMLCalendarDate("12/06/2015"));
            hGet.setArg0(hGetReq);
            hGetIn.setGetHotels(hGet);

            HotelsGetOutputElement hGetOut = hotelsGet(hGetIn);
            List<Booking> hotelsList = hGetOut.getGetHotelsResponse().getReturn().getBookingList();
            
            //Add hotel to itinerary
            HotelAddInputElement hAddIn = new HotelAddInputElement();
            hAddIn.setItineraryID(client);
            hAddIn.setBookingNb(hotelsList.get(0).getBookingNumber());
            boolean hAddOut = hotelAdd(hAddIn);
            assertEquals(hAddOut, true);   
            
        //Book itinerary
        ItineraryBookInputElement bookIn = new ItineraryBookInputElement();
        bookIn.setItineraryID(client);
        int month = 2;
        int year = 11;
        bookIn.setMonth(month);
        bookIn.setYear(year);
        String name = "Tick Joachim";
        String number = "50408824";
        bookIn.setName(name);
        bookIn.setNumber(number);
        boolean bookOut = itineraryBook(bookIn);        
        assertTrue(bookOut);   
        
        //Get itinerary and check that all status are 'confirmed'
        List<String> itGetOut = itineraryGet(client).getAnswer();
        for(String ligne: itGetOut){
            System.out.println(ligne);
            if(ligne.startsWith("Status")){
                assertTrue(ligne.endsWith("confirmed"));
            }
        }
        
        //Cancel Booking
        boolean cancelOut = itineraryCancel(client);
        assertFalse(cancelOut);
        
        //Get itinerary and check that all status are "cancelled" exept for the
        //second one that should be "confirmed"
        int i=1;
        List<String> itGetOut2 = itineraryGet(client).getAnswer();
        for(String ligne: itGetOut2){
            System.out.println(ligne);
            if(ligne.startsWith("Status") && i!=2){
                assertTrue(ligne.endsWith("cancelled"));
                i++;
            }
            else if(ligne.startsWith("Status")){
                assertTrue(ligne.endsWith("confirmed"));
                i++;
            }
        }
    }
    
    @Test
    public void B(){
        //Create itinerary
        String client = "Marie";
        client = itineraryCreate(client);
        
        //Plan flight
            //Get a list of flights
            FlightsGetInputElement flightGetIn = new FlightsGetInputElement();
            flightGetIn.setItineraryID(client);
            GetFlights gFlights = new GetFlights();
            String deptDate = "07/06/2015";
            FlightRequest fReq = new FlightRequest();
            fReq.setStartLocation("Torino");
            fReq.setEndLocation("London");
            fReq.setFlightDate(toXMLCalendarDate(deptDate));
            gFlights.setFlightsReq(fReq);
            flightGetIn.setGetFlights(gFlights);

            FlightsGetOutputElement flightGetOut = flightsGet(flightGetIn);
            List<FlightDetails> flightList = flightGetOut.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn = new FlightsAddInputElement();
            fAddIn.setBookingNumber(flightList.get(0).getBookingNo());
            fAddIn.setItineraryID(client);
            boolean fAddOut = flightAdd(fAddIn);
            assertEquals(fAddOut, true);
            
        //Plan second flight: this flight is made so that its cancelling always fails
            //Get flight list
            FlightsGetInputElement flightGetIn2 = new FlightsGetInputElement();
            flightGetIn2.setItineraryID(client);
            GetFlights gFlights2 = new GetFlights();
            String deptDate2 = "08/05/2016";
            FlightRequest fReq2 = new FlightRequest();
            fReq2.setStartLocation("Curitiba");
            fReq2.setEndLocation("Tokyo");
            fReq2.setFlightDate(toXMLCalendarDate(deptDate2));
            gFlights2.setFlightsReq(fReq2);
            flightGetIn2.setGetFlights(gFlights2);

            FlightsGetOutputElement flightGetOut2 = flightsGet(flightGetIn2);
            List<FlightDetails> flightList2 = flightGetOut2.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn2 = new FlightsAddInputElement();
            fAddIn2.setBookingNumber(flightList2.get(0).getBookingNo());
            fAddIn2.setItineraryID(client);
            boolean fAddOut2 = flightAdd(fAddIn2);
            assertEquals(fAddOut2, true);
            
        //Plan third flight
            //Get flight list
            FlightsGetInputElement flightGetIn3 = new FlightsGetInputElement();
            flightGetIn3.setItineraryID(client);
            GetFlights gFlights3 = new GetFlights();
            String deptDate3 = "07/06/2015";
            FlightRequest fReq3 = new FlightRequest();
            fReq3.setStartLocation("Munchen");
            fReq3.setEndLocation("Madrid");
            fReq3.setFlightDate(toXMLCalendarDate(deptDate3));
            gFlights3.setFlightsReq(fReq3);
            flightGetIn3.setGetFlights(gFlights3);

            FlightsGetOutputElement flightGetOut3 = flightsGet(flightGetIn3);
            List<FlightDetails> flightList3 = flightGetOut3.getGetFlightsResponse().getReturn();
            
            //Add flight to itinerary
            FlightsAddInputElement fAddIn3 = new FlightsAddInputElement();
            fAddIn3.setBookingNumber(flightList3.get(0).getBookingNo());
            fAddIn3.setItineraryID(client);
            boolean fAddOut3 = flightAdd(fAddIn3);
            assertEquals(fAddOut3, true);
         
        //Get itinerary and check that all status are 'confirmed'

        List<String> itGetOut = itineraryGet(client).getAnswer();
        for(String ligne: itGetOut){
            System.out.println(ligne);
            if(ligne.startsWith("Status")){
                assertTrue(ligne.endsWith("unconfirmed"));
            }
        }
            
        //Book itinerary
        ItineraryBookInputElement bookIn = new ItineraryBookInputElement();
        bookIn.setItineraryID(client);
        int month = 2;
        int year = 11;
        bookIn.setMonth(month);
        bookIn.setYear(year);
        String name = "Tick Joachim";
        String number = "50408824";
        bookIn.setName(name);
        bookIn.setNumber(number);
        boolean bookOut;        
        try {
            bookOut = itineraryBook(bookIn);
            fail("Booking should have failed!");
        } catch (ItineraryBookFault ex) {
            assertEquals(ex.getFaultInfo(), "Error when booking itinerary: booking cancelled");
        }
        
        //Get itinerary and check that first flight is 'cancelled', other ones are 'unconfirmed'
        List<String> itGetOut2 = itineraryGet(client).getAnswer();
        int i=1;
        for(String ligne: itGetOut2){
            System.out.println(ligne);
            if(ligne.startsWith("Status")){
                if(i==1){
                    assertTrue(ligne.endsWith("cancelled"));
                }
                else{
                    assertTrue(ligne.endsWith("unconfirmed"));
                }
                i++;
            }
        }
    }
    
    public final XMLGregorianCalendar toXMLCalendarDate(String inDate){
        
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try{
         date = format.parse(inDate);
        }
        catch(ParseException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            
        }
        
        return xmlCalendar;
    
    }
    
    private Date toDate(String inDate) {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date = new Date();
        try {
            date = format.parse(inDate);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        return date;
    }
    
    private void printList(List<FlightDetails> res){
        if(res.size() == 0){
            System.out.println("Search Flight not found");
        }
        else
        {
            for(FlightDetails fl:res)
            {
                System.out.println("Booking no: " + fl.getBookingNo());
                System.out.println("StartLoc: " + fl.getStartLocation());
                System.out.println("End Location: " + fl.getEndLocation());
                System.out.println("Departure Time: " + fl.getDepartureTime());
                System.out.println("Arrival Time: " + fl.getArrivalTime());
                System.out.println("Price: " + fl.getPrice());
                System.out.println("Company: " + fl.getAirlineCompany());
             
            }
        }
    }
    
    private static String itineraryCreate(java.lang.String input1) {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.itineraryCreate(input1);
    }

    private static FlightsGetOutputElement flightsGet(ws.travel.FlightsGetInputElement input1) {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.flightsGet(input1);
    }

    private static HotelsGetOutputElement hotelsGet(ws.travel.HotelsGetInputElement input1) {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.hotelsGet(input1);
    }

    

    private static boolean flightAdd(ws.travel.FlightsAddInputElement input1) {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.flightAdd(input1);
    }

    private static ItineraryGetOutputElement itineraryGet(java.lang.String input1) {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.itineraryGet(input1);
    }

    private static boolean hotelAdd(ws.travel.HotelAddInputElement input1) {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.hotelAdd(input1);
    }

    private static boolean itineraryBook(ws.travel.ItineraryBookInputElement part1) throws ItineraryBookFault {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.itineraryBook(part1);
    }

    private static boolean itineraryCancel(java.lang.String input1) {
        ws.travel.TravelService service = new ws.travel.TravelService();
        ws.travel.TravelPortType port = service.getTravelPortTypeBindingPort();
        return port.itineraryCancel(input1);
    }

    
    
}
