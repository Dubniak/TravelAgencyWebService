
package ws.travel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.travel package. 
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

    private final static QName _ItineraryGetInputElement_QNAME = new QName("http://travel.ws", "itineraryGetInputElement");
    private final static QName _HotelAddOutputElement_QNAME = new QName("http://travel.ws", "hotelAddOutputElement");
    private final static QName _ItineraryCreateInputElement_QNAME = new QName("http://travel.ws", "itineraryCreateInputElement");
    private final static QName _ItineraryCancelOutputElement_QNAME = new QName("http://travel.ws", "itineraryCancelOutputElement");
    private final static QName _ItineraryCancelInputElement_QNAME = new QName("http://travel.ws", "itineraryCancelInputElement");
    private final static QName _FlightsAddOutputElement_QNAME = new QName("http://travel.ws", "flightsAddOutputElement");
    private final static QName _ItineraryBookOutputElement_QNAME = new QName("http://travel.ws", "itineraryBookOutputElement");
    private final static QName _ItineraryBookFault_QNAME = new QName("http://travel.ws", "itineraryBookFault");
    private final static QName _ItineraryCreateOutputElement_QNAME = new QName("http://travel.ws", "itineraryCreateOutputElement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.travel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ItineraryGetOutputElement }
     * 
     */
    public ItineraryGetOutputElement createItineraryGetOutputElement() {
        return new ItineraryGetOutputElement();
    }

    /**
     * Create an instance of {@link FlightsGetInputElement }
     * 
     */
    public FlightsGetInputElement createFlightsGetInputElement() {
        return new FlightsGetInputElement();
    }

    /**
     * Create an instance of {@link FlightsAddInputElement }
     * 
     */
    public FlightsAddInputElement createFlightsAddInputElement() {
        return new FlightsAddInputElement();
    }

    /**
     * Create an instance of {@link FlightsGetOutputElement }
     * 
     */
    public FlightsGetOutputElement createFlightsGetOutputElement() {
        return new FlightsGetOutputElement();
    }

    /**
     * Create an instance of {@link ItineraryBookInputElement }
     * 
     */
    public ItineraryBookInputElement createItineraryBookInputElement() {
        return new ItineraryBookInputElement();
    }

    /**
     * Create an instance of {@link HotelAddInputElement }
     * 
     */
    public HotelAddInputElement createHotelAddInputElement() {
        return new HotelAddInputElement();
    }

    /**
     * Create an instance of {@link HotelsGetOutputElement }
     * 
     */
    public HotelsGetOutputElement createHotelsGetOutputElement() {
        return new HotelsGetOutputElement();
    }

    /**
     * Create an instance of {@link HotelsGetInputElement }
     * 
     */
    public HotelsGetInputElement createHotelsGetInputElement() {
        return new HotelsGetInputElement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "itineraryGetInputElement")
    public JAXBElement<String> createItineraryGetInputElement(String value) {
        return new JAXBElement<String>(_ItineraryGetInputElement_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "hotelAddOutputElement")
    public JAXBElement<Boolean> createHotelAddOutputElement(Boolean value) {
        return new JAXBElement<Boolean>(_HotelAddOutputElement_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "itineraryCreateInputElement")
    public JAXBElement<String> createItineraryCreateInputElement(String value) {
        return new JAXBElement<String>(_ItineraryCreateInputElement_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "itineraryCancelOutputElement")
    public JAXBElement<Boolean> createItineraryCancelOutputElement(Boolean value) {
        return new JAXBElement<Boolean>(_ItineraryCancelOutputElement_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "itineraryCancelInputElement")
    public JAXBElement<String> createItineraryCancelInputElement(String value) {
        return new JAXBElement<String>(_ItineraryCancelInputElement_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "flightsAddOutputElement")
    public JAXBElement<Boolean> createFlightsAddOutputElement(Boolean value) {
        return new JAXBElement<Boolean>(_FlightsAddOutputElement_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "itineraryBookOutputElement")
    public JAXBElement<Boolean> createItineraryBookOutputElement(Boolean value) {
        return new JAXBElement<Boolean>(_ItineraryBookOutputElement_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "itineraryBookFault")
    public JAXBElement<String> createItineraryBookFault(String value) {
        return new JAXBElement<String>(_ItineraryBookFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://travel.ws", name = "itineraryCreateOutputElement")
    public JAXBElement<String> createItineraryCreateOutputElement(String value) {
        return new JAXBElement<String>(_ItineraryCreateOutputElement_QNAME, String.class, null, value);
    }

}
