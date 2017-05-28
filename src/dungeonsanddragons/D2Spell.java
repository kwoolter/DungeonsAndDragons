/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class D2Spell extends D2Item {
    
    private D2CharacterStats.D2Abilities enumAbility;
    private int iModifier;
    private int iDuration;
    D2Dice diceEffect;
    
    public D2Spell(String sNewName, String sNewImageFileName, int iNewValue) {
    
    super(sNewName, sNewImageFileName, 0,iNewValue);
    
    }
}
