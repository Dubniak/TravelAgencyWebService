
package niceviewservice.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the niceviewservice.ws package. 
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

    private final static QName _BookHotel_QNAME = new QName("http://ws.niceviewservice/", "bookHotel");
    private final static QName _CancelHotelResponse_QNAME = new QName("http://ws.niceviewservice/", "cancelHotelResponse");
    private final static QName _GetHotelRequest_QNAME = new QName("http://ws.niceviewservice/", "getHotelRequest");
    private final static QName _Booking_QNAME = new QName("http://ws.niceviewservice/", "Booking");
    private final static QName _BookHotelResponse_QNAME = new QName("http://ws.niceviewservice/", "bookHotelResponse");
    private final static QName _CancelHotelFault_QNAME = new QName("http://ws.niceviewservice/", "cancelHotelFault");
    private final static QName _Hotel_QNAME = new QName("http://ws.niceviewservice/", "Hotel");
    private final static QName _Address_QNAME = new QName("http://ws.niceviewservice/", "Address");
    private final static QName _BookHotelFault_QNAME = new QName("http://ws.niceviewservice/", "bookHotelFault");
    private final static QName _GetHotelReply_QNAME = new QName("http://ws.niceviewservice/", "getHotelReply");
    private final static QName _GetHotelsResponse_QNAME = new QName("http://ws.niceviewservice/", "getHotelsResponse");
    private final static QName _BookHotelRequest_QNAME = new QName("http://ws.niceviewservice/", "bookHotelRequest");
    private final static QName _CancelHotel_QNAME = new QName("http://ws.niceviewservice/", "cancelHotel");
    private final static QName _CancelHotelRequest_QNAME = new QName("http://ws.niceviewservice/", "cancelHotelRequest");
    private final static QName _GetHotels_QNAME = new QName("http://ws.niceviewservice/", "getHotels");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: niceviewservice.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHotelsResponse }
     * 
     */
    public GetHotelsResponse createGetHotelsResponse() {
        return new GetHotelsResponse();
    }

    /**
     * Create an instance of {@link GetHotels }
     * 
     */
    public GetHotels createGetHotels() {
        return new GetHotels();
    }

    /**
     * Create an instance of {@link BookHotel }
     * 
     */
    public BookHotel createBookHotel() {
        return new BookHotel();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link CancelHotelResponse }
     * 
     */
    public CancelHotelResponse createCancelHotelResponse() {
        return new CancelHotelResponse();
    }

    /**
     * Create an instance of {@link GetHotelRequest }
     * 
     */
    public GetHotelRequest createGetHotelRequest() {
        return new GetHotelRequest();
    }

    /**
     * Create an instance of {@link Booking }
     * 
     */
    public Booking createBooking() {
        return new Booking();
    }

    /**
     * Create an instance of {@link BookHotelResponse }
     * 
     */
    public BookHotelResponse createBookHotelResponse() {
        return new BookHotelResponse();
    }

    /**
     * Create an instance of {@link BookHotelRequest }
     * 
     */
    public BookHotelRequest createBookHotelRequest() {
        return new BookHotelRequest();
    }

    /**
     * Create an instance of {@link GetHotelReply }
     * 
     */
    public GetHotelReply createGetHotelReply() {
        return new GetHotelReply();
    }

    /**
     * Create an instance of {@link CancelHotel }
     * 
     */
    public CancelHotel createCancelHotel() {
        return new CancelHotel();
    }

    /**
     * Create an instance of {@link CancelHotelRequest }
     * 
     */
    public CancelHotelRequest createCancelHotelRequest() {
        return new CancelHotelRequest();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookHotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "bookHotel")
    public JAXBElement<BookHotel> createBookHotel(BookHotel value) {
        return new JAXBElement<BookHotel>(_BookHotel_QNAME, BookHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelHotelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "cancelHotelResponse")
    public JAXBElement<CancelHotelResponse> createCancelHotelResponse(CancelHotelResponse value) {
        return new JAXBElement<CancelHotelResponse>(_CancelHotelResponse_QNAME, CancelHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotelRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "getHotelRequest")
    public JAXBElement<GetHotelRequest> createGetHotelRequest(GetHotelRequest value) {
        return new JAXBElement<GetHotelRequest>(_GetHotelRequest_QNAME, GetHotelRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Booking }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "Booking")
    public JAXBElement<Booking> createBooking(Booking value) {
        return new JAXBElement<Booking>(_Booking_QNAME, Booking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookHotelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "bookHotelResponse")
    public JAXBElement<BookHotelResponse> createBookHotelResponse(BookHotelResponse value) {
        return new JAXBElement<BookHotelResponse>(_BookHotelResponse_QNAME, BookHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "cancelHotelFault")
    public JAXBElement<String> createCancelHotelFault(String value) {
        return new JAXBElement<String>(_CancelHotelFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "Hotel")
    public JAXBElement<Hotel> createHotel(Hotel value) {
        return new JAXBElement<Hotel>(_Hotel_QNAME, Hotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Address }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "Address")
    public JAXBElement<Address> createAddress(Address value) {
        return new JAXBElement<Address>(_Address_QNAME, Address.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "bookHotelFault")
    public JAXBElement<String> createBookHotelFault(String value) {
        return new JAXBElement<String>(_BookHotelFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotelReply }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "getHotelReply")
    public JAXBElement<GetHotelReply> createGetHotelReply(GetHotelReply value) {
        return new JAXBElement<GetHotelReply>(_GetHotelReply_QNAME, GetHotelReply.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotelsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "getHotelsResponse")
    public JAXBElement<GetHotelsResponse> createGetHotelsResponse(GetHotelsResponse value) {
        return new JAXBElement<GetHotelsResponse>(_GetHotelsResponse_QNAME, GetHotelsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookHotelRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "bookHotelRequest")
    public JAXBElement<BookHotelRequest> createBookHotelRequest(BookHotelRequest value) {
        return new JAXBElement<BookHotelRequest>(_BookHotelRequest_QNAME, BookHotelRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelHotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "cancelHotel")
    public JAXBElement<CancelHotel> createCancelHotel(CancelHotel value) {
        return new JAXBElement<CancelHotel>(_CancelHotel_QNAME, CancelHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelHotelRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "cancelHotelRequest")
    public JAXBElement<CancelHotelRequest> createCancelHotelRequest(CancelHotelRequest value) {
        return new JAXBElement<CancelHotelRequest>(_CancelHotelRequest_QNAME, CancelHotelRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotels }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.niceviewservice/", name = "getHotels")
    public JAXBElement<GetHotels> createGetHotels(GetHotels value) {
        return new JAXBElement<GetHotels>(_GetHotels_QNAME, GetHotels.class, null, value);
    }

}
