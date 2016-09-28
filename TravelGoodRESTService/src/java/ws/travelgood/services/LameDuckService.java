package ws.travelgood.services;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.List;
import lameduck.ws.BookFlightRequest;
import lameduck.ws.CancelFlightRequest;
import lameduck.ws.CreditCardFaultMessage;
import lameduck.ws.FlightDetails;
import lameduck.ws.FlightRequest;
import lameduck.ws.LameDuckServiceFault;
import ws.travelgood.Utils;

/**
 *
 * @author Catalin
 */
public class LameDuckService {
    
    public static boolean bookFlight(int bookingNo, 
            CreditCardInfoType creditCardInfoType) throws LameDuckServiceFault, CreditCardFaultMessage
    {
        BookFlightRequest bookFlightRequest = new BookFlightRequest();
        bookFlightRequest.setBookingNo(bookingNo);
        bookFlightRequest.setCreditCardDetails(creditCardInfoType);
        
        return bookFlight(bookFlightRequest);
    }
    
    public static void cancelFlight(int bookingNo, int flightPrice, 
            CreditCardInfoType creditCardInfoType) throws LameDuckServiceFault
    {
        CancelFlightRequest cancelFlightRequest = new CancelFlightRequest();
        cancelFlightRequest.setBookingNo(bookingNo);
        cancelFlightRequest.setFlightPrice(flightPrice);
        cancelFlightRequest.setCreditCardDetails(creditCardInfoType);
        
        cancelFlight(cancelFlightRequest);
    }
    
    public static List<FlightDetails> getFlights(String departure, String arrival, String date) 
    {       
        FlightRequest flightRequest = new FlightRequest();
        flightRequest.setStartLocation(departure);
        flightRequest.setEndLocation(arrival);
        flightRequest.setFlightDate(Utils.toXMLCalendarDate(date));
        
        return getFlights(flightRequest);
    }

    private static boolean bookFlight(lameduck.ws.BookFlightRequest bookReq) throws LameDuckServiceFault, CreditCardFaultMessage {
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
