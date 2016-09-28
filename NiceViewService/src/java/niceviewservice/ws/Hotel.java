/*
 * @author Eirini
 */
package niceviewservice.ws;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Hotel")
public class Hotel {

    protected String name;
    protected String reservationService;
    protected Address address;
    protected double price;
    protected boolean guarantee;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the reservationService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationService() {
        return reservationService;
    }

    /**
     * Sets the value of the reservationService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationService(String value) {
        this.reservationService = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the guarantee property.
     * 
     */
    public boolean isGuarantee() {
        return guarantee;
    }

    /**
     * Sets the value of the guarantee property.
     * 
     */
    public void setGuarantee(boolean value) {
        this.guarantee = value;
    }

}
