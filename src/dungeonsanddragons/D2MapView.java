/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.util.EnumMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.Iterator;

/**
 *
 * @author JaneW
 */
public class D2MapView extends javax.swing.JPanel {

    // Stores where locations are loaded onto the logical map
    private HashMap<Integer, Vector> setVisited;
    private D2Map mapCurrent;
    // Stores the screen panels for each floor
    private SortedMap<Integer, JPanel> mapFloors;
    D2Avatar avaPlayer1;
    D2Avatar avaPlayer2;
    D2Avatar avaTroll;
    D2Avatar avaGoblin;
    private int iSelectedFloor;
    private D2Character charPlayer1;
    private int iCurrentLocation = 1;
    private int iXScreenOffset = 320;
    private int iYScreenOffset = 300;
    private int iStartLocation = 14;

    /**
     * Creates new form D2MapView
     */
    public D2MapView() {

        iSelectedFloor = 0;
        //this.iXScreenOffset = 150;
        //this.iYScreenOffset = 200;

        // List of rooms that have been created and where they are in the world
        setVisited = new HashMap<Integer, Vector>();

        // Hash map of JPanels that represent each floor
        mapFloors = new TreeMap<Integer, JPanel>();

        // Initialize GUI components
        initComponents();

        tabLevels.setSize(700, 500);


        avaPlayer1 = new D2Avatar("avatar1.png", D2CharacterFactory.getRandom());
        avaPlayer1.setLocation(1);

        avaPlayer2 = new D2Avatar("avatar2.png", D2CharacterFactory.getRandom());
        avaPlayer2.setLocation(1);

        avaTroll = new D2Avatar("troll.png", D2CharacterFactory.getRandom());
        avaTroll.setLocation(23);

        avaGoblin = new D2Avatar("goblin.png", D2CharacterFactory.getRandom());
        avaGoblin.setLocation(14);


    }

    // Specify which map to View
    public void setMap(D2Map mapCurrent) {
        this.mapCurrent = mapCurrent;
    }

    // Convert a location to a tab number
    protected int locationToTab(int iLocation) {
        int iTabNumber = 0;

        Vector<Integer> v = setVisited.get(iLocation);
        iTabNumber = floorToTab(v.get(2));

        return iTabNumber;
    }

    // Convert a floor coordinate to a tab number
    protected int floorToTab(int iFloor) {
        int iTabNumber = 0;

        Set<Integer> s = mapFloors.keySet();

        Iterator it = s.iterator();
        boolean bFound = false;
        while (it.hasNext() && bFound == false) {
            int value = (Integer) it.next();
            if (value == iFloor) {
                bFound = true;
            } else {
                ++iTabNumber;
            }

        }

        return iTabNumber;
    }

    // Display the specified Avatar
    public void displayCharacter(D2Avatar avaNew) {

        // Get the previous coordinates for this avatar
        Vector<Integer> vFrom = this.getLocationCoords(avaNew.getPrevLocation());
        int xFrom = vFrom.get(0);
        int yFrom = vFrom.get(1);
        int zFrom = vFrom.get(2);

        // Get the currrent coordinates for this avatar
        Vector<Integer> v = this.getLocationCoords(avaNew.getLocation());
        int x = v.get(0);
        int y = v.get(1);
        int z = v.get(2);

        // Get the floor panel that this character is going to be added to..
        JPanel floor = mapFloors.get(z);
        JLabel lblCharacter = avaNew.getView();

        if (floor != null) {
            // Convert to screen coords
            int iScreenFromX = xFrom * 64 + this.iXScreenOffset;
            int iScreenFromY = yFrom * 64 + this.iYScreenOffset;
            int iScreenToX = x * 64 + this.iXScreenOffset;
            int iScreenToY = y * 64 + this.iYScreenOffset;

            //  If the character is moving on the same floor then animate
            if (zFrom == z) {
                int xNew = iScreenFromX;
                int yNew = iScreenFromY;

                while (xNew != iScreenToX) {
                    lblCharacter.setLocation(xNew, yNew);
                    lblCharacter.setVisible(true);
                    this.repaint();
                    int iXDiff = iScreenToX - iScreenFromX;
                    xNew += (iXDiff == 0) ? 0 : (iXDiff > 0) ? 1 : -1;
                }

                while (yNew != iScreenToY) {

                    lblCharacter.setLocation(xNew, yNew);
                    lblCharacter.setVisible(true);
                    this.repaint();


                    int iYDiff = iScreenToY - iScreenFromY;
                    yNew += (iYDiff == 0) ? 0 : (iYDiff > 0) ? 1 : -1;

                }

            }

            // Position the Character
            lblCharacter.setBounds(iScreenToX, iScreenToY, 64, 64);
            floor.add(lblCharacter);
            floor.setComponentZOrder(lblCharacter, 0);
        }
        lblCharacter.setVisible(true);

    }

