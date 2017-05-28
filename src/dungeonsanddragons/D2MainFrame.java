/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Insets;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author JaneW
 */
public class D2MainFrame extends javax.swing.JFrame {
    
    private D2EquipmentChooser winEquipmentChooser;
    private D2ItemView winItemView;
    private D2CharacterTabbedView winCharacterView1;
    private D2CharacterTabbedView winCharacterView2;
    
    private D2Character charPlayer1;
    private D2Character charPlayer2;
    private enum gameState {START, DEAL, FIGHT, COMPUTER_TURN, END};
    private gameState enumCurrentState;
    private CardGame game;
    

    /**
     * Creates new form D2MainFrame
     */
    public D2MainFrame() {
        
        UIManager.put("TabbedPane.selected", Color.BLACK);
        UIManager.put("TabbedPane.borderHightlightColor", Color.BLACK);
        UIManager.put("TabbedPane.tabAreaBackground", Color.BLACK);
        UIManager.put("TabbedPane.unselectedBackground", Color.GREEN);
        UIManager.put("TabbedPane.contentAreaColor", Color.BLACK);
        UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.unselectedTabForeground", Color.BLACK);
        UIManager.put("TabbedPane.selectHighlight", Color.GRAY);
        UIManager.put("TabbedPane.unselectedTabForeground", Color.BLACK);
        
        
        

        initComponents();
        this.setPreferredSize(new java.awt.Dimension(600, 800));
        
        winEquipmentChooser = new D2EquipmentChooser();
        winItemView = new D2ItemView();
             
        winCharacterView1 = new D2CharacterTabbedView();       
        getContentPane().add(winCharacterView1);
        winCharacterView1.setBounds(0,56,350,316);
        
        winCharacterView2 = new D2CharacterTabbedView();       
        getContentPane().add(winCharacterView2);
        winCharacterView2.setBounds(350,56,350,316);

        //winItemView.setBounds(450,0,200,260);

        
        //winItemView.setItem(new D2Weapon("Demon Axe","axe.png",D2WeaponSlot.RIGHT_HAND,1,1,new D2Dice(1,6),1));

        //winEquipmentChooser.setBounds(0,0,450,408);
        enumCurrentState = gameState.START;
        game = new CardGame();
        game.addPlayer(new Player("Jack", Player.PlayerType.COMPUTER));
        game.addPlayer(new Player("Computer", Player.PlayerType.COMPUTER));
        initCharacters();

        
        
        winCharacterView1.setView(charPlayer1);
        winCharacterView2.setView(charPlayer2);
        this.setState();
        
        this.setSize(716,620);
        this.setTitle("D2 - Dungeons and Dragons");
        
        
    }
    
    protected void initCharacters() {
        charPlayer1 = D2CharacterFactory.getRandom();
        charPlayer2 = D2CharacterFactory.getRandom();
       
        D2Weapon weaponPlayer1 = D2WeaponFactory.getRandom();
        D2Weapon weaponPlayer2 = D2WeaponFactory.getRandom();
        charPlayer1.addWeapon(weaponPlayer1);
        charPlayer2.addWeapon(weaponPlayer2);
        charPlayer1.addEquipment(D2EquipmentFactory.getRandom());
        charPlayer2.addEquipment(D2EquipmentFactory.getRandom());
    }
    
    private void setState() {
        switch (enumCurrentState) {
            case START:
                this.butRestart.setEnabled(false);
                this.butDeal.setEnabled(true);
                this.butP1Melee.setEnabled(false);
                this.butP2Melee.setEnabled(false);
                break;
            case DEAL:
                this.butRestart.setEnabled(true);
                this.butDeal.setEnabled(true);
                this.butP1Melee.setEnabled(false);
                this.butP2Melee.setEnabled(false);
                break;
            case FIGHT:
                this.butRestart.setEnabled(true);
                this.butDeal.setEnabled(false);
                if(game.getCurrentPlayerNumber()== 1) {
                    this.butP1Melee.setEnabled(true);
                    this.butP2Melee.setEnabled(false);                    
                }
                else {
                    this.butP1Melee.setEnabled(false);
                    this.butP2Melee.setEnabled(true);  
                }
                break;
            case END:
                this.butRestart.setEnabled(true);
                this.butP1Melee.setEnabled(false);
                this.butP2Melee.setEnabled(false);                
            default:
                break;
        }        
        this.lblPlayer1Name.setText(game.getPlayer(1).getName());
        this.lblPlayer1Score.setText(Integer.toString(game.getPlayer(1).getWins()));
        this.lblPlayer2Name.setText(game.getPlayer(2).getName());
        this.lblPlayer2Score.setText(Integer.toString(game.getPlayer(2).getWins()));
            
    }
    
