/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class D2Equipment extends D2Item {
    
       
    private D2EquipmentSlot slotValid;
    private int iArmourBonus;

    public int getArmourBonus() {
        return iArmourBonus;
    }

    public void setArmourBonus(int iArmourBonus) {
        this.iArmourBonus = iArmourBonus;
    }

    public int getAttackBonus() {
        return iAttackBonus;
    }

    public void setAttackBonus(int iAttackBonus) {
        this.iAttackBonus = iAttackBonus;
    }
       private int iAttackBonus;
       
       public D2Equipment() {
            super("Unknown Equipment","unknown.jpg", 0, 0 );
            slotValid = D2EquipmentSlot.UNKNOWN;
       }
       
       public D2Equipment (String sNewName, String sNewImageFileName,D2EquipmentSlot slotNewValid, int iNewWeight, int iNewValue, int iNewArmourBonus)  {
           super(sNewName, sNewImageFileName,iNewWeight, iNewValue);
           slotValid = slotNewValid;
           iArmourBonus = iNewArmourBonus;
       }
       
       public D2EquipmentSlot getSlot() {
           return slotValid;
       }
       
       @Override public void print() {
           System.out.println(  sName + "(" + slotValid.name() +"," + 
                                "Weight=" + Integer.toString(iWeight)+",Value="+Integer.toString(iValue)+")");
           
       }
       
       @Override public String toString() {
           return sName + "(" + slotValid.name() +"," + 
                                "Weight=" + Integer.toString(iWeight)+",Value="+Integer.toString(iValue)+")";
       }
       
       @Override public String getImageFileName() {
        return "/dungeonsanddragons/resources/equipment/" + sImageFileName;
    }
       
      
}
