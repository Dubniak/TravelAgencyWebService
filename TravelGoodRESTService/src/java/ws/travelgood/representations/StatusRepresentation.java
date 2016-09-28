package ws.travelgood.representations;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eirini
 */

@XmlRootElement()
public class StatusRepresentation extends Representation {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
