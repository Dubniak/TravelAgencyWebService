/**
 *
 * @author catalin
 */
package lameduck.ws;

import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.jws.WebService;


import java.util.Date;


import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import javax.xml.ws.WebServiceRef;

/**
 *
 * @author catalin
 */
@WebService(serviceName = "LameDuckService")
public class LameDuck {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service = new BankService();

    String LD_Account = "50208812";

    FlightDetails availFlight1;
    FlightDetails availFlight2;
    FlightDetails availFlight3;
    FlightDetails errorWhenCancel;
    FlightDetails errorWhenBooking;

    List<FlightDetails> flights;

    private Map<String, List<FlightDetails>> bookedFlights;

    private final static int groupNo = 3; //the groupNo. that should be 
    //included in """""some"""" msg  

    /* public final Date toDate(String inDate){
        
     final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
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
        
     Date xmlCalendar = null;
     try {
     xmlCalendar = DatatypeFactory.newInstance().newDate(gCalendar);
     } catch (DatatypeConfigurationException ex) {
     Logger.getLogger(LameDuckService.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     return xmlCalendar;
    
     }
     */
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

    public final void initFlights() {

        availFlight1 = new FlightDetails();
        availFlight2 = new FlightDetails();
        availFlight3 = new FlightDetails();
        errorWhenCancel = new FlightDetails();
        errorWhenBooking = new FlightDetails();
        
        
        availFlight1.setBookingNo(12345);
        availFlight1.setStartLocation("Copenhagen");
        availFlight1.setEndLocation("Bucharest");

        final String deptDateStr1 = "03/01/2015 21:10";
        Date deptDate1 = toDate(deptDateStr1);
        final String arrDateStr1 = "04/01/2015 01:10";
        Date arrDate1 = toDate(arrDateStr1);

        availFlight1.setDepartureTime(deptDate1);
        availFlight1.setArrivalTime(arrDate1);
        availFlight1.setAirlineCompany("AustrianAirlines");
        availFlight1.setPrice(100);
        
        availFlight2.setBookingNo(34567);
        availFlight2.setStartLocation("Torino");
        availFlight2.setEndLocation("London");

        final String deptDateStr2 = "07/06/2015 20:10";
        Date deptDate2 = toDate(deptDateStr2);
        final String arrDateStr2 = "07/06/2015 22:05";
        Date arrDate2 = toDate(arrDateStr2);

        availFlight2.setDepartureTime(deptDate2);
        availFlight2.setArrivalTime(arrDate2);
        availFlight2.setAirlineCompany("AirItalia");
        availFlight2.setPrice(200);
        
        availFlight3.setBookingNo(78901);
        availFlight3.setStartLocation("Munchen");
        availFlight3.setEndLocation("Madrid");

        final String deptDateStr3 = "08/11/2015 16:10";
        Date deptDate3 = toDate(deptDateStr3);
        final String arrDateStr3 = "08/11/2015 20:10";
        Date arrDate3 = toDate(arrDateStr3);

        availFlight3.setDepartureTime(deptDate3);
        availFlight3.setArrivalTime(arrDate3);
        availFlight3.setAirlineCompany("AirBerlin");
        availFlight3.setPrice(300);

        errorWhenCancel.setStartLocation("Rio");
        errorWhenCancel.setEndLocation("NY");
        errorWhenCancel.setBookingNo(89009);        
        
        final String deptDateStr4 = "08/11/2016 16:10";
        Date deptDate4 = toDate(deptDateStr4);
        final String arrDateStr4 = "08/11/2016 20:10";
        Date arrDate4 = toDate(arrDateStr4);
        
        errorWhenCancel.setDepartureTime(deptDate4);
        errorWhenCancel.setArrivalTime(arrDate4);
        errorWhenCancel.setAirlineCompany("AirFrance");
        errorWhenCancel.setPrice(500);
        
        errorWhenBooking.setStartLocation("Curitiba");
        errorWhenBooking.setEndLocation("Tokyo");
        errorWhenBooking.setBookingNo(90999);        
        
        final String deptDateStr5 = "08/05/2016 16:10";
        Date deptDate5 = toDate(deptDateStr5);
        final String arrDateStr5 = "09/05/2016 20:10";
        Date arrDate5 = toDate(arrDateStr5);
        
        errorWhenBooking.setDepartureTime(deptDate5);
        errorWhenBooking.setArrivalTime(arrDate5);
        errorWhenBooking.setAirlineCompany("American Airlines");
        errorWhenBooking.setPrice(700);
    }

    
    private CreditCardInfoType getCardInfo(CreditCardInfoType cardReq){
 
        CreditCardInfoType res = new CreditCardInfoType();
       
        res.setName(cardReq.getName());
        res.setNumber(cardReq.getNumber());
        res.setExpirationDate(cardReq.getExpirationDate());
 
        return res;
    }
    
    public LameDuck() {
        //this.service = new BankService();
        initFlights();

        flights = new ArrayList();

        flights.add(availFlight1);
        flights.add(availFlight2);
        flights.add(availFlight3);
        flights.add(errorWhenCancel);
        flights.add(errorWhenBooking);

        bookedFlights = new HashMap<>();

    }

    /**
     *
     * @param fligthsReq
     * @return
     */

