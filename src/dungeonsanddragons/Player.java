/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

/**
 *
 * @author JaneW
 */
public class Player {
    private String sName;
    public enum PlayerType { HUMAN, COMPUTER, UNKNOWN };
    private PlayerType ePlayerType;
    int iWins;
    
    public Player () {
        sName="UNKNOWN";
        ePlayerType = PlayerType.UNKNOWN;
        iWins = 0;
    }
    
    public Player (String sNewName, Player.PlayerType eNewType) {
        sName=sNewName;
        ePlayerType = eNewType;
        iWins = 0;        
    }
    
    public String getName() {
        return sName;
    }
    
    public Player.PlayerType getType() {
        return ePlayerType;
    }
    
    public int getWins() {
        return iWins;
    }
    
    public int addWin() {
        return ++iWins;
    }
}
