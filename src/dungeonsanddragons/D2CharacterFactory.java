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

/**
 *
 * @author JaneW
 */
public class D2CharacterFactory {
    
    static private ArrayList<D2Character> arlCharacters;
    
    public D2CharacterFactory() {
                arlCharacters = new ArrayList<D2Character>();
        }
    
    static public D2Character getRandom() {
        int iSelected = D2Dice.roll(1,arlCharacters.size(),0)-1;
        return arlCharacters.get(iSelected);
    }
    
    public void load() {
        System.out.println("Loading Characters..."); 
        Scanner s = null;
        Path p1 = Paths.get(".","D2Characters.csv");

        try {

            InputStream in = Files.newInputStream(p1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            String sName;
            String sRace;
            String sClass;
            String sImageFileName;

            while ((line = reader.readLine()) != null) {

                s = new Scanner(line).useDelimiter(",");
                sName = s.next();
                sRace  = s.next();
                D2Race raceNew = D2RaceFactory.getD2Race(sRace);
                sClass = s.next();
                D2Class classNew = D2ClassFactory.getD2Class(sClass);
                sImageFileName = s.next();
                
                D2Character newElement = new D2Character(sName, raceNew, classNew, s.nextInt(), // HP
                                                            new D2CharacterStats(s.nextInt(), // STR
                                                                                s.nextInt(), // DEX
                                                                                s.nextInt(), // CON
                                                                                s.nextInt(), // WIS
                                                                                s.nextInt(), // INT
                                                                                s.nextInt())); // CHA

                newElement.setImageFileName(sImageFileName);

                
                // Add all of the racial modifiers for this race...
                newElement.addModifiers(D2ModifierFactory.getObjectModifiers(raceNew.getEnumRace().name()));
                
                // Re-roll abilities to over-write loaded ones
                newElement.rollAbilities();
                
                newElement.print();
                arlCharacters.add(newElement);
            }
            reader.close();

        } catch (IOException x) {

            System.err.println("STOP:" + x);

        }
        s.close();

    }
    
}