    // Perform a Melee attack turn on a specified character
    private void attack(D2Character charAttacker, D2Character charDefender) {
        StringBuffer sComments = new StringBuffer();
        textComments.append("\n" + charAttacker.getName() + " Melee Attack...");

        charAttacker.attackMelee(charDefender, sComments);
        textComments.append(sComments.toString());
        
        if(charDefender.getHP()<0) {
            game.getCurrentPlayer().addWin();
            // lose all of your money
            charDefender.setMoney(new D2Money(0));
            enumCurrentState = gameState.END;
        }
        

        winCharacterView1.setView(charPlayer1);
        winCharacterView2.setView(charPlayer2);    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblPlayer1Name = new javax.swing.JLabel();
        lblPlayer2Name = new javax.swing.JLabel();
        lblPlayer1Score = new javax.swing.JLabel();
        lblPlayer2Score = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        butP1Melee = new javax.swing.JButton();
        butDeal = new javax.swing.JButton();
        butRestart = new javax.swing.JButton();
        butP2Melee = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textComments = new javax.swing.JTextArea();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        lblPlayer1Name.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayer1Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPlayer1Name.setText("Player 1");

        lblPlayer2Name.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayer2Name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPlayer2Name.setText("Player 2");

        lblPlayer1Score.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayer1Score.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPlayer1Score.setText("Score:");

        lblPlayer2Score.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayer2Score.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPlayer2Score.setText("Score:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPlayer1Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPlayer2Name))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPlayer1Score)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPlayer2Score)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer1Name)
                    .addComponent(lblPlayer2Name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer1Score)
                    .addComponent(lblPlayer2Score))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        butP1Melee.setText("Melee");
        butP1Melee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butP1MeleeActionPerformed(evt);
            }
        });

        butDeal.setText("Deal");
        butDeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDealActionPerformed(evt);
            }
        });

        butRestart.setLabel("Restart");
        butRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRestartActionPerformed(evt);
            }
        });

        butP2Melee.setText("Melee");
        butP2Melee.setToolTipText("");
        butP2Melee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butP2MeleeActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textComments.setColumns(1);
        textComments.setEditable(false);
        textComments.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textComments.setLineWrap(true);
        textComments.setRows(5);
        textComments.setWrapStyleWord(true);
        textComments.setDoubleBuffered(true);
        textComments.setMaximumSize(new java.awt.Dimension(800, 800));
        textComments.setPreferredSize(new java.awt.Dimension(600, 800));
        jScrollPane1.setViewportView(textComments);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(butP1Melee)
                        .addGap(32, 32, 32)
                        .addComponent(butDeal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butRestart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(butP2Melee)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butP1Melee)
                    .addComponent(butDeal)
                    .addComponent(butRestart)
                    .addComponent(butP2Melee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butP1MeleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butP1MeleeActionPerformed
        attack(charPlayer1,charPlayer2);
        int i = game.getNextPlayer();
        this.setState();
        playSound("sword-remove.wav");
        

    }//GEN-LAST:event_butP1MeleeActionPerformed

    private void butP2MeleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butP2MeleeActionPerformed

        
        attack(charPlayer2,charPlayer1);
        int i = game.getNextPlayer();
        this.setState();
        playSound("sword-remove.wav");

    }//GEN-LAST:event_butP2MeleeActionPerformed

    private void butRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRestartActionPerformed
        // TODO add your handling code here:
        enumCurrentState = gameState.START;
        initCharacters();
        this.setState();
        winCharacterView1.setView(charPlayer1);
        winCharacterView2.setView(charPlayer2);
        
        textComments.setText("");
        
        playSound("fanfare.wav");
    }//GEN-LAST:event_butRestartActionPerformed

    private void butDealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDealActionPerformed
        // TODO add your handling code here:
        enumCurrentState = gameState.FIGHT;
        this.setState();
    }//GEN-LAST:event_butDealActionPerformed

    
    
    private void playSound(String sSoundFile) {
        try {
          String sClip = "/dungeonsanddragons/resources/sounds/" + sSoundFile ;  
            
          Clip clip = AudioSystem.getClip();
          AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sClip));
          clip.open(inputStream);
          clip.start(); 
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(D2MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D2MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D2MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D2MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new D2MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butDeal;
    private javax.swing.JButton butP1Melee;
    private javax.swing.JButton butP2Melee;
    private javax.swing.JButton butRestart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPlayer1Name;
    private javax.swing.JLabel lblPlayer1Score;
    private javax.swing.JLabel lblPlayer2Name;
    private javax.swing.JLabel lblPlayer2Score;
    private javax.swing.JTextArea textComments;
    // End of variables declaration//GEN-END:variables
}
