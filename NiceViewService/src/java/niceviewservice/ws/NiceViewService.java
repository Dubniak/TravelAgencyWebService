/*
 * @author Eirini
 */
package niceviewservice.ws;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;


@WebService(serviceName = "NiceViewService")
public class NiceViewService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    
    static List<Hotel> HotelList = new ArrayList();
    List<Booking> SearchList = new ArrayList();
    
   
    
    static int bookingNo = 1000;
    static final int groupNo = 3;
  
    
    /*code from StackOverFlow */
    private static int daysBetween(long timeInMillis, long timeInMillis0) {
        long miliseconds = timeInMillis0 - timeInMillis;
        return (int)miliseconds / (1000*60*60*24);
    }  
    
    static {
    
        Address address1 = new Address();
        address1.setCity("Copenhagen");
        address1.setCountry("Denmark");
        address1.setStreetName("Furesovej");
        address1.setNumber(11);
        address1.setZipCode(12312);

        Hotel hotel1 = new Hotel();
        hotel1.setName("Danmark Hotel");
        hotel1.setAddress(address1);
        hotel1.setPrice(120);
        hotel1.setGuarantee(true);
        hotel1.setReservationService("Nice View");

        HotelList.add(hotel1);

        Address address2 = new Address();
        address2.setCity("Copenhagen");
        address2.setCountry("Denmark");
        address2.setStreetName("Vangeledet");
        address2.setNumber(123);
        address2.setZipCode(12332);

        Hotel hotel2 = new Hotel();
        hotel2.setName("Urban House");
        hotel2.setAddress(address2);
        hotel2.setPrice(50);
        hotel2.setGuarantee(false);
        hotel2.setReservationService("Nice View");

        HotelList.add(hotel2);
        
    
    }   
    
    
    public GetHotelReply getHotels(GetHotelRequest request) {
        
        SearchList.clear();
        for(Hotel hotel : HotelList) {
           
           if (hotel.getAddress().getCity().equals(request.getCity())) {
               bookingNo++;
               int days = daysBetween(request.getArrivalDate().toGregorianCalendar().getTimeInMillis(),request.getDepartureDate().toGregorianCalendar().getTimeInMillis());
               Booking newEntry = new Booking();
               newEntry.setHotel(hotel);
               newEntry.setStatus(false);
               newEntry.setBookingNumber(bookingNo);
               newEntry.setPrice(hotel.getPrice()*days);
               SearchList.add(newEntry);            
            }        
        }
        
        GetHotelReply result = new GetHotelReply();
        result.bookingList = SearchList;
        return result;
        
    }
    
    
    public boolean bookHotel(BookHotelRequest request) throws BookHotelFault, CreditCardFaultMessage {
        
        Booking find = new Booking();
        find = null;
        
        for(Booking element : SearchList) {
           
           if (element.getBookingNumber() == request.getBookingNo()) {
             find = element;
             break;                        
           }      
           
        }
        
        if (find == null) 
            throw new BookHotelFault("Booking failed",  "Booking number not in search list");
        
        if (find.isStatus())
            throw new BookHotelFault("Booking failed", "Hotel already booked for these days");
        
        
        if (!find.getHotel().isGuarantee()) {
            find.setStatus(true);
            return true;
        }
        else {
           
            CreditCardInfoType card = new CreditCardInfoType();
            card.setName(request.getCreditCardInfo().getName());
            card.setNumber(request.getCreditCardInfo().getNumber());
            ExpirationDate date = new ExpirationDate();
            date.setMonth(request.getCreditCardInfo().getExpirationDate().getMonth());
            date.setYear(request.getCreditCardInfo().getExpirationDate().getYear());
            card.setExpirationDate(date);
            
            try {
                    boolean valid = validateCreditCard(groupNo, card, (int) find.getPrice());
                    if(valid) {
                    find.setStatus(true);
                }
                return valid;
            } catch (CreditCardFaultMessage exception) {
                throw new BookHotelFault("Hotel booking failed!", exception.getMessage());
            }      
        }
    }
     
     
    public void cancelHotel(CancelHotelRequest request) throws CancelHotelFault {
       
        Booking find = new Booking();
        
        for(Booking element : SearchList) {
           
           if (element.getBookingNumber() == request.getBookingNo()) {
             find = element;
             break;                        
           }  
           
        }
        
        if (find == null) 
            throw new CancelHotelFault("Cancellation of booking failed", "Booking number not in search list");
        
        if (!find.isStatus())
            throw new CancelHotelFault("Cancellation of booking failed", "Hotel was not booked");
        
        find.setStatus(false);      
       
    }    

    private boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}


