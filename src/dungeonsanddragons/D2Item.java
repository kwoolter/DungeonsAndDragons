/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class D2Item extends Object {
    protected String sName;
    protected int iWeight;
    protected int iValue;
    String sImageFileName;

    public String getImageFileName() {
        return "/dungeonsanddragons/resources/items/"+sImageFileName;
    }

    public void setImageFileName(String sImageFileName) {
        this.sImageFileName = sImageFileName;
    }
    
    public D2Item() {
        
    }
    public D2Item(String sNewName, String sNewImageFileName, int iNewWeight, int iNewValue) {
        sName = sNewName;
        sImageFileName = sNewImageFileName;
        iWeight = iNewWeight;
        iValue = iNewValue;
    }
    public String getName() {
        return sName;
    }
    public int getWeight() {
        return iWeight;
    }
    
    public int getValue() {
        return iValue;
    }
    
    public D2Money getMoneyValue() {
        return new D2Money(iValue);
    }
    
    public void print() {
        System.out.println(sName + "(Weight=" + Integer.toString(iWeight)+", Value="+Integer.toString(iValue)+")");
    }
    
    @Override public String toString() {
        return sName;
    }
    
    
}
