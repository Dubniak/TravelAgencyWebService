/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import niceviewservice.ws.BookHotelFault;
import niceviewservice.ws.BookHotelRequest;
import niceviewservice.ws.BookHotelResponse;
import niceviewservice.ws.Booking;
import niceviewservice.ws.CancelHotelFault;
import niceviewservice.ws.CancelHotelRequest;
import niceviewservice.ws.CreditCardFaultMessage;
import niceviewservice.ws.GetHotelReply;
import niceviewservice.ws.GetHotelRequest;
import niceviewservice.ws.Hotel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eirini
 */
public class HotelTest {
    
    
    CreditCardInfoType tCard = new CreditCardInfoType();   
        
    CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();
   
    
    
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
    
    private void printList(List<Booking> res){
        if(res.isEmpty()){
            System.out.println("No hotels match your criteria");
        }
        else
        {
            for(Booking hotel: res)
            {
                System.out.println("Booking no: " + hotel.getBookingNumber());
                System.out.println("Hotel name: " + hotel.getHotel().getName());
                System.out.println("Reservation Service: " + hotel.getHotel().getReservationService());
                System.out.println("Price: " + hotel.getPrice());
    
            }
        }
    }
    
    @Test
    public void getHotels() {
        
        System.out.println("Starting testing get hotels...");
        System.out.println();
        GetHotelRequest request = new GetHotelRequest();
        GetHotelReply reply = new GetHotelReply();
        //first test
        System.out.println("Test 1 -> get");
        String arrivalDate = "14/12/2015";
        String departureDate = "20/12/2015";
        String city = "Athens";
        
        request.setArrivalDate(toXMLCalendarDate(arrivalDate));
        request.setDepartureDate(toXMLCalendarDate(departureDate));
        request.setCity(city);
        
        reply = getHotels(request);
        
        assertEquals(0, reply.getBookingList().size());
        printList(reply.getBookingList());
        
        System.out.println();
        //second test
        System.out.println("Test 2 -> get");
        arrivalDate = "17/1/2016";
        departureDate = "20/1/2016";
        city = "Copenhagen";
        
        request.setArrivalDate(toXMLCalendarDate(arrivalDate));
        request.setDepartureDate(toXMLCalendarDate(departureDate));
        request.setCity(city);
        
        reply = getHotels(request);
        
        assertEquals(2, reply.getBookingList().size());
        printList(reply.getBookingList());
        
        System.out.println();
    
    }
    
    @Test
    public void bookHotelWithoutGuarantee() throws CreditCardFaultMessage, BookHotelFault {
        
        System.out.println("Starting testing book hotels...");
        System.out.println();
        //get Hotels
        GetHotelRequest getRequest = new GetHotelRequest();
        GetHotelReply getReply = new GetHotelReply();
        String arrivalDate = "17/1/2016";
        String departureDate = "20/1/2016";
        String city = "Copenhagen";
        getRequest.setArrivalDate(toXMLCalendarDate(arrivalDate));
        getRequest.setDepartureDate(toXMLCalendarDate(departureDate));
        getRequest.setCity(city);
        getReply = getHotels(getRequest);
        assertEquals(2, getReply.getBookingList().size());
        
        //book hotel without guarantee
        BookHotelRequest bookRequest = new BookHotelRequest();
        boolean bookReply;
        bookRequest.setBookingNo(getReply.getBookingList().get(1).getBookingNumber());
        bookReply = bookHotel(bookRequest);
        assertEquals(true, bookReply);
        System.out.println("Booking successful");
        System.out.println();
    
    }
    
     
    @Test
    public void bookHotelWithGuaranteeValid() throws CreditCardFaultMessage, BookHotelFault {
        
        System.out.println("Starting testing book hotels...");
        System.out.println();
        //get Hotels
        GetHotelRequest getRequest = new GetHotelRequest();
        GetHotelReply getReply = new GetHotelReply();
        String arrivalDate = "17/1/2016";
        String departureDate = "20/1/2016";
        String city = "Copenhagen";
        getRequest.setArrivalDate(toXMLCalendarDate(arrivalDate));
        getRequest.setDepartureDate(toXMLCalendarDate(departureDate));
        getRequest.setCity(city);
        getReply = getHotels(getRequest);
        assertEquals(2, getReply.getBookingList().size());
        
        //valid credit card details
        int month = 2;
        int year = 11;
        tCard.setName("Tick Joachim");
        tCard.setNumber("50408824");
        tCard.setExpirationDate(expDate); 
        expDate.setMonth(month);
        expDate.setYear(year);
        
        //book hotel without guarantee
        BookHotelRequest bookRequest = new BookHotelRequest();
        boolean bookReply;
        bookRequest.setBookingNo(getReply.getBookingList().get(0).getBookingNumber());
        bookRequest.setCreditCardInfo(tCard);
        
        bookReply = bookHotel(bookRequest);
        assertEquals(true, bookReply);
        System.out.println("Booking successful");
        System.out.println();
    
    }
    
