/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.util.ArrayList;
/**
 *
 * @author JaneW
 */
public class CardGame {
    private int iPlayers;
    private ArrayList<Player> arlPlayers;
    private int iCurrentPlayer;
    private int iNumberOfCards;
    private int iNumberOfTurns;
    
    public CardGame() {
        arlPlayers = new ArrayList<Player>();
        iCurrentPlayer=0;
        iNumberOfCards=0;
        iNumberOfTurns=0;
    }
    
    public void addPlayer(Player pNewPlayer) {
        arlPlayers.add(pNewPlayer);
        if(iCurrentPlayer==0) {
            iCurrentPlayer=1;
        }
    }
    
    public void setNumberOfCards(int iNewCards) {
        iNumberOfCards=iNewCards;
    }
    
    public int getNumberOfCards() {
        return iNumberOfCards;
    }
    
    public int getNumberOfTurns() {
        return iNumberOfTurns;
    }
    
    public int getNextTurn() {
        return ++iNumberOfTurns;
    }
            
    public int getCurrentPlayerNumber() {
        return iCurrentPlayer;
    }
    
    public Player getCurrentPlayer() {

        return arlPlayers.get(iCurrentPlayer-1);
    }
    

    public Player getPlayer(int iSelectedPlayer) {

        return arlPlayers.get(iSelectedPlayer-1);
    }
    
    
    public int getNextPlayer() {
        int iNextPlayer = iCurrentPlayer;
        if(iCurrentPlayer==arlPlayers.size()) {
            iCurrentPlayer=1;
        }
        else {
            iCurrentPlayer++;
        }
        return iNextPlayer;
    }
    
    public int getPlayers() {
        return arlPlayers.size()+1;
    }
}
