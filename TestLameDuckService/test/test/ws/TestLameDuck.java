/**
 *
 * @author catalin
 */
package test.ws;

import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import lameduck.ws.BookFlightRequest;
import lameduck.ws.CancelFlightRequest;
import lameduck.ws.CreditCardFaultMessage;
import lameduck.ws.FlightDetails;
import lameduck.ws.FlightRequest;
import lameduck.ws.LameDuckServiceFault;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;




public class TestLameDuck{
    CreditCardInfoType tCard = new CreditCardInfoType();
    CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();
   
    public TestLameDuck() {
       
    }
    
    
    
    @BeforeClass
    public static void setUpClass() {
       System.out.println("Before class...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("After class...");
    }
    
    @Before
    public void setUp() {
        System.out.println("Setting up testbench...");
    }
    
    @After
    public void tearDown() {
        System.out.println("Testing Done...");
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
     
    
    @Test
     public void testGetFlights() throws DatatypeConfigurationException {
       
        
        
        List<FlightDetails> res;
        String deptDate = "07/06/2015";
        
        System.out.println("Starting testing get flights...");
        System.out.println("Test 1 -> get\n");
        FlightRequest fReq = new FlightRequest();
        
        fReq.setStartLocation("Torino");
        fReq.setEndLocation("London");
        fReq.setFlightDate(toXMLCalendarDate(deptDate));
        res = getFlights(fReq);
        assertEquals(res.size(), 1);
        printList(res);
        
        
        System.out.println("\nTest 2 -> get");
        deptDate = "08/06/2015";
        
        fReq.setStartLocation("Bucharest");
        fReq.setEndLocation("London");  
        fReq.setFlightDate(toXMLCalendarDate(deptDate));
        
        res = getFlights(fReq);
        
        assertEquals(res.size(), 0);
        printList(res);
        
        
        System.out.println("\nTest 3 -> get");
        deptDate = "03/01/2015";
        
        fReq.setStartLocation("Copenhagen");
        fReq.setEndLocation("Bucharest");  
        fReq.setFlightDate(toXMLCalendarDate(deptDate));
        
        res = getFlights(fReq);
        
        assertEquals(res.size(), 1);
        printList(res);
    }
     
     
     @Test
     public void testBookFlights() throws LameDuckServiceFault, CreditCardFaultMessage{
     
     int month = 2;
     int year = 11;
     
     boolean res_valid_book = false;
     BookFlightRequest bReq = new BookFlightRequest();
     
     
     int tBookingNo = 34567;
     tCard.setName("Tick Joachim");
     tCard.setNumber("50408824");
     tCard.setExpirationDate(expDate);
     
     expDate.setMonth(month);
     expDate.setYear(year);
     
     bReq.setBookingNo(tBookingNo);
     bReq.setCreditCardDetails(tCard);
     
     
     res_valid_book = bookFlight(bReq);
     
     
     assertEquals(true, res_valid_book);
     
     }
     
     @Test (expected = LameDuckServiceFault.class)
     public void testBookNonAvailFlight() throws LameDuckServiceFault, CreditCardFaultMessage {
     int month = 2;
     int year = 11;
     
     boolean res_valid_book = false;
     BookFlightRequest bReq = new BookFlightRequest();
     
     
     int tBookingNo = 30002;
     tCard.setName("Tick Joachim");
     tCard.setNumber("50408824");
     tCard.setExpirationDate(expDate);
     
     expDate.setMonth(month);
     expDate.setYear(year);
     
     bReq.setBookingNo(tBookingNo);
     bReq.setCreditCardDetails(tCard);
     
     bookFlight(bReq);
     
  
     }
     
     
     @Test 
     public void testCancelFlight() throws LameDuckServiceFault{
     int month = 2;
     int year = 11;
     
     CancelFlightRequest cReq = new CancelFlightRequest();
     
     int tBookingNo = 34567;
     int tPrice = 200;
     //creditCardDetails
     tCard.setName("Tick Joachim");
     tCard.setNumber("50408824");
     tCard.setExpirationDate(expDate);
     
     expDate.setMonth(month);
     expDate.setYear(year);
     
     System.out.println("Card Owner: " + tCard.getName());
     
     
     cReq.setBookingNo(tBookingNo);
     cReq.setFlightPrice(tPrice);
     cReq.setCreditCardDetails(tCard);
     
     
     System.out.println("Credit Card name: " + tCard.getName());
     
     cancelFlight(cReq);
     
     }

    private static boolean bookFlight(lameduck.ws.BookFlightRequest bookReq) throws CreditCardFaultMessage, LameDuckServiceFault {
        lameduck.ws.LameDuckService service = new lameduck.ws.LameDuckService();
        lameduck.ws.LameDuck port = service.getLameDuckPort();
        return port.bookFlight(bookReq);
    }

    private static void cancelFlight(lameduck.ws.CancelFlightRequest cancelReq) throws LameDuckServiceFault {
        lameduck.ws.LameDuckService service = new lameduck.ws.LameDuckService();
        lameduck.ws.LameDuck port = service.getLameDuckPort();
        port.cancelFlight(cancelReq);
    }

    private static java.util.List<lameduck.ws.FlightDetails> getFlights(lameduck.ws.FlightRequest flightsReq) {
        lameduck.ws.LameDuckService service = new lameduck.ws.LameDuckService();
        lameduck.ws.LameDuck port = service.getLameDuckPort();
        return port.getFlights(flightsReq);
    }

    }
