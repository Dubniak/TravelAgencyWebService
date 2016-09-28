
package ws.travelgood;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.travelgood.entities.FlightWithStatus;
import ws.travelgood.entities.HotelWithStatus;
import ws.travelgood.entities.Itinerary;

/**
 *
 * @author Marie
 */
public class Utils {
    
    public static XMLGregorianCalendar toXMLGregorianCalendarDate(String date)
    {
         DatatypeFactory df = null;
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
        
        return df.newXMLGregorianCalendar("2011-09-15");        
    } 
    
     public static final XMLGregorianCalendar toXMLCalendarDate(String inDate){
        
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try{
         date = format.parse(inDate);
        }
        catch(ParseException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            
        }
        
        return xmlCalendar;
    
    }
    
    public boolean isTerminatable(Itinerary itinerary)
    {
        boolean isTerminatable = false;
        GregorianCalendar today = new GregorianCalendar();
        
        for(FlightWithStatus f : itinerary.getFlights())
        {
            GregorianCalendar departureDate = f.getFlight().getDepartureTime().toGregorianCalendar();
            
            if(departureDate.before(today))
            {
                isTerminatable = true;
                break;
            }
        }
        
        if(!isTerminatable)
        {
            for(HotelWithStatus h : itinerary.getHotels())
            {
                GregorianCalendar arrivalDate = Utils.toXMLCalendarDate(h.getArrivalDate()).toGregorianCalendar();

                if(arrivalDate.before(today))
                {
                    isTerminatable = true;
                    break;
                }
            }
        }
        
        return isTerminatable;
    }
}
