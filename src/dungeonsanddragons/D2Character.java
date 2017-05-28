 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author JaneW
 */
public class D2Character {
    
    // Size enum
    static enum CharacterSize { LARGE (-1, "Large"), MEDIUM (0, "Medium"), SMALL (1, "Small"), UNKNOWN(0, "Unknown");
        private int iSizeModifier;
        private String sDisplayName;
        CharacterSize(int iNewSizeModifier,String sNewName) {
            this.iSizeModifier = iNewSizeModifier;
            this.sDisplayName = sNewName;
        }
        public int getModifier() { return iSizeModifier;}
        public String toString() {
            return this.sDisplayName;
        }
        // Get an enum from a string - any case match
        static public D2Character.CharacterSize fromString(String sClass) {
            CharacterSize enumSelected = UNKNOWN;
            for(CharacterSize e : CharacterSize.values()) {
                if(e.name().equals(sClass.toUpperCase())) {
                    enumSelected = e;
                }
            }
            return enumSelected;
        }
    };
    
    D2CharacterStats stats;
    private String sName;
    private D2Race myD2Race;
    private D2Class myD2Class;
    private String sImageFileName;
    
    
    private int iArmorBonus;
    private int iShieldBonus;
    private int iHP;
    private int iMaxHP;
    private int iXP;
    private int iLevel;

    private D2ModifierMap mapModifiers;  
    private ArrayList<D2Item> arlInventory;
    private EnumMap<D2EquipmentSlot, D2Equipment> mapEquipped;
    private EnumMap<D2WeaponSlot, D2Weapon> mapWeapons;
       
    private D2Weapon weaponMain;
    private D2Money moneyWealth;
    
    public D2Character(String sNewName, D2Race raceNew, D2Class classNew, int iNewHP, D2CharacterStats statsNew) {
        sName = sNewName;
        myD2Race = raceNew;
        myD2Class = classNew;
        iHP = iNewHP;
        iLevel = 1;
        iXP = 0;

        stats = new D2CharacterStats(statsNew);
        
        // Initialize th character
        init();
        
        // Temp code for demo
        //stats.roll();
        
        // Add the modifiers for the character's race...
        // mapModifiers.addModifiers(D2ModifierFactory.getObjectModifiers(raceNew.getEnumRace().name()));
        
       
    }
    
    protected void init() {
        

        mapModifiers = new D2ModifierMap();
        arlInventory = new ArrayList<D2Item>();
        mapEquipped = new EnumMap<D2EquipmentSlot, D2Equipment>(D2EquipmentSlot.class);
        mapWeapons = new EnumMap<D2WeaponSlot, D2Weapon>(D2WeaponSlot.class);
        
        // Set starting wealth base on dice
        moneyWealth = new D2Money(myD2Class.getStartingWealthDice().roll()*10);
        
        // Calculate max HP based on maximum hit die and CON bonus * level
        iMaxHP = myD2Class.getHitDice().getMax() + (stats.getStatModifier(D2CharacterStats.D2Abilities.CON, D2CharacterStats.D2ModifierType.BONUS)*getLevel());
        iHP = iMaxHP;
        
        if(sImageFileName==null) {
            sImageFileName="unknown.png";
        }
        
        System.out.println("Created a character:" + this.toString());
    }

    public CharacterSize getSize() {
        return myD2Race.getSize();
    }

    public String getImageFileName() {
        return "/dungeonsanddragons/resources/characters/" + sImageFileName;
    }

    public void setImageFileName(String sImageFileName) {
        this.sImageFileName = sImageFileName;
    }
   
    
    
    public int getLevel() {
        return D2Rules.XPtoLevel(iXP);
    }

    public void setLevel(int iLevel) {
        this.iLevel = iLevel;
    }

    public int getXP() {
        return iXP;
    }

    public void setXP(int iXP) {
        this.iXP = iXP;
    }

    public String getD2Class() {
        return myD2Class.getName();
    }

    public String getRace() {
        return myD2Race.getName();
    }
    
    public int getSpeed() {
        return myD2Race.getSpeed();
    }

    public int getMaxHP() {
        return iMaxHP;
    }

    
    
    public int getHP() {
        return iHP;
    }

    public void setHP(int iHP) {
        this.iHP = iHP;
    }
    

     public String getName() {
         return sName;
     }
     
