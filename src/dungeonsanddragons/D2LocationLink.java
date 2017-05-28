/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class D2LocationLink {
    
    // Enum for directions that you can move
    public enum D2Direction {
        NORTH, SOUTH, EAST, WEST, UP, DOWN, UNKNOWN;
        
        @Override public String toString() {
            String sDirection = this.name();             
            return sDirection;
        }
        
        // Return opposite direction
        public D2Direction getOpposite () {
             return getOpposite(this);
        }
        
        // Return opposite direction
        static public D2Direction getOpposite(D2Direction enumDirection) {
            D2Direction eOpposite = UNKNOWN;

            switch (enumDirection) {
                case NORTH:
                    eOpposite = SOUTH;
                    break;
                case SOUTH:
                    eOpposite = NORTH;
                    break;
                case EAST:
                    eOpposite = WEST;
                    break;
                case WEST:
                    eOpposite = EAST;
                    break;
                case UP:
                    eOpposite = DOWN;
                    break;
                case DOWN:
                    eOpposite = UP;
                    break;
            }
            return eOpposite;
        }
        
        // Get an enum from a string - any case match
        static public D2Direction fromString(String sClass) {
            D2Direction enumSelected = UNKNOWN;
            for(D2Direction e : D2Direction.values()) {
                if(e.name().equals(sClass.toUpperCase())) {
                    enumSelected = e;
                }
            }
            return enumSelected;
        }
    }
    
    
    // Direction for this link
    private D2Direction directionTo;
    
    // Which location do you end up at?
    private int iLocationTo;
    
    // Description of the link
    private String sDescription;
    
    // Is the link locked?
    private boolean bLocked;
    
    // Why is it locked?
    private String sLockedReason;
    private String sUnlockedReason;
    
    
    // Constructor
    public D2LocationLink(D2Direction directionNew, int iNewLocation, String sNewDescription) {
        this.directionTo = directionNew;
        this.iLocationTo = iNewLocation;
        this.sDescription = sNewDescription;
        this.bLocked = false;
        this.sLockedReason = "";
        this.sUnlockedReason = "";
    }

    // Constructor if this is a locked link
    public D2LocationLink(D2Direction directionNew, int iNewLocation, String sNewDescription, boolean bNewLocked, String sNewLockedReason, String sNewUnlockedReason) {
        this.directionTo = directionNew;
        this.iLocationTo = iNewLocation;
        this.sDescription = sNewDescription;
        this.bLocked = bNewLocked;
        this.sLockedReason = sNewLockedReason;
        this.sUnlockedReason = sNewUnlockedReason;
    }

    @Override
    public String toString() {
        return "D2LocationLink{" + "directionTo=" + directionTo + ", iLocationTo=" + iLocationTo + ", sDescription=" + sDescription + ", bLocked=" + bLocked + ", sLockedReason=" + sLockedReason + ", sUnlockedReason=" + sUnlockedReason + '}';
    }


    public D2Direction getDirectionTo() {
        return directionTo;
    }

    public int getLocationTo() {
        return iLocationTo;
    }

    public String getDescription() {
        return sDescription;
    }

    public boolean isLocked() {
        return bLocked;
    }

    public String getLockedReason() {
        return sLockedReason;
    }
    
    
    
}