    /*public List<FlightInformation> getFlights(@WebParam(name = "origin") String origin, 
            @WebParam(name = "destination") String destination, 
            @WebParam(name = "date") String date)   */
    @WebMethod(operationName = "getFlights")       
    public List<FlightDetails> getFlights(@WebParam(name = "flightsReq") FlightRequest flightsReq) {

        List<FlightDetails> res = new ArrayList();
        
        for (FlightDetails fl : flights) {

            String sLoc = fl.getStartLocation();
            String dLoc = fl.getEndLocation();

            Date reqDate = flightsReq.getFlightDate();
            //Date availDate = new Date();
            Date availDate = flightsReq.getFlightDate();

            if ((sLoc.equalsIgnoreCase(flightsReq.getStartLocation()))
                    && (dLoc.equalsIgnoreCase(flightsReq.getEndLocation()))
                    && (reqDate.equals(availDate))) {

                res.add(fl);
            }

        }

        return res;

    }
    /*(@WebParam(name = "origin") String origin, 
            @WebParam(name = "destination") String destination, 
            @WebParam(name = "date") String date)*/
    @WebMethod(operationName = "bookFlight")  
    public boolean bookFlight(@WebParam(name = "bookReq")BookFlightRequest bookReq) throws LameDuckServiceFault, CreditCardFaultMessage {
        boolean booked = false;
        boolean isValid = false;
        boolean found = false;

        int bookNo = bookReq.getBookingNo();
        
        //REturn a fault when trying to book flight errorWhenBooking
        if(bookNo == 90999){
            String ex_msg = "Flight with bookingNo: " + Integer.toString(bookNo)
                    + "not found in the list of available flights";
            LameDuckServiceFault bFault = new LameDuckServiceFault(ex_msg, bookNo);
            throw bFault;
        }
        
        int price = 0;
        FlightDetails f = new FlightDetails();
        String company = new String();
        //List<FlightDetails> bFlights;
        //get the price of the flight the user wants to book
        for (FlightDetails fl : flights) {
            if (fl.getBookingNo() == bookNo) {
                price = fl.getPrice();
                company = fl.getAirlineCompany();
                found = true;
                f = fl;
                break;
            }
        }

        if (!found) {
            String ex_msg = "Flight with bookingNo: " + Integer.toString(bookNo)
                    + "not found in the list of available flights";
            LameDuckServiceFault bFault = new LameDuckServiceFault(ex_msg, bookNo);
            throw bFault;

        }

        AccountType agency_acc = new AccountType();
        agency_acc.setName("LameDuck");
        agency_acc.setNumber("50208812");

        CreditCardInfoType user_card = getCardInfo(bookReq.getCreditCardDetails());
        
        System.out.println("Name: " + user_card.getName());
        System.out.println("BookingNo: " + bookNo);
        System.out.println("Exp. Date: " + user_card.getExpirationDate());
        System.out.println("Card Number: " + user_card.getNumber());
        
       try {
            isValid = service.getBankPort().validateCreditCard(groupNo, user_card, (int) (price));
       } catch (CreditCardFaultMessage ex) {
            String msg = ex.getFaultInfo().getMessage();
            String ex_msg = "Card Validation Operation Failed -> Details: " + msg
                    + " Booking for flight: " + Integer.toString(bookNo) + "failed";
            LameDuckServiceFault bFault = new LameDuckServiceFault(ex_msg, bookNo);
            throw bFault;
        }
        // if (isValid) {
        try {

            boolean charged = service.getBankPort().chargeCreditCard(groupNo, user_card, (int)price, agency_acc);
            
        } catch (CreditCardFaultMessage ex) {
            String msg = ex.getFaultInfo().getMessage();

            String ex_msg = "Card Charge Operation Failed -> Details: " + msg
                    + " Booking for flight: " + Integer.toString(bookNo) + "failed";
            LameDuckServiceFault bFault = new LameDuckServiceFault(ex_msg, bookNo);
            throw bFault;
        }

      
        String key = user_card.getNumber();
        //insert a new flight for a user that has already reserved some flights
        if (bookedFlights.containsKey(key)) {
            List<FlightDetails> flist = bookedFlights.get(key);
            flist.add(f);
        }//first reservation for that user
        else {
            List<FlightDetails> flist = new ArrayList();
            flist.add(f);
            bookedFlights.put(key, flist);
        }
        
        booked = true;
       

        return booked;

       
    }

    @WebMethod(operationName = "cancelFlight") 
    public void cancelFlight(@WebParam(name = "cancelReq")CancelFlightRequest cancelReq) throws  LameDuckServiceFault{

        int bookNo = cancelReq.getBookingNo();
        double price = cancelReq.getFlightPrice();
       
        if(cancelReq.getBookingNo() == 89009){
            LameDuckServiceFault cFault1 = new LameDuckServiceFault("Error", bookNo);
            throw cFault1;
        }
        
        CreditCardInfoType user_card = getCardInfo(cancelReq.getCreditCardDetails());
        
        AccountType agency_acc = new AccountType();
        agency_acc.setName("LameDuck");
        agency_acc.setNumber(LD_Account);

        try {
            boolean refund;
            refund = service.getBankPort().refundCreditCard(groupNo, user_card, bookNo, agency_acc);
        } catch (CreditCardFaultMessage ex) {
            String msg = ex.getFaultInfo().getMessage();

            String ex_msg = "Cancel Flight Operation Failed -> Details: " + msg
                    + " Cancel Booking for flight: " + Integer.toString(bookNo) + "failed";
            LameDuckServiceFault cFault = new LameDuckServiceFault(ex_msg, bookNo);
            throw cFault;
        }
        
        List<FlightDetails> f;
        f = bookedFlights.remove(user_card.getNumber());

    }

    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

  

}