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
import java.util.EnumMap;

/**
 *
 * @author JaneW
 */
public class D2ClassFactory {
    
    static private EnumMap<D2Class.D2Classes, D2Class> mapClasses;
    
    public D2ClassFactory() {
                mapClasses = new EnumMap<D2Class.D2Classes, D2Class>(D2Class.D2Classes.class);
        }
    
    static public D2Class getRandom() {
        int iSelected = D2Dice.roll(1,mapClasses.size(),0)-1;
        return mapClasses.get(iSelected);
    }
    
    static public D2Class getD2Class(D2Class.D2Classes enumNewClass) {
         return mapClasses.get(enumNewClass);
    }
    
    static public D2Class getD2Class(String sNewClassName) {
            return mapClasses.get(D2Class.D2Classes.fromString(sNewClassName));

    }
    
    public void load() {
        System.out.println("Loading Classes..."); 
        Scanner s = null;
        Path p1 = Paths.get(".","D2Classes.csv");

        try {

            InputStream in = Files.newInputStream(p1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;


            while ((line = reader.readLine()) != null) {

                s = new Scanner(line).useDelimiter(",");
                
                String sClassName = s.next(); // Class Name
                D2Class.D2Classes enumKey = D2Class.D2Classes.fromString(sClassName);
                
                
                D2Class newElement = new D2Class(sClassName, // CLASS NAME
                                        new D2Dice(1,s.nextInt()), // Hit dice

                                        s.nextInt(), // BAB start level
                                        s.nextDouble(), // BAB per level
                                        new D2Dice(s.nextInt(),s.nextInt())); // Wealth dice


                newElement.print();  
                mapClasses.put(enumKey, newElement);
            }
            reader.close();

        } catch (IOException x) {
            System.err.println("STOP:" + x);
        }
        s.close();

    }
    
}
