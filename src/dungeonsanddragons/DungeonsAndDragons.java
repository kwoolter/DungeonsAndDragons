/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author JaneW
 */
public class DungeonsAndDragons {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int iRoll;
        StringBuffer sComments = new StringBuffer("");
        
        D2LocationFactory factoryLocations= new D2LocationFactory();
        factoryLocations.load();
        
        D2Map map = new D2Map();
        map.load();
        map.test();
        
        D2ModifierFactory factoryModifiers = new D2ModifierFactory();
        factoryModifiers.load();
        D2ClassFactory factoryClasses = new D2ClassFactory();
        factoryClasses.load();
        D2RaceFactory factoryRaces = new D2RaceFactory();
        factoryRaces.load();
        D2CharacterFactory factoryCharacters = new D2CharacterFactory();
        factoryCharacters.load();
        D2EquipmentFactory factoryEquipment = new D2EquipmentFactory();
        factoryEquipment.load();
        D2WeaponFactory factoryWeapons = new D2WeaponFactory();
        factoryWeapons.load();
        

        D2MapFrame frameMap = new D2MapFrame();
        frameMap.setMap(map);
        
        frameMap.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                        System.exit(0);
                }
        });
        frameMap.setVisible(true);
        frameMap.setTitle("Map");
        
        
        D2MainFrame frame = new D2MainFrame();
        
        frame.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                        System.exit(0);
                }
        });
        frame.setVisible(true);
        
//        D2EquipmentSlot slot = D2EquipmentSlot.fromString("NECK");
//      
//        D2Character charKeith = new D2Character("Keith","Elf", "Paladin", 10, new D2CharacterStats(10,10,10,10,10,10));
//        charKeith.setImageFileName("p#ki1m.png");
//        D2Character charJack = new D2Character("Jack","Imp","Fighter",15,new D2CharacterStats(10,10,10,10,10,10));
//        D2Weapon weaponKeith = new D2Weapon("Dagger","mystery.png",D2WeaponSlot.RIGHT_HAND,1,1,new D2Dice(1,6),1);
//        D2Weapon weaponJack = new D2Weapon("Demon Axe","mystery.png",D2WeaponSlot.RIGHT_HAND,1,1,new D2Dice(1,6),1);
//        charKeith.addWeapon(weaponKeith);
//        charJack.addWeapon(weaponJack);
//        charKeith.addItem(weaponKeith);
//        charKeith.addItem(new D2Item("Key","mystery.png",0,0));
//        charKeith.addItem(new D2Money(10));
//        charKeith.addItem(new D2Money(115));
//        charJack.addItem(new D2Item("Food","mystery.png",0,0));
//        charJack.addItem(new D2Money(11505));
//        charJack.addItem(weaponJack);
//        
//        charJack.addEquipment(new D2Equipment("Big Hat", "mystery.png", D2EquipmentSlot.HEAD, 0,0));
//        charJack.addEquipment(new D2Equipment("Silly Hat", "mystery.png",D2EquipmentSlot.HEAD, 0,0));
//
//        charJack.addEquipment(new D2Equipment("Leather Trousers", "mystery.png",D2EquipmentSlot.LEGS, 0,0));
//        charKeith.addEquipment(new D2Equipment("Pendant of Life", "mystery.png",D2EquipmentSlot.NECK, 0,0));
//
//        System.err.println("Start the fight!!!");
//        for (int i=0;i<8;i++) {
//            charKeith.print();
//            charJack.print();
//            
//            D2Character charAttack = charKeith;
//            D2Character charDefend = charJack;
//
//            charAttack.attackMelee(charDefend,sComments);
//            
//            charAttack = charJack;
//            charDefend = charKeith;
//            
//            charAttack.attackMelee(charDefend,sComments);
//
//           
//        }
       
       
    }
}
