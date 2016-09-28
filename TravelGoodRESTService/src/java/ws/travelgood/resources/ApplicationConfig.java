package ws.travelgood.resources;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Emanuela-Maria
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.travelgood.resources.ItineraryResetResource.class);
        resources.add(ws.travelgood.resources.ItineraryResource.class);
        resources.add(ws.travelgood.resources.ItineraryStatusResource.class);
        resources.add(ws.travelgood.resources.FlightResource.class);
        resources.add(ws.travelgood.resources.HotelsResource.class);
    }
    
}
