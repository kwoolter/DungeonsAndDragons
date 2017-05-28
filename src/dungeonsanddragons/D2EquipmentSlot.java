/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public enum D2EquipmentSlot {
    HEAD, EARS, NECK, BODY, ARMS, FINGERS, LEGS, HANDS, FEET, UNKNOWN;
    
    D2EquipmentSlot() {
        
    }
    static public D2EquipmentSlot fromString(String sSlot) {
        D2EquipmentSlot slotResult = UNKNOWN;
        for (D2EquipmentSlot p : D2EquipmentSlot.values()) {
            if(p.name().equals(sSlot)) {
                return p;
            }
        }
        return slotResult;
    }
}
