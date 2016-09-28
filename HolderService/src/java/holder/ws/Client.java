/**
 *
 * @author Marios - Marie
 */
package holder.ws;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Client")
public class Client {
    protected String clientId;
    protected List<Itinerary> itinerary;
    
    public Client(){
        itinerary = new ArrayList<Itinerary>();
    }
    
    public void setClientId(String value){
        this.clientId = value;
    }
    
    public String getClientId(){
        return this.clientId;
    }
    
    public void setItinerary(List<Itinerary> value){
        this.itinerary = value;
    }
    
    public void addItinerary(Itinerary value){
        System.out.println("addItinerary for " + value.getItineraryId());
        if (itinerary == null)
            System.out.println("itinerary == null");
        itinerary.add(value);
    }
    
    public List<Itinerary> getItinerary(){
        return this.itinerary;
    }
}