    // Display a character at a specified location
    public void displayCharacter(String sImageName, int iLocation) {
        // Convert a room number to coordinates
        Vector<Integer> vLocationCoords = setVisited.get(iLocation);

        // Display the character at the coordinates
        displayCharacter(sImageName, vLocationCoords.get(0), vLocationCoords.get(1), vLocationCoords.get(2));

    }

    // Display a character at the specified location
    public void displayCharacter(String sImageName, int x, int y, int z) {
        JLabel lblCharacter = new JLabel();
        lblCharacter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dungeonsanddragons/resources/avatars/" + sImageName)));

        // Get the floor panel that this character is going to be added to..
        JPanel floor = mapFloors.get(z);

        if (floor != null) {
            // Convert to screen coords
            int iScreenX = x * 64 + this.iXScreenOffset;
            int iScreenY = y * 64 + this.iYScreenOffset;
            // Position the Character
            lblCharacter.setBounds(iScreenX, iScreenY, 64, 64);
            floor.add(lblCharacter);
            floor.setComponentZOrder(lblCharacter, 0);
            floor.grabFocus();
        }

        //floor.paint();
        lblCharacter.setVisible(true);
    }

    // Display the NPC characters
    protected void displayNPCs() {
        //this.displayCharacter(avaTroll);
        //this.displayCharacter(avaGoblin);
        this.displayCharacter(avaPlayer2);
    }

    // Add a location to the set of visited locations
    private void visit(int iLocation, Vector vCoords) {
        setVisited.put(iLocation, vCoords);
    }

    // Get the coordinates of a specified location
    private Vector getLocationCoords(int iLocation) {
        return setVisited.get(iLocation);
    }

    // See if we have already visited a specified location
    private boolean isVisited(int iLocation) {

        return setVisited.containsKey(iLocation);
    }

    // Begin the map drawing process
    public void redraw() {
        int iStartLocation = this.iStartLocation; // Which room do you want to start at?
        //iStartLocation = avaPlayer1.getLocation();
        

        // What are the coordinates for the first room?
        int x = 0;
        int y = 0;
        int z = 0;

        // Kick off the recursive drawing process
        draw(iStartLocation, x, y, z);

        // Now add the characters
        this.displayCharacter(this.avaPlayer1);
        this.displayNPCs();

        drawRoomDescription();

        // Now add each floor's JPanel to a tabbed pane
        Set<Integer> ss = this.mapFloors.keySet();

        Iterator it = ss.iterator();

        while (it.hasNext()) {
            int value = (Integer) it.next();
            JPanel floor = mapFloors.get(value);
            this.tabLevels.add(Integer.toString(value), floor);

        }

        tabLevels.setSelectedIndex(locationToTab(iCurrentLocation));



    }

