/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.awt.Color;
import java.util.EnumMap;

/**
 *
 * @author JaneW
 */
public class D2Location {
    
    // The ID of this location
    private int iLocationID;
    
    // The description of this location
    private String sLocationDescription;
    
    // Type of image style for this room on the map
    private String sImageType;
    
    // Background colour for tis location
    private Color colourBackground;
    
    // The map of links to whi8ch you can travel to from here
    private EnumMap<D2LocationLink.D2Direction, D2LocationLink> mapLinks;
    
    // Constructor
    public D2Location (int iNewID, String sNewDescription, String sNewImageType, Color colorNew) {
        this.iLocationID = iNewID;
        this.sLocationDescription = sNewDescription;
        this.sImageType = sNewImageType;
        this.colourBackground = colorNew;
        this.mapLinks = new EnumMap<D2LocationLink.D2Direction, D2LocationLink>(D2LocationLink.D2Direction.class);
    }
    
    // Add a link to another location
    public void addLink(D2LocationLink linkNew) {
        this.mapLinks.put(linkNew.getDirectionTo(), linkNew);
        
    }
    
    // Return a copy of the links that you can move to from here
    public EnumMap<D2LocationLink.D2Direction, D2LocationLink> getLinks() {
        EnumMap<D2LocationLink.D2Direction, D2LocationLink> mapCopy = new EnumMap<D2LocationLink.D2Direction, D2LocationLink>(D2LocationLink.D2Direction.class);
        
        for(D2LocationLink.D2Direction e : D2LocationLink.D2Direction.values()) {
                    mapCopy.put(e, this.mapLinks.get(e));
        }
        return mapCopy;
    }

    @Override
    public String toString() {
        return sLocationDescription;
    }
    
    // Get the type of image to be used for this locations
    public String getImageType() {
        return sImageType;
    }

    // Get the backgropund colour to be used for this location
    public Color getBackgroundColour() {
        return colourBackground;
    }
    
    
    
    
    
    
}
