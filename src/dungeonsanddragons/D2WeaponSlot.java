/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public enum D2WeaponSlot {
    LEFT_HAND, RIGHT_HAND, BOTH_HANDS, BACK, UNKNOWN;
    
    D2WeaponSlot() {
        
    }
    
    static public D2WeaponSlot fromString(String sSlot) {
        D2WeaponSlot slotResult = UNKNOWN;
        for (D2WeaponSlot p : D2WeaponSlot.values()) {
            if(p.name().equals(sSlot)) {
                return p;
            }
        }
        return slotResult;
    }
}
