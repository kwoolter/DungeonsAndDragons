/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import dungeonsanddragons.D2CharacterStats.D2Abilities;
import java.util.Objects;

/**
 *
 * @author JaneW
 */
public class D2Modifier extends Object {
    
    private String sName;
    private D2CharacterStats.D2Abilities enumAbility;
    private int iModifier;
    private int iTurns;
    
    // Constructor
    public D2Modifier ( String sNewName,
                        D2CharacterStats.D2Abilities enumNewAbility,
                        int iNewModifier,
                        int iNewTurns) {
        this.sName = sNewName;
        this.enumAbility = enumNewAbility;
        this.iModifier = iNewModifier;
        this.iTurns = iNewTurns;
        
    }

    public D2Modifier(D2Modifier modNew) {
        this.sName = modNew.getName();
        this.enumAbility = modNew.getAbility();
        this.iModifier = modNew.getModifier();
        this.iTurns = modNew.getTurns();
    }
    

    public D2Abilities getAbility() {
        return enumAbility;
    }

    public int getModifier() {
        return iModifier;
    }

    public String getName() {
        return sName;
    }

    public int getTurns() {
        return iTurns;
    }
    
    
    
    // Equals operator
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final D2Modifier other = (D2Modifier) obj;
        if (!Objects.equals(this.sName, other.sName)) {
            return false;
        }
        if (this.enumAbility != other.enumAbility) {
            return false;
        }
        return true;
    }
    // Hash code operator
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.sName);
        hash = 89 * hash + (this.enumAbility != null ? this.enumAbility.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "D2Modifier{" + "Name=" + sName + ", Ability=" + enumAbility + ", Modifier=" + iModifier + ", Turns=" + iTurns + '}';
    }
    
    
    
    
    
}
