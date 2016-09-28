
package lameduck.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the lameduck.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookFlightRequest_QNAME = new QName("http://ws.lameduck/", "bookFlightRequest");
    private final static QName _CancelFlightRequest_QNAME = new QName("http://ws.lameduck/", "cancelFlightRequest");
    private final static QName _GetFlightsResponse_QNAME = new QName("http://ws.lameduck/", "getFlightsResponse");
    private final static QName _BookFlightFault_QNAME = new QName("http://ws.lameduck/", "BookFlightFault");
    private final static QName _FlightDetails_QNAME = new QName("http://ws.lameduck/", "FlightDetails");
    private final static QName _BookFlight_QNAME = new QName("http://ws.lameduck/", "bookFlight");
    private final static QName _CancelFlight_QNAME = new QName("http://ws.lameduck/", "cancelFlight");
    private final static QName _GetFlights_QNAME = new QName("http://ws.lameduck/", "getFlights");
    private final static QName _BookFlightResponse_QNAME = new QName("http://ws.lameduck/", "bookFlightResponse");
    private final static QName _FlightRequest_QNAME = new QName("http://ws.lameduck/", "FlightRequest");
    private final static QName _CancelFlightResponse_QNAME = new QName("http://ws.lameduck/", "cancelFlightResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lameduck.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookFlightResponse }
     * 
     */
    public BookFlightResponse createBookFlightResponse() {
        return new BookFlightResponse();
    }

    /**
     * Create an instance of {@link GetFlights }
     * 
     */
    public GetFlights createGetFlights() {
        return new GetFlights();
    }

    /**
     * Create an instance of {@link FlightRequest }
     * 
     */
    public FlightRequest createFlightRequest() {
        return new FlightRequest();
    }

    /**
     * Create an instance of {@link CancelFlightResponse }
     * 
     */
    public CancelFlightResponse createCancelFlightResponse() {
        return new CancelFlightResponse();
    }

    /**
     * Create an instance of {@link FlightDetails }
     * 
     */
    public FlightDetails createFlightDetails() {
        return new FlightDetails();
    }

    /**
     * Create an instance of {@link BookFlight }
     * 
     */
    public BookFlight createBookFlight() {
        return new BookFlight();
    }

    /**
     * Create an instance of {@link CancelFlight }
     * 
     */
    public CancelFlight createCancelFlight() {
        return new CancelFlight();
    }

    /**
     * Create an instance of {@link CancelFlightRequest }
     * 
     */
    public CancelFlightRequest createCancelFlightRequest() {
        return new CancelFlightRequest();
    }

    /**
     * Create an instance of {@link GetFlightsResponse }
     * 
     */
    public GetFlightsResponse createGetFlightsResponse() {
        return new GetFlightsResponse();
    }

    /**
     * Create an instance of {@link BookFlightRequest }
     * 
     */
    public BookFlightRequest createBookFlightRequest() {
        return new BookFlightRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlightRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "bookFlightRequest")
    public JAXBElement<BookFlightRequest> createBookFlightRequest(BookFlightRequest value) {
        return new JAXBElement<BookFlightRequest>(_BookFlightRequest_QNAME, BookFlightRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlightRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "cancelFlightRequest")
    public JAXBElement<CancelFlightRequest> createCancelFlightRequest(CancelFlightRequest value) {
        return new JAXBElement<CancelFlightRequest>(_CancelFlightRequest_QNAME, CancelFlightRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "getFlightsResponse")
    public JAXBElement<GetFlightsResponse> createGetFlightsResponse(GetFlightsResponse value) {
        return new JAXBElement<GetFlightsResponse>(_GetFlightsResponse_QNAME, GetFlightsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "BookFlightFault")
    public JAXBElement<Integer> createBookFlightFault(Integer value) {
        return new JAXBElement<Integer>(_BookFlightFault_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "FlightDetails")
    public JAXBElement<FlightDetails> createFlightDetails(FlightDetails value) {
        return new JAXBElement<FlightDetails>(_FlightDetails_QNAME, FlightDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "bookFlight")
    public JAXBElement<BookFlight> createBookFlight(BookFlight value) {
        return new JAXBElement<BookFlight>(_BookFlight_QNAME, BookFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "cancelFlight")
    public JAXBElement<CancelFlight> createCancelFlight(CancelFlight value) {
        return new JAXBElement<CancelFlight>(_CancelFlight_QNAME, CancelFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlights }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "getFlights")
    public JAXBElement<GetFlights> createGetFlights(GetFlights value) {
        return new JAXBElement<GetFlights>(_GetFlights_QNAME, GetFlights.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "bookFlightResponse")
    public JAXBElement<BookFlightResponse> createBookFlightResponse(BookFlightResponse value) {
        return new JAXBElement<BookFlightResponse>(_BookFlightResponse_QNAME, BookFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "FlightRequest")
    public JAXBElement<FlightRequest> createFlightRequest(FlightRequest value) {
        return new JAXBElement<FlightRequest>(_FlightRequest_QNAME, FlightRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lameduck/", name = "cancelFlightResponse")
    public JAXBElement<CancelFlightResponse> createCancelFlightResponse(CancelFlightResponse value) {
        return new JAXBElement<CancelFlightResponse>(_CancelFlightResponse_QNAME, CancelFlightResponse.class, null, value);
    }

}