    // recursive method to draw the whole map
    public void draw(int iLocation, int x, int y, int z) {

        System.out.println("\nDrawing location " + iLocation);

        // Create string to build the image file name for this location
        String sLocationImageFileName = "";

        // String for up/down indicators
        String sRoomName = Integer.toString(iLocation);


        // Get the decription of this room
        D2Location loc = D2LocationFactory.get(iLocation);
        String sRoomDescription = loc.toString();

        // ...and the shape of the room
        sLocationImageFileName = loc.getImageType() + "/D2MapRoom";

        // Flag this location as visited and store its coordinates at the same time
        Vector vCoords = new Vector();
        vCoords.add(x);
        vCoords.add(y);
        vCoords.add(z);

        this.visit(iLocation, vCoords);

        // Get the directions available from this location
        EnumMap<D2LocationLink.D2Direction, D2LocationLink> directions = mapCurrent.getDirections(iLocation);

        // Loop through all directions
        for (D2LocationLink.D2Direction e : D2LocationLink.D2Direction.values()) {

            // get the link for the direction
            D2LocationLink link = directions.get(e);

            // Found a link?
            if (link != null) {

                System.out.println("\nFound an exist " + e);

                if (e == D2LocationLink.D2Direction.UP) {
                    sRoomName += ":+";
                } else if (e == D2LocationLink.D2Direction.DOWN) {
                    sRoomName += ":-";
                } else {
                    // Add the direction to the image file name if not UP/DOWN
                    sLocationImageFileName += e.toString().substring(0, 1);
                }

                // If we have not already been to this link location...
                if (isVisited(link.getLocationTo()) == false) {

                    System.out.println("\nExploring exit " + link.getLocationTo() + " " + e);

                    int iXShift = 0;
                    int iYShift = 0;
                    int iZShift = 0;

                    switch (e) {
                        case NORTH:
                            iYShift = -1;
                            break;
                        case SOUTH:
                            iYShift = 1;
                            break;
                        case EAST:
                            iXShift = 1;
                            break;
                        case WEST:
                            iXShift = -1;
                            break;
                        case UP:
                            iZShift = 1;
                            break;
                        case DOWN:
                            iZShift = -1;
                            break;

                    }
                    // Recursively draw all connecting rooms...
                    draw(link.getLocationTo(), x + iXShift, y + iYShift, z + iZShift);

                }
            }
        }
        // Append the file type to the generated image file name
        sLocationImageFileName += ".png";

        // Draw this room at the specified logical position
        drawRoom(x, y, z, sLocationImageFileName, sRoomName, sRoomDescription, loc.getBackgroundColour());

    }

    // Draw the panel that describes teh current location
    public void drawRoomDescription() {


        D2Location locCurrent = D2LocationFactory.get(this.iCurrentLocation);

        // Create an empty string buffer to hold the location description
        StringBuffer locDescription = new StringBuffer("");

        // Get the long description of the current room
        D2Map.getLocationDescription(iCurrentLocation, locDescription);

        // Display the description
        txtDescription.setText(locDescription.toString());

        Vector v = setVisited.get(this.iCurrentLocation);

        // Set the state of the GUI  e.g. directional buttons
        setState();


    }
    // Add the room to the map

