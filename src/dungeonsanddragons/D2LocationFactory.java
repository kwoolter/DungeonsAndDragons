/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author JaneW
 */
public class D2LocationFactory {
    
    static private HashMap<Integer, D2Location> arlLocations;
    
    public D2LocationFactory() {
                arlLocations = new HashMap<Integer, D2Location>();
        }
    
    static public D2Location getRandom() {
        int iSelected = D2Dice.roll(1,arlLocations.size(),0)-1;
        return arlLocations.get(iSelected);
    }
    
    
    // Get a location by ID
    static public D2Location get(int iSelected) {
        return arlLocations.get(iSelected);
    }    
    
    public void load() {
        System.out.println("Loading Locations..."); 
        Scanner s = null;
        // Path p1 = Paths.get(".","D2LocationsTest.csv");
        Path p1 = Paths.get(".","D2Locations.csv");

        try {

            InputStream in = Files.newInputStream(p1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            int iLocationID;
            String sLocationDescription;
            String sImageFileName;

            while ((line = reader.readLine()) != null) {

                s = new Scanner(line).useDelimiter(",");
                iLocationID = s.nextInt(); // Unique ID for this location
                sLocationDescription  = s.next(); // Description of the location
                sImageFileName = s.next(); // Type of image to be used for this location om the map
                Color colorBackground = new Color(s.nextInt(),s.nextInt(),s.nextInt()); //  Background colour to be used for this location on the map
                
                D2Location newElement = new D2Location(iLocationID, sLocationDescription, sImageFileName,colorBackground);
                
                System.out.println(newElement);
                arlLocations.put(iLocationID,newElement);
            }
            reader.close();

        } catch (IOException x) {

            System.err.println("STOP:" + x);

        }
        s.close();

    }
    
}
