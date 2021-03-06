/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class D2WeaponView extends javax.swing.JPanel {

    /**
     * Creates new form D2ItemView
     */
    public D2WeaponView() {
        initComponents();
    }
    
    public void setItem(D2Weapon itemNew) {
        lblName.setText(itemNew.getName());
        lblWeight.setText(Integer.toString(itemNew.getWeight()));
        lblValue.setText(Integer.toString(itemNew.getValue()));
        lblDamage.setText(Integer.toString(itemNew.getMinDamage())+"-"+Integer.toString(itemNew.getMaxDamage()));
        System.out.println(itemNew.getImageFileName());
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(itemNew.getImageFileName())));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblValue = new javax.swing.JLabel();
        lblWeight = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDamage = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(0, 0, 0));
        setToolTipText("Item Viewer");

        lblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Item Name");
        lblName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Value:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Weight:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblValue.setForeground(new java.awt.Color(255, 255, 255));
        lblValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblValue.setText("jLabel3");

        lblWeight.setForeground(new java.awt.Color(255, 255, 255));
        lblWeight.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWeight.setText("jLabel4");

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dungeonsanddragons/resources/weapons/Frostblade.png"))); // NOI18N
        lblImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Damage:");

        lblDamage.setForeground(new java.awt.Color(255, 255, 255));
        lblDamage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDamage.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDamage)
                                    .addComponent(lblValue)
                                    .addComponent(lblWeight)))
                            .addComponent(lblImage)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblDamage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblWeight))
                .addGap(4, 4, 4)
                .addComponent(lblImage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDamage;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblValue;
    private javax.swing.JLabel lblWeight;
    // End of variables declaration//GEN-END:variables
}
