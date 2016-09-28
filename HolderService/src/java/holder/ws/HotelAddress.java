/**
 *
 * @author Marios - Marie
 */
package holder.ws;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name= "HotelAddress")
public class HotelAddress {
    protected String country;
    protected String city;
    protected String streetName;
    protected int number;
    protected int zipCode;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getNumber() {
        return number;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
   
    
}
