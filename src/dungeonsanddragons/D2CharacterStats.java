/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author KeithW
 */

import java.util.Arrays;
import java.util.EnumMap;

public class D2CharacterStats {
    
    static public enum D2Abilities { STR, DEX, CON, WIS, INT, CHA, UNKNOWN;
    
        D2Abilities() {
            
        }
        // Get an enum from a string - any case match
        static public D2Abilities fromString(String sClass) {
            D2Abilities enumSelected = UNKNOWN;
            for(D2Abilities e : D2Abilities.values()) {
                if(e.name().equals(sClass.toUpperCase())) {
                    enumSelected = e;
                }
            }
            return enumSelected;
        }
    };
    
    public enum D2ModifierType { VALUE, BONUS, PENALTY };
    
    private EnumMap<D2Abilities, Integer> mapAbilities;

    
    public D2CharacterStats(int iSTR, int iDEX, int iCON, int iWIS, int iINT, int iCHA) {
        mapAbilities = new EnumMap<D2Abilities, Integer>(D2Abilities.class);
        mapAbilities.put(D2Abilities.STR, iSTR);
        mapAbilities.put(D2Abilities.DEX, iDEX);
        mapAbilities.put(D2Abilities.CON, iCON);
        mapAbilities.put(D2Abilities.WIS, iWIS);
        mapAbilities.put(D2Abilities.INT, iINT);
        mapAbilities.put(D2Abilities.CHA, iCHA);
    }
    
    public D2CharacterStats() {
          mapAbilities = new EnumMap<D2Abilities, Integer>(D2Abilities.class);
      }
    // Copy Constructor
    public D2CharacterStats(D2CharacterStats statsNew) {
        mapAbilities = new EnumMap<D2Abilities, Integer>(D2Abilities.class);
        
        for(D2Abilities e: D2Abilities.values()) {
            int i = statsNew.getStat(e);
            if(i >=0 ) {
                this.setStat(e, statsNew.getStat(e));
            }

        }
     
    }

    @Override
    public String toString() {
        return "D2CharacterStats{" + "mapAbilities=" + mapAbilities + '}';
    }
    
    

    public void setStat(D2Abilities enumNewAbility, int iNewAbilityStat) {
        mapAbilities.put(enumNewAbility, iNewAbilityStat);
    }
    // Get a specified Stat
    public int getStat(D2Abilities enumAbility) {
        Integer i = mapAbilities.get(enumAbility);
        if(i == null) {
            i=0;
        }
        return i;
    }
    
    // Default is to get the full value of teh modifier
    public int getStatModifier(D2Abilities enumAbility) {
        return getStatModifier(enumAbility, D2ModifierType.VALUE);
        }
    
    // Get a base stat modifier with an option to just get either the BONUS or PENALY value
    public int getStatModifier(D2Abilities enumAbility, D2ModifierType enumType) {
        int iBaseStat = this.getStat(enumAbility);
        int iModifier = 0;
                
        if(iBaseStat<10) {
                iModifier = (int) Math.floor((((double)iBaseStat - 11)/2));
        }
        else {
                iModifier = (int) Math.ceil(((double)iBaseStat - 11)/2);
        }
        
               
        // Now check to see if just the BONUS or PENALTY was requested...
        switch (enumType) {
            case BONUS:
                if(iModifier <0) {
                    iModifier = 0;
                }
                break;
            case PENALTY:
                if(iModifier >0) {
                    iModifier = 0;
                }
                break;
            default:
                break;
        }
        
        return iModifier;
    }
    
    // Populate each stat with a new roll
    public void roll() {
        // Array to store 4 d6 dice rolls
       int [] arrayRolls = new int[4];

       // For each ability....
       for(D2Abilities e: D2Abilities.values()) {
           // Roll 4 x d6
            for(int i=0;i<4;++i) {
                arrayRolls[i] = D2Dice.roll(1,6,0);
            }
            // Sort ascending
            Arrays.sort(arrayRolls);
            
            // Remove the lowest number
            arrayRolls[0] = 0;
            
            // Total up the dice
            int iSum = 0;
            for(int i:arrayRolls) {
                    iSum += i;
            }
            // set the stat
            this.setStat(e, iSum);
        }
   }
}
