/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import javax.swing.JLabel;

/**
 *
 * @author JaneW
 */
public class D2Avatar {
    
    private String sImageFileName;
    private int iLocation;
    private int iPrevLocation;
    private JLabel lblView;
    private D2Character character;

    public D2Avatar(String sImageFileName, D2Character character) {
        this.sImageFileName = sImageFileName;
        this.character = character;
        this.iLocation = 0;
        this.iPrevLocation = 0;
        
        lblView = new JLabel();
        lblView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dungeonsanddragons/resources/avatars/" + sImageFileName)));
        
    }

    public int getLocation() {
        return iLocation;
    }

    public int getPrevLocation() {
        return iPrevLocation;
    }
    
    

    public void setLocation(int iLocation) {
        if(this.iLocation == 0) {
            this.iLocation = iLocation;
        }
        this.iPrevLocation = this.iLocation;
        this.iLocation = iLocation;
    }

    public JLabel getView() {
        return lblView;
    }


    
    
}
