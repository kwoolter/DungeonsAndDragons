/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author JaneW
 */
public class D2Map {
    
    // Hash map to map a location to its available directions
    static private HashMap<Integer, EnumMap<D2LocationLink.D2Direction, D2LocationLink>> locationMap;
    
    // Constructor
    public D2Map () {
        locationMap = new HashMap<Integer, EnumMap<D2LocationLink.D2Direction, D2LocationLink>>();
    }
    
    // Retrieve the directions for a specified location
    static public EnumMap<D2LocationLink.D2Direction, D2LocationLink> getDirections(int iSelected) {
        return locationMap.get(iSelected);
    }
    
    // Load th map from file
    public void load() {
        System.out.println("Loading Map..."); 
        Scanner s = null;
        // Path p1 = Paths.get(".","D2MapTest.csv");
        Path p1 = Paths.get(".","D2Map.csv");

        try {

            InputStream in = Files.newInputStream(p1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            int iFromLocationID;
            int iToLocationID;
            String sDirection;
            D2LocationLink.D2Direction enumDirection;
            String sLinkDescription;
            boolean bLocked;
            String sLockedReason;
            String sUnlockedReason;
            D2LocationLink linkTo;
            D2LocationLink linkFrom;


            while ((line = reader.readLine()) != null) {

                s = new Scanner(line).useDelimiter(",");
                iFromLocationID = s.nextInt();  // Start location
                iToLocationID = s.nextInt(); // Destination location
                sDirection  = s.next();  // Direction of travel
                sLinkDescription = s.next();  // description of link
                
                // Is optional locked data there?
                if(s.hasNextBoolean()) {
                    bLocked = s.nextBoolean();
                    sLockedReason = s.next();
                    sUnlockedReason = s.next();
                }
                else {
                    bLocked = false;
                    sLockedReason = "";
                    sUnlockedReason = "";
                }
                
                D2LocationLink.D2Direction direction = D2LocationLink.D2Direction.fromString(sDirection);
                        
                // Create a link in both directions
                linkTo = new D2LocationLink( direction,iToLocationID, sLinkDescription,bLocked,sLockedReason,sUnlockedReason );
                linkFrom = new D2LocationLink( direction.getOpposite(), iFromLocationID,sLinkDescription,bLocked,sLockedReason, sUnlockedReason);

                
                EnumMap<D2LocationLink.D2Direction, D2LocationLink> mapDirections;
                
                // Get the available directions for the FROM location
                mapDirections = locationMap.get(iFromLocationID);
                if(mapDirections == null) {
                    mapDirections = new EnumMap<D2LocationLink.D2Direction, D2LocationLink>(D2LocationLink.D2Direction.class);
                    locationMap.put(iFromLocationID, mapDirections);
                }
                mapDirections.put(linkTo.getDirectionTo(),linkTo);
                
                // Get the directions for the TO location
                mapDirections = locationMap.get(iToLocationID);
                if(mapDirections == null) {
                    mapDirections = new EnumMap<D2LocationLink.D2Direction, D2LocationLink>(D2LocationLink.D2Direction.class);
                    locationMap.put(iToLocationID, mapDirections);
                }
                mapDirections.put(linkFrom.getDirectionTo(),linkFrom);
        

            }
            reader.close();

        } catch (IOException x) {

            System.err.println("STOP:" + x);

        }
        s.close();
        
        System.out.println(locationMap);

    }

    // Print put the details of a selected location in the map
    static public void getLocationDescription(int iSelected, StringBuffer buffDescriptionOutput) {
        
        buffDescriptionOutput.append(iSelected + ". You are ");
        buffDescriptionOutput.append(D2LocationFactory.get(iSelected) +".");

        EnumMap<D2LocationLink.D2Direction, D2LocationLink> mapDirections = getDirections(iSelected);
        D2LocationLink link;
        
        // See what exits exist
        Set<D2LocationLink.D2Direction> exits = mapDirections.keySet();
        int iExits = exits.size();
        
        // If only one...
        if(iExits == 1) {
                buffDescriptionOutput.append(" There is an exit ");
        }
        else {
                buffDescriptionOutput.append(" There are exits ");
        }
        
        
        // How many exits have we got from the directions map?
        int iFound =0;
        
        // Print out all of the exits
        for(D2LocationLink.D2Direction e : D2LocationLink.D2Direction.values()) {
            link = mapDirections.get(e);
            
            // Did we find anything for this direction?
            if(link != null) {
                ++iFound;
                // If this is the last exit of many..
                if(iFound == iExits  && iExits > 1) {
                    buffDescriptionOutput.append("and ");
                }
                buffDescriptionOutput.append(link.getDirectionTo() + " " + link.getDescription());
                
                // If there are still more exits to come...
                if(iFound < iExits) {
                    buffDescriptionOutput.append(", ");
                }
                
            }
        }
        
        buffDescriptionOutput.append(".\n");
            

            
    }
    
    // Print put the details of a selected location in the map
    public void print(int iSelected) {
        
        StringBuffer sBuff = new StringBuffer("");
        this.getLocationDescription(iSelected, sBuff);
        
        System.out.print(sBuff);
            

            
    }
    
    // print out all of the locations in the map
    public void test() {
        int iCurrentLocation = 1;
        
        for(int i: locationMap.keySet()) {
            this.print(i);
        }
        
    }
    
}