     // Derive the characters armour class
     public int getArmourClass() {
         
         // Work our armour bonus from equipped items
         int iEquipmentArmour =0;
         for(D2EquipmentSlot slot :  D2EquipmentSlot.values()) {
             D2Equipment equip = getEquipment(slot);
             if(equip != null) {
                iEquipmentArmour+=equip.getArmourBonus();
             }
         }
         
         int iDEXModifier = stats.getStatModifier(D2CharacterStats.D2Abilities.DEX);
         int iSizeModifier =  myD2Race.getSize().getModifier();
         
         System.out.println("\nArmour Class=10 + Armour("+iEquipmentArmour+") + "+
                 "Shield("+iShieldBonus+") + DEX(" + iDEXModifier +") + Size(" +
                 iSizeModifier +")");
         // return 10 + Armour bonus + shield bouns + dexterity modifier
         return 10 + 
                 iEquipmentArmour + 
                 iShieldBonus + 
                 iDEXModifier +
                 iSizeModifier;
     }
     
    public int getMeleeDamage() {
        return weaponMain.getDamage();
    
    }
    
    public int doDamage(int iDamage) {
        if((iHP -= iDamage) <0); {
            this.die();
        }
        return iHP;
    }
    
    // Print Character Details
    public void print() {
        System.out.println("Name:" + sName +
                            " Race=" + myD2Race.toString() + " Class=" + myD2Class.toString());
        System.out.println("HP:" + Integer.toString(iHP));
        System.out.println("Stats=" + stats);
        // Print out all of the modifiers..
        System.out.println("Modifiers=" + mapModifiers);
        
        System.out.println("Inventory:" + arlInventory.size() + " items");
        if(arlInventory.isEmpty()) {
            System.out.println("Empty!!");            
        }
        for(int i=0;i<arlInventory.size();i++) {
            arlInventory.get(i).print();
        }
        
        System.out.print("Equipment:");
        System.out.println(mapEquipped);
        this.moneyWealth.print();
        System.out.println("Modifiers: " + this.mapModifiers);
            
    }
    // Melee attack rules
    public int attackMelee(D2Character charDefender, StringBuffer sComments) {
        
        sComments.append(sName + " attacks " + charDefender.getName());
        
        // Roll the attack dice
        D2CharacterStats statsLatest = this.getStats();
        int iChance = D2Dice.roll(1,20,0);
        int iStrengthModifier = statsLatest.getStatModifier(D2CharacterStats.D2Abilities.STR,D2CharacterStats.D2ModifierType.BONUS);
        int iBAB = myD2Class.getBAB(this.iXP);
        
        // and calculate Attack Bonus
        int iAttackBonus = iChance + iStrengthModifier + iBAB;
            
        
        D2Weapon weaponSelected = mapWeapons.get(D2WeaponSlot.RIGHT_HAND);
        if(weaponSelected == null) {
            weaponSelected=new D2Weapon("Bare Hands","mystery.png",D2WeaponSlot.RIGHT_HAND,1,1,new D2Dice(1,1),0);
            boolean bOK=this.addWeapon(weaponSelected);
        }
        sComments.append(" with his " + weaponSelected);
        sComments.append("\nAttack roll = roll(" + iChance + ") + STR(" + iStrengthModifier +") + BAB(" +iBAB+") = " + iAttackBonus);
        sComments.append("\nStats=" + statsLatest);
        // Get weapon damage + STR modifier
        int iDamage = weaponSelected.getDamage() + iStrengthModifier;
        
        //Critical Hit rule if natural 20
        if(iChance == 20) {
            // Roll again...
            if((D2Dice.roll(1,20,0) + iStrengthModifier)>charDefender.getArmourClass()) {
                iDamage += weaponSelected.getDamage() + iStrengthModifier;

                sComments.append("\nCritical hit " + Integer.toString(iDamage) + " damage!");
            }
           else {
                sComments.append("\n" + Integer.toString(iDamage) + " damage!");
           }          
        }
        // Hit rule
        else if(iAttackBonus>= charDefender.getArmourClass()) {
            sComments.append("\n" + Integer.toString(iDamage) + " damage!");
        }
        // Miss rule
        else {
            iDamage = 0;
            sComments.append("\nMissed!");
        }
        
        // Do damage and see if you killed the defender....

        if(charDefender.doDamage(iDamage) <0) {
            sComments.append("\n" + charDefender.getName()+" the " + charDefender.getD2Class() + " " + charDefender.getRace() + " is dead.");
            sComments.append("\n" + this.getName()+" finds " + charDefender.getMoney() + "money\n\n" );
            this.addItem(charDefender.getMoney());
            this.addXP(charDefender);
            charDefender.setMoney(new D2Money(0));
        }
        
        return iDamage;
        
    }
    