    @Test
    public void bookHotelWithGuaranteeInvalid() throws CreditCardFaultMessage, BookHotelFault {
        
        System.out.println("Starting testing book hotels...");
        System.out.println();
        //get Hotels
        GetHotelRequest getRequest = new GetHotelRequest();
        GetHotelReply getReply = new GetHotelReply();
        String arrivalDate = "17/1/2016";
        String departureDate = "20/1/2016";
        String city = "Copenhagen";
        getRequest.setArrivalDate(toXMLCalendarDate(arrivalDate));
        getRequest.setDepartureDate(toXMLCalendarDate(departureDate));
        getRequest.setCity(city);
        getReply = getHotels(getRequest);
        assertEquals(2, getReply.getBookingList().size());
        
        //valid credit card details
        int month = 2;
        int year = 11;
        tCard.setName("Invalid name");
        tCard.setNumber("Invalid number");
        tCard.setExpirationDate(expDate); 
        expDate.setMonth(month);
        expDate.setYear(year);
        
        //book hotel without guarantee
        BookHotelRequest bookRequest = new BookHotelRequest();
        boolean bookReply;
        bookRequest.setBookingNo(getReply.getBookingList().get(0).getBookingNumber());
        bookRequest.setCreditCardInfo(tCard);
        
        try {
            bookReply = bookHotel(bookRequest);
            fail("Exception");
        } catch (BookHotelFault exception) {
            assertTrue(true);
        }
        
        System.out.println("Booking unsuccessful");
        System.out.println();
    
    }
    
    @Test
    public void cancelHotelSuccessful() throws CreditCardFaultMessage, BookHotelFault, CancelHotelFault {
        
        System.out.println("Starting testing cancel hotels...");
        System.out.println();
        
        //get Hotels
        GetHotelRequest getRequest = new GetHotelRequest();
        GetHotelReply getReply = new GetHotelReply();
        String arrivalDate = "17/1/2016";
        String departureDate = "20/1/2016";
        String city = "Copenhagen";
        getRequest.setArrivalDate(toXMLCalendarDate(arrivalDate));
        getRequest.setDepartureDate(toXMLCalendarDate(departureDate));
        getRequest.setCity(city);
        getReply = getHotels(getRequest);
        assertEquals(2, getReply.getBookingList().size());
        
        //book hotel without guarantee
        BookHotelRequest bookRequest = new BookHotelRequest();
        boolean bookReply;
        bookRequest.setBookingNo(getReply.getBookingList().get(1).getBookingNumber());
        bookReply = bookHotel(bookRequest);
        assertEquals(true, bookReply);
        System.out.println("Booking successful");
        System.out.println();
        
        //cancel already booked hotel - success
        CancelHotelRequest cancelRequest = new CancelHotelRequest();
        cancelRequest.setBookingNo(bookRequest.getBookingNo());
        cancelHotel(cancelRequest);
        System.out.println("Cancelation successful");
        System.out.println();
    
    }
    
    @Test
    public void cancelHotelUnsuccessful() throws CreditCardFaultMessage, BookHotelFault, CancelHotelFault {
        
        System.out.println("Starting testing cancel hotels...");
        System.out.println();
        
        //get Hotels
        GetHotelRequest getRequest = new GetHotelRequest();
        GetHotelReply getReply = new GetHotelReply();
        String arrivalDate = "17/1/2016";
        String departureDate = "20/1/2016";
        String city = "Copenhagen";
        getRequest.setArrivalDate(toXMLCalendarDate(arrivalDate));
        getRequest.setDepartureDate(toXMLCalendarDate(departureDate));
        getRequest.setCity(city);
        getReply = getHotels(getRequest);
        assertEquals(2, getReply.getBookingList().size());
               
        //cancel unbooked hotel - failure
        CancelHotelRequest cancelRequest = new CancelHotelRequest();
        cancelRequest.setBookingNo(getReply.getBookingList().get(0).getBookingNumber());
        
        try {
            cancelHotel(cancelRequest);
            fail("Exception");
        } catch (CancelHotelFault exception) {
            assertTrue(true);
        }
        System.out.println("Cancelation unsuccessful");
        System.out.println();

    }
    
    
    private static boolean bookHotel(niceviewservice.ws.BookHotelRequest arg0) throws CreditCardFaultMessage, BookHotelFault {
        niceviewservice.ws.NiceViewService_Service service = new niceviewservice.ws.NiceViewService_Service();
        niceviewservice.ws.NiceViewService port = service.getNiceViewServicePort();
        return port.bookHotel(arg0);
    }

    private static void cancelHotel(niceviewservice.ws.CancelHotelRequest arg0) throws CancelHotelFault {
        niceviewservice.ws.NiceViewService_Service service = new niceviewservice.ws.NiceViewService_Service();
        niceviewservice.ws.NiceViewService port = service.getNiceViewServicePort();
        port.cancelHotel(arg0);
    }

    private static GetHotelReply getHotels(niceviewservice.ws.GetHotelRequest arg0) {
        niceviewservice.ws.NiceViewService_Service service = new niceviewservice.ws.NiceViewService_Service();
        niceviewservice.ws.NiceViewService port = service.getNiceViewServicePort();
        return port.getHotels(arg0);
    }
}