    private void drawRoom(int iXOffset, int iYOffset, int iZOffset, String sFileName, String sRoomName, String sToolTip, Color colourBackground) {

        // Get the floor panel that this room is going to be added to..
        JPanel floor = mapFloors.get(iZOffset);

        // If a JPanel for this floor is not created then make a new one
        if (floor == null) {
            floor = new JPanel();
            floor.setLayout(null);
            floor.setSize(600, 400);
            floor.setBackground(new java.awt.Color(129, 110, 39));
            JLabel lblFloorName = new JLabel("Floor " + Integer.toString(iZOffset));
            lblFloorName.setForeground(new Color(50, 50, 50));
            lblFloorName.setFont(new java.awt.Font("Tahoma", 0, 18));

            floor.add(lblFloorName);
            lblFloorName.setBounds(10, 0, 100, 30);
            lblFloorName.setLocation(10, 10);

            mapFloors.put(iZOffset, floor);
        }

        System.out.println("Draw " + sFileName + " at " + iXOffset + "," + iYOffset);

        // Convert to screen coords
        int iScreenX = iXOffset * 64 + this.iXScreenOffset;
        int iScreenY = iYOffset * 64 + this.iYScreenOffset;

        // Create the label for the room
        JLabel lblRoom = new JLabel();

        // Load the appropriate image
        lblRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dungeonsanddragons/resources/maps/" + sFileName)));

        // Position it on the map
        lblRoom.setBounds(iScreenX, iScreenY, 64, 64);

        // Set its appearance
        lblRoom.setForeground(new Color(50, 50, 50));
        // lblRoom.setBackground(new Color(153, 255, 153));
        lblRoom.setBackground(colourBackground);
        lblRoom.setOpaque(true);
        lblRoom.setText(sRoomName);
        lblRoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblRoom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRoom.setToolTipText(sToolTip);

        // Add the JLabel room to the floor
        floor.add(sFileName, lblRoom);


    }

    // Move an NPC
    protected void moveNPC(D2Avatar avaSelected) {

        // Default is to stay still
        int iNewLoc = avaSelected.getLocation();

        // Find directions available from current location
        EnumMap<D2LocationLink.D2Direction, D2LocationLink> mapDirections = D2Map.getDirections(avaSelected.getLocation());

        // Roll a dice
        int iRandom = D2Dice.roll(1, mapDirections.size(), 0);

        Set<D2LocationLink.D2Direction> s = mapDirections.keySet();
        D2LocationLink.D2Direction dirNew = null;
        Iterator it = s.iterator();
        for (int i = 0; i < iRandom; ++i) {
            dirNew = (D2LocationLink.D2Direction) it.next();
        }

        if (mapDirections.get(dirNew) != null) {
            iNewLoc = mapDirections.get(dirNew).getLocationTo();
            avaSelected.setLocation(iNewLoc);
            displayCharacter(avaSelected);
        }
    }

    // Move character to a new location based on a given direction
    protected void moveLocation(D2LocationLink.D2Direction eDirection) {
        EnumMap<D2LocationLink.D2Direction, D2LocationLink> mapDirections = D2Map.getDirections(iCurrentLocation);

        D2LocationLink link = mapDirections.get(eDirection);
        if (link != null) {
            iCurrentLocation = link.getLocationTo();
            this.drawRoomDescription();
        }
        this.avaPlayer1.setLocation(iCurrentLocation);
        this.displayCharacter(this.avaPlayer1);

        tabLevels.setSelectedIndex(locationToTab(iCurrentLocation));

        moveNPC(avaGoblin);
        moveNPC(avaTroll);
        moveNPC(avaPlayer2);

    }

