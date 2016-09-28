package ws.travelgood.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eirini
 */
@XmlRootElement()
public class CreditCard {
    private String name;
    private String number;
    private String expirationMonth;
    private String expirationYear;

    
    @XmlAttribute()
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    @XmlAttribute()
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
    
    @XmlAttribute
    public String getExpirationMonth() {
        return expirationMonth;
    }
    @XmlAttribute
    public String getExpirationYear() {
        return expirationYear;
    }

}
