/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class D2Money extends D2Item {
 
    public D2Money () {
        super("Money","gold.png",0, 0);
    }
    
    public D2Money (int iNewValue) {
        super("Money","gold.png", 0, iNewValue);
    }
    
    public D2Money add(D2Money moneyAdd) {
        this.iValue += moneyAdd.getValue();
        return this;
    }
    
    @Override public void print() {
        System.out.println("Money = " + Integer.toString(getGold()) + "g" + Integer.toString(getSilver()) + "s" +Integer.toString(getBronze())+"b");
    }
    
    @Override public String toString() {
        String money ="";
        
        if(getGold()>0) {
            money += Integer.toString(getGold()) + "g";
        }
        else if(getSilver()>0) {
            money += Integer.toString(getSilver()) + "s";
            
        }
        money += Integer.toString(getBronze())+"b";
        
        return money;
    }
    
    public int getBronze() {
        return iValue % 100;
    }
    
    public int getSilver() {
        return (iValue / 100) % 100;
    }
    
        
    public int getGold() {
        return iValue / 10000;
    }
    
}
