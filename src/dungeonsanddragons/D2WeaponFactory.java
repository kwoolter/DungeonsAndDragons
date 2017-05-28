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
public class D2WeaponFactory {
    
    static private ArrayList<D2Weapon> arlEquipment;
    
    public D2WeaponFactory() {
                arlEquipment = new ArrayList<D2Weapon>();
        }
    
    static public D2Weapon getRandom() {
        int iSelected = D2Dice.roll(1,arlEquipment.size(),0)-1;
        return arlEquipment.get(iSelected);
    }
    
    public void load() {
        System.out.println("Loading Elements..."); 
        Scanner s = null;
        Path p1 = Paths.get(".","D2Weapons.csv");

        try {

            InputStream in = Files.newInputStream(p1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;

            while ((line = reader.readLine()) != null) {

                s = new Scanner(line).useDelimiter(",");

                D2Weapon newElement = new D2Weapon(   s.next(), // name
                                                        s.next(), // Image file name
                                                            D2WeaponSlot.fromString(s.next()), // valid slot
                                                            s.nextInt(), // Weight
                                                            s.nextInt(), // Value
                                                            new D2Dice(s.nextInt(),s.nextInt()), // Damage dice
                                                            s.nextInt()); // attack bonus


                newElement.print();  
                arlEquipment.add(newElement);
            }
            reader.close();

        } catch (IOException x) {

            System.err.println("STOP:" + x);

        }
        s.close();

    }
    
}
