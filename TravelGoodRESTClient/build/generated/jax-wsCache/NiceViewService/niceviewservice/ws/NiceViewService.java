
package niceviewservice.ws;

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
@WebService(name = "NiceViewService", targetNamespace = "http://ws.niceviewservice/")
@XmlSeeAlso({
    dk.dtu.imm.fastmoney.types.ObjectFactory.class,
    dk.dtu.imm.fastmoney.ObjectFactory.class,
    niceviewservice.ws.ObjectFactory.class
})
public interface NiceViewService {


    /**
     * 
     * @param arg0
     * @throws CancelHotelFault
     */
    @WebMethod
    @RequestWrapper(localName = "cancelHotel", targetNamespace = "http://ws.niceviewservice/", className = "niceviewservice.ws.CancelHotel")
    @ResponseWrapper(localName = "cancelHotelResponse", targetNamespace = "http://ws.niceviewservice/", className = "niceviewservice.ws.CancelHotelResponse")
    @Action(input = "http://ws.niceviewservice/NiceViewService/cancelHotelRequest", output = "http://ws.niceviewservice/NiceViewService/cancelHotelResponse", fault = {
        @FaultAction(className = CancelHotelFault.class, value = "http://ws.niceviewservice/NiceViewService/cancelHotel/Fault/CancelHotelFault")
    })
    public void cancelHotel(
        @WebParam(name = "arg0", targetNamespace = "")
        CancelHotelRequest arg0)
        throws CancelHotelFault
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws CreditCardFaultMessage
     * @throws BookHotelFault
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "bookHotel", targetNamespace = "http://ws.niceviewservice/", className = "niceviewservice.ws.BookHotel")
    @ResponseWrapper(localName = "bookHotelResponse", targetNamespace = "http://ws.niceviewservice/", className = "niceviewservice.ws.BookHotelResponse")
    @Action(input = "http://ws.niceviewservice/NiceViewService/bookHotelRequest", output = "http://ws.niceviewservice/NiceViewService/bookHotelResponse", fault = {
        @FaultAction(className = BookHotelFault.class, value = "http://ws.niceviewservice/NiceViewService/bookHotel/Fault/BookHotelFault"),
        @FaultAction(className = CreditCardFaultMessage.class, value = "http://ws.niceviewservice/NiceViewService/bookHotel/Fault/CreditCardFaultMessage")
    })
    public boolean bookHotel(
        @WebParam(name = "arg0", targetNamespace = "")
        BookHotelRequest arg0)
        throws BookHotelFault, CreditCardFaultMessage
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns niceviewservice.ws.GetHotelReply
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getHotels", targetNamespace = "http://ws.niceviewservice/", className = "niceviewservice.ws.GetHotels")
    @ResponseWrapper(localName = "getHotelsResponse", targetNamespace = "http://ws.niceviewservice/", className = "niceviewservice.ws.GetHotelsResponse")
    @Action(input = "http://ws.niceviewservice/NiceViewService/getHotelsRequest", output = "http://ws.niceviewservice/NiceViewService/getHotelsResponse")
    public GetHotelReply getHotels(
        @WebParam(name = "arg0", targetNamespace = "")
        GetHotelRequest arg0);

}
