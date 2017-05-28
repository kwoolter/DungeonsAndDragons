/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;


/**
 *
 * @author JaneW
 */

// A factory that create a map of modifiers for each object
public class D2ModifierFactory {
    
    // The map that will hold the list of modifiers for each object
    static private Map<String, D2ModifierMap> mapModifiers;
    
    // Default constructor
    public D2ModifierFactory() {
                mapModifiers = new HashMap<>();
        }
    
    // Get the modifier map for a specified object
    static public D2ModifierMap getObjectModifiers(String sObjectName) {
         return mapModifiers.get(sObjectName);
    }
    
   // Add a modifier to the specified objects map
    static public void addD2Modifier(String sNewModifiedObject, D2Modifier modNew) {
        
        // See if a map of modifiers already exists for this object in the map
        D2ModifierMap mapMatch =  mapModifiers.get(sNewModifiedObject);
        
        // If no then create an entry in the map with an empty list
        if(mapMatch==null) {
            mapMatch = new D2ModifierMap();
            mapModifiers.put(sNewModifiedObject,mapMatch);
        }
        // add the new modifier to the map for selected object
        mapMatch.addModifier(modNew);
        
    }
    
    // Load the map of modifiers for each object from file
    public void load() {
        System.out.println("Loading Modifiers..."); 
        Scanner s = null;
        Path p1 = Paths.get(".","D2Modifiers.csv");

        try {

            InputStream in = Files.newInputStream(p1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;


            while ((line = reader.readLine()) != null) {
                
                s = new Scanner(line).useDelimiter(",");
                
                String sModifiedObject = s.next();  // Name of Object to be modified
                String sModifierName = s.next(); //NAME of modifier
                String sAbilityName = s.next(); // ABILITY
                D2CharacterStats.D2Abilities enumAbility = D2CharacterStats.D2Abilities.fromString(sAbilityName);
                int iModifierValue = s.nextInt(); // MODIFIER
                int iModifierTurns = s.nextInt(); // TURNS
               
                // Create the modifier
                D2Modifier newElement = new D2Modifier(sModifierName,enumAbility, iModifierValue, iModifierTurns);

                              
                // Add the modifer to the list of modifiers for that object
                             
                addD2Modifier(sModifiedObject, newElement);
            }
            reader.close();

        } catch (IOException x) {
            System.err.println("STOP:" + x);
        }
        s.close();
        
        System.out.println("Map of modifiers:"+mapModifiers);

    }
    
    public void test() {
        //Enumeration<String> e;
        //e = mapModifiers.keys();
        
        
    }
    
}
