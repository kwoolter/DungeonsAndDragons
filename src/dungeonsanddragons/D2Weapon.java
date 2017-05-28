/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class D2Weapon extends D2Item {

    private D2Dice diceDamage;
    private int iAttackBonus;
    private D2WeaponSlot slotValid;

    public D2WeaponSlot getSlot() {
        return slotValid;
    }

    public void setSlot(D2WeaponSlot slotValid) {
        this.slotValid = slotValid;
    }

    public D2Weapon(String sNewName, String sNewImageFileName, D2WeaponSlot slotValid, int iNewWeight, int iNewValue, D2Dice diceNewDamage, int iNewAttackBonus) {
        super(sNewName,sNewImageFileName, iNewWeight, iNewValue);
        diceDamage = new D2Dice(diceNewDamage);
        iAttackBonus = iNewAttackBonus;
        this.slotValid = slotValid;
    }

    @Override public String getName() {
        String sWeaponName = super.getName();
        if(iAttackBonus>0) {
        sWeaponName.concat("+" + Integer.toString(iAttackBonus));
        }
        return sWeaponName;
    }
    
    @Override public String getImageFileName() {
        return "/dungeonsanddragons/resources/weapons/" + super.sImageFileName;
    }

    public int getDamage() {
        return diceDamage.roll(iAttackBonus);
    }

    public int getMaxDamage() {
        return diceDamage.getMax() + (iAttackBonus);
    }

    public int getMinDamage() {
        return diceDamage.getMin() + (iAttackBonus);
    }

    @Override public void print() {
        System.out.print("Weapon Name:" + getName());
        System.out.print(", Max Damage=" + getMaxDamage());
        System.out.print(", Min Damage=" + getMinDamage()+"\n");
        super.print();
    }
    
    @Override public String toString() {
        return getName();
    }

}
