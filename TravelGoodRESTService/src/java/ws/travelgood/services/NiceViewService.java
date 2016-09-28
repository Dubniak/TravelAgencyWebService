package ws.travelgood.services;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import niceviewservice.ws.BookHotelFault;
import niceviewservice.ws.BookHotelRequest;
import niceviewservice.ws.CancelHotelFault;
import niceviewservice.ws.CancelHotelRequest;
import niceviewservice.ws.CreditCardFaultMessage;
import niceviewservice.ws.GetHotelReply;
import niceviewservice.ws.GetHotelRequest;
import ws.travelgood.Utils;


/**
 *
 * @author Marie
 */
public class NiceViewService {
    
    public static boolean bookHotel(int bookingNo, CreditCardInfoType creditCardInfoType) throws BookHotelFault, CreditCardFaultMessage
    {
        BookHotelRequest bookHotelRequest = new BookHotelRequest();
        bookHotelRequest.setBookingNo(bookingNo);
        bookHotelRequest.setCreditCardInfo(creditCardInfoType);
        
        return bookHotel(bookHotelRequest);
    }
    
    public static void cancelHotel(int bookingNo) throws CancelHotelFault
    {
        CancelHotelRequest cancelHotelRequest = new CancelHotelRequest();
        cancelHotelRequest.setBookingNo(bookingNo);
        
        cancelHotel(cancelHotelRequest);
    }
    
    public static GetHotelReply getHotels(String city, String arrivalDate, String departureDate)
    {
        GetHotelRequest getHotelRequest = new GetHotelRequest();
        getHotelRequest.setCity(city);
        getHotelRequest.setArrivalDate(
                Utils.toXMLGregorianCalendarDate(arrivalDate));
        getHotelRequest.setDepartureDate(
                Utils.toXMLGregorianCalendarDate(departureDate));
        
        return getHotels(getHotelRequest);
    }    
    
    
    private static boolean bookHotel(niceviewservice.ws.BookHotelRequest arg0) throws BookHotelFault, CreditCardFaultMessage {
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