    // Set the stae of the GUI
    public void setState() {

        // Get the direction available form this room
        EnumMap<D2LocationLink.D2Direction, D2LocationLink> mapDirections = D2Map.getDirections(iCurrentLocation);

        // Loop through all possible direction
        for (D2LocationLink.D2Direction e : D2LocationLink.D2Direction.values()) {

            //  See if there is a link?
            D2LocationLink link = mapDirections.get(e);
            boolean bButtonState;

            // If no link then we want to disable the appropriate button
            if (link == null) {
                bButtonState = false;
            } else {
                bButtonState = true;
            }

            // Set the state of the appropriate direction button.
            switch (e) {
                case NORTH:
                    butNorth.setEnabled(bButtonState);
                    break;
                case SOUTH:
                    butSouth.setEnabled(bButtonState);
                    break;
                case EAST:
                    butEast.setEnabled(bButtonState);
                    break;
                case WEST:
                    butWest.setEnabled(bButtonState);
                    break;
                case UP:
                    butUp.setEnabled(bButtonState);
                    break;
                case DOWN:
                    butDown.setEnabled(bButtonState);
                    break;
                default:
                    break;
            }
        }


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        tabLevels = new javax.swing.JTabbedPane();
        panelLocation = new javax.swing.JPanel();
        butNorth = new javax.swing.JButton();
        butEast = new javax.swing.JButton();
        butWest = new javax.swing.JButton();
        butSouth = new javax.swing.JButton();
        butUp = new javax.swing.JButton();
        butDown = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setBackground(new java.awt.Color(0, 0, 0));

        tabLevels.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabLevels.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        panelLocation.setBackground(new java.awt.Color(255, 255, 153));

        butNorth.setText("N");
        butNorth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNorthActionPerformed(evt);
            }
        });

        butEast.setText("E");
        butEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEastActionPerformed(evt);
            }
        });

        butWest.setText("W");
        butWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butWestActionPerformed(evt);
            }
        });

        butSouth.setText("S");
        butSouth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSouthActionPerformed(evt);
            }
        });

        butUp.setText("Up");
        butUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butUpActionPerformed(evt);
            }
        });

        butDown.setText("Down");
        butDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDownActionPerformed(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setText("abc");
        txtDescription.setWrapStyleWord(true);
        txtDescription.setBorder(null);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout panelLocationLayout = new javax.swing.GroupLayout(panelLocation);
        panelLocation.setLayout(panelLocationLayout);
        panelLocationLayout.setHorizontalGroup(
            panelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLocationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLocationLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(butUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butWest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butNorth)
                            .addComponent(butSouth))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butEast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butDown)))
                .addContainerGap())
        );
        panelLocationLayout.setVerticalGroup(
            panelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLocationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLocationLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(butNorth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butSouth))
                    .addGroup(panelLocationLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelLocationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(butWest)
                            .addComponent(butEast)
                            .addComponent(butDown)
                            .addComponent(butUp))))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(tabLevels, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tabLevels)
                    .addComponent(panelLocation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void butNorthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNorthActionPerformed
        // TODO add your handling code here:
        D2LocationLink.D2Direction eDirection = D2LocationLink.D2Direction.NORTH;
        moveLocation(eDirection);


    }//GEN-LAST:event_butNorthActionPerformed

    private void butSouthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSouthActionPerformed
        // TODO add your handling code here:
        D2LocationLink.D2Direction eDirection = D2LocationLink.D2Direction.SOUTH;
        moveLocation(eDirection);
    }//GEN-LAST:event_butSouthActionPerformed

    private void butWestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butWestActionPerformed
        // TODO add your handling code here:
        D2LocationLink.D2Direction eDirection = D2LocationLink.D2Direction.WEST;
        moveLocation(eDirection);
    }//GEN-LAST:event_butWestActionPerformed

    private void butEastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEastActionPerformed
        // TODO add your handling code here:
        D2LocationLink.D2Direction eDirection = D2LocationLink.D2Direction.EAST;
        moveLocation(eDirection);
    }//GEN-LAST:event_butEastActionPerformed

    private void butUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butUpActionPerformed
        // TODO add your handling code here:
        D2LocationLink.D2Direction eDirection = D2LocationLink.D2Direction.UP;
        moveLocation(eDirection);
    }//GEN-LAST:event_butUpActionPerformed

    private void butDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDownActionPerformed
        // TODO add your handling code here:
        D2LocationLink.D2Direction eDirection = D2LocationLink.D2Direction.DOWN;
        moveLocation(eDirection);
    }//GEN-LAST:event_butDownActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butDown;
    private javax.swing.JButton butEast;
    private javax.swing.JButton butNorth;
    private javax.swing.JButton butSouth;
    private javax.swing.JButton butUp;
    private javax.swing.JButton butWest;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panelLocation;
    private javax.swing.JTabbedPane tabLevels;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables
}