    // Gain XP based on who you defeated
    public int addXP(D2Character charDefeated) {
        int iCurrentLevel = D2Rules.XPtoLevel(iXP);
        int iLevelDiff = charDefeated.getLevel() - this.getLevel();
        this.iXP += (iLevelDiff+1)*300;
        if(D2Rules.XPtoLevel(iXP) > iCurrentLevel) {
            this.levelUp();
        }
        
        return this.iXP;
    }
    
    // Level Up!!!
    public int levelUp() {
        iHP+=5;
        return ++iLevel;
    }
    
    // You are dead
    public void die() {
        // lose some XP
        this.iXP -= D2Dice.roll(1,3,0);
        if(this.getXP()<0) {
            setXP(0);
        }

    }
    
    public boolean addItem(D2Item itemNew) {
        return arlInventory.add(itemNew);
    }
    
    public boolean addItem(D2Money moneyNew) {
        this.moneyWealth.add(moneyNew);
        return true;
    }
    
    public boolean addEquipment(D2Equipment equipNew) {
        boolean bOK = true;
        if (mapEquipped.get(equipNew.getSlot()) != null) {
            System.out.println(equipNew.getSlot() + " full!");
            bOK=false;
        }
        else {
            mapEquipped.put(equipNew.getSlot(), equipNew);
        }
    return bOK;
    }
    public D2Equipment getEquipment(D2EquipmentSlot slotSelected) {
        return mapEquipped.get(slotSelected);
    }
    public D2Equipment removeEquipment(D2EquipmentSlot slotSelected) {
        return mapEquipped.remove(slotSelected);
    }
    
    
    public boolean addWeapon(D2Weapon equipNew) {
        boolean bOK = true;
        if (mapWeapons.get(equipNew.getSlot()) != null) {
            System.out.println(equipNew.getSlot() + " full!");
            bOK=false;
        }
        else {
            mapWeapons.put(equipNew.getSlot(), equipNew);
        }
    return bOK;
    }
    
    public D2Weapon getWeapon(D2WeaponSlot slotSelected) {
        return mapWeapons.get(slotSelected);
    }
    
    public D2Weapon removeWeapon(D2WeaponSlot slotSelected) {
        return mapWeapons.remove(slotSelected);
    }
    
    // Add an ability modifier...
    public boolean addModifier(D2Modifier modNewModifier) {
        
        this.mapModifiers.addModifier(modNewModifier);
        
        return true;
    }
    
    // Add a whole map of ability modifiers the the characters existing map
    public void addModifiers(D2ModifierMap mapNew) {
        this.mapModifiers.addModifiers(mapNew);
    }
    
    // REmove a modifier
    public boolean removeModifier(D2Modifier modNewModifier) {
        boolean bOK=true;
        mapModifiers.removeModifier(modNewModifier);
        return bOK;      
    }
    
    // Look at all of the modifiers for a specific abilitly and return total
    public int getTotalModifiers(D2CharacterStats.D2Abilities enumAbility) {
        
        return mapModifiers.getTotalModifiers(enumAbility);
        
    }
    
    // Re roll ability stats
    public void rollAbilities() {
        stats.roll();
        stats.setStat(D2CharacterStats.D2Abilities.UNKNOWN, 0);
    }

    public D2Money getMoney() {
        return moneyWealth;
    }

    public void setMoney(D2Money moneyWealth) {
        this.moneyWealth = moneyWealth;
    }
    // Return a ststs object that iclude the base stats plus any modifiers
    public D2CharacterStats getStats() {
        D2CharacterStats statsTotal = new D2CharacterStats();
        
        for(D2CharacterStats.D2Abilities e : D2CharacterStats.D2Abilities.values()) {
            int iStatValue = stats.getStat(e) + getTotalModifiers(e);
            statsTotal.setStat(e, iStatValue);
        }
        return statsTotal;
    }
    
    @Override public String toString() {
        String sDescription = "";
        sDescription += sName + myD2Race + myD2Class + stats;
        
        
        return sDescription;
    }
    
    
    
    
}
