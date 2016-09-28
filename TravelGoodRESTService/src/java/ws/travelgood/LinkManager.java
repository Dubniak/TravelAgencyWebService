
package ws.travelgood;

import ws.travelgood.representations.Link;
import ws.travelgood.representations.Representation;

/**
 *
 * @author Catalin
 */
public class LinkManager {
    
    public static Link getLink(String baseURI, String relationBase, String relation, String id)
    {
        Link link = new Link();
        link.setUri(String.format("%s/%s/%s", baseURI, id, relation));
        link.setRel(String.format("%s%s", relationBase, relation));
        return link;
    }
    
    public static Link getSelfLink(String baseURI, String relationBase, String relation, String id)
    {
        Link link = new Link();
        link.setUri(String.format("%s/%s", baseURI, id));
        link.setRel(String.format("%s%s", relationBase, relation));
        return link;
    }
    
    public static Link getCancelLink(String baseURI, String relationBase, String relation, String id)
    {
        Link link = new Link();
        link.setUri(String.format("%s/%s/status", baseURI, id));
        link.setRel(String.format("%s%s", relationBase, relation));
        return link;
    }
    
    public static Link getCompositeLink(String baseURI, String relationBase, String relation, String id, String composition)
    {
        Link link = new Link();
        link.setUri(String.format("%s/%s/flights", baseURI, id));
        link.setRel(String.format("%s%s", relationBase, relation));
        return link;
    }
}
