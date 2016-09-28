
package lameduck.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LameDuck", targetNamespace = "http://ws.lameduck/")
@XmlSeeAlso({
    dk.dtu.imm.fastmoney.types.ObjectFactory.class,
    dk.dtu.imm.fastmoney.ObjectFactory.class,
    lameduck.ws.ObjectFactory.class
})
public interface LameDuck {


    /**
     * 
     * @param flightsReq
     * @return
     *     returns java.util.List<lameduck.ws.FlightDetails>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getFlights", targetNamespace = "http://ws.lameduck/", className = "lameduck.ws.GetFlights")
    @ResponseWrapper(localName = "getFlightsResponse", targetNamespace = "http://ws.lameduck/", className = "lameduck.ws.GetFlightsResponse")
    @Action(input = "http://ws.lameduck/LameDuck/getFlightsRequest", output = "http://ws.lameduck/LameDuck/getFlightsResponse")
    public List<FlightDetails> getFlights(
        @WebParam(name = "flightsReq", targetNamespace = "")
        FlightRequest flightsReq);

    /**
     * 
     * @param bookReq
     * @return
     *     returns boolean
     * @throws LameDuckServiceFault
     * @throws CreditCardFaultMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "bookFlight", targetNamespace = "http://ws.lameduck/", className = "lameduck.ws.BookFlight")
    @ResponseWrapper(localName = "bookFlightResponse", targetNamespace = "http://ws.lameduck/", className = "lameduck.ws.BookFlightResponse")
    @Action(input = "http://ws.lameduck/LameDuck/bookFlightRequest", output = "http://ws.lameduck/LameDuck/bookFlightResponse", fault = {
        @FaultAction(className = LameDuckServiceFault.class, value = "http://ws.lameduck/LameDuck/bookFlight/Fault/LameDuckServiceFault"),
        @FaultAction(className = CreditCardFaultMessage.class, value = "http://ws.lameduck/LameDuck/bookFlight/Fault/CreditCardFaultMessage")
    })
    public boolean bookFlight(
        @WebParam(name = "bookReq", targetNamespace = "")
        BookFlightRequest bookReq)
        throws CreditCardFaultMessage, LameDuckServiceFault
    ;

    /**
     * 
     * @param cancelReq
     * @throws LameDuckServiceFault
     */
    @WebMethod
    @RequestWrapper(localName = "cancelFlight", targetNamespace = "http://ws.lameduck/", className = "lameduck.ws.CancelFlight")
    @ResponseWrapper(localName = "cancelFlightResponse", targetNamespace = "http://ws.lameduck/", className = "lameduck.ws.CancelFlightResponse")
    @Action(input = "http://ws.lameduck/LameDuck/cancelFlightRequest", output = "http://ws.lameduck/LameDuck/cancelFlightResponse", fault = {
        @FaultAction(className = LameDuckServiceFault.class, value = "http://ws.lameduck/LameDuck/cancelFlight/Fault/LameDuckServiceFault")
    })
    public void cancelFlight(
        @WebParam(name = "cancelReq", targetNamespace = "")
        CancelFlightRequest cancelReq)
        throws LameDuckServiceFault
    ;

}
