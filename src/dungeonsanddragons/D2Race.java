/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author U394198
 */
public class D2Race {
    // Enumeration for the different Races available
    public enum D2Races { DWARF("Dwarf"), ELF("Elf"), GNOME("Gnome"), HALFELF("Half Elf"), HALFORC("Half Orc"), HALFLING("Halfing"), HUMAN("Human"), UNKNOWN("Unkown");
    
        private String sDisplayName;
        D2Races(String sNewDisplayName) {
            this.sDisplayName = sNewDisplayName;
        }
        @Override public String toString() { return this.sDisplayName; } 
        
        // Get an enum from a string - any case match
        static public D2Races fromString(String sClass) {
            D2Races enumSelected = UNKNOWN;
            for(D2Races e : D2Races.values()) {
                if(e.name().equals(sClass.toUpperCase())) {
                    enumSelected = e;
                }
            }
            return enumSelected;
        }
    };
    
    // Private member variables:
    private D2Races enumRace;
    private String sName;
    
    // Ability bonuses and penalties
    // Size
    D2Character.CharacterSize enumSize;
    // Speed
    int iSpeed;
    

    // Default Constructor
    public D2Race() {
        enumRace = D2Races.UNKNOWN;
        
    }
    
    public D2Race(D2Races enumNewRace) {

        this.enumRace = enumNewRace;

        
    }
    
    public D2Race (String sNewName, String sNewSize, int iNewSpeed) {

        this.enumRace = D2Races.fromString(sNewName);
        this.enumSize = D2Character.CharacterSize.fromString(sNewSize);
        this.iSpeed = iNewSpeed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final D2Race other = (D2Race) obj;
        if (this.enumRace != other.getEnumRace()) {
            return false;
        }

        return true;
    }
    
    
    
    @Override public String toString() {
      
        return enumRace.toString();
    }

    public D2Races getEnumRace() {
        return enumRace;
    }
    
    public String getName() {
        return enumRace.toString();
    }

    public int getSpeed() {
        return iSpeed;
    }
    
    

    public D2Character.CharacterSize getSize() {
        return enumSize;
    }
    
    
    
    public void print() {
        System.out.println("Name=" + getName() + ", Size=" + enumSize.toString() + ", Speed="+ Integer.toString(iSpeed));
    }
    
    
}
