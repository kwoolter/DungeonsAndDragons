/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author U394198
 */
public class D2Class {
    
    // Enumeration of the available classes
    public enum D2Classes { BARBARIAN("Barbarian"), BARD("Bard"), CLERIC("Cleric"), DRUID("Druid"), FIGHTER("Fighter"), MONK("Monk"), PALADIN("Paladin"), RANGER("Ranger"), ROGUE("Rogue"), WIZARD("Wizard"), SORCERER("Sorcerer"), UNKNOWN("Unknown");
        
        private String sDisplayName;
        
        D2Classes(String sNewDisplayName) {
            this.sDisplayName = sNewDisplayName;

        }
        
        @Override public String toString() { return this.sDisplayName; } 
        
        // Get an enum from a string - any case match
        static public D2Classes fromString(String sClass) {
            D2Classes enumSelected = UNKNOWN;
            for(D2Classes e : D2Classes.values()) {
                if(e.name().equals(sClass.toUpperCase())) {
                    enumSelected = e;
                }
            }
            return enumSelected;
        }
    };
    
    // The clas of this object
    private D2Classes enumClass;
    
    // Starting Wealth Dice
    D2Dice diceStartingWealth;
    
    // Hit Die
    D2Dice diceHit;
    
    // Skill Ranks per level
    int iSkillRanksPerLevel;
    
    // Base Attack Bonus per level
    double dLevelBAB;
    
    // What level does BAB kick in
    int iStartBABLevel;
    
    public D2Class() {
        this.enumClass = D2Classes.UNKNOWN;
    }
    
    public D2Class(D2Classes enumNewClass) {
        this.enumClass = enumNewClass;
    }
    
    public D2Class (String sNewName, D2Dice diceNewHitDice, int iNewStartBABLevel, double dNewLevelBAB, D2Dice diceNewWealthDice) {
        this.enumClass = D2Classes.fromString(sNewName);
        this.diceHit = diceNewHitDice;
        this.iStartBABLevel = iNewStartBABLevel;
        this.dLevelBAB = dNewLevelBAB;
        this.diceStartingWealth = diceNewWealthDice;
    }
    
    @Override public String toString() {
        return enumClass.toString();
    }
    public void print() {
        System.out.println("Class=" + getName() + " HitDie=" + diceHit + 
                            "BABStartLevel" + iStartBABLevel +
                            "BABPerLevel=" + dLevelBAB +
                            "Wealth=" + diceStartingWealth);
    }
    public String getName() {
        return enumClass.toString();
    }

    public D2Dice getHitDice() {
        return diceHit;
    }

    public D2Dice getStartingWealthDice() {
        return diceStartingWealth;
    }
    
    // Calculate base attack bonus based on current level and Class BAB stats
    public int getBAB(int iCurrentXP) {
        double dBAB=0;
        int iLevel = D2Rules.XPtoLevel(iCurrentXP);

        dBAB = (double)(iLevel - this.iStartBABLevel + 1) * (double)this.dLevelBAB;

        return (int)dBAB;
    }
    
}
