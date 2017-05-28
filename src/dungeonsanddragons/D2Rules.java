/**
 * 
 */
package dungeonsanddragons;

import java.lang.Math;

/**
 * @author u394198
 *
 */
public class D2Rules {
	
	
	public D2Rules () {
		
	}
	// Convert XP to Level using the magic formula
	static public int XPtoLevel(int iXPCheck) {
		return (int)Math.floor((1+Math.sqrt((double)iXPCheck/125+1))/2);
	}
	
	// Convert a level to the min XP required using the magic formula
	static public int LeveltoXP(int iLevelCheck) {
		return 500 * (iLevelCheck-1) * (iLevelCheck);
	}

	
	// Calculate percentage progress to next level based on current XP
	static public double progressToNextLevel(int iXPCheck) {
		double dProgressPercent=0.0;
		
		// Convert XP to current level
		int iCurrentLevel = XPtoLevel(iXPCheck);
		
		// Calc does not work if no XP
		if(iXPCheck>0) {
			// How far have we progress from the start of the level towards the next level XP band?
			dProgressPercent = (iXPCheck - LeveltoXP(iCurrentLevel))*100/(LeveltoXP(iCurrentLevel+1)-LeveltoXP(iCurrentLevel));
		}
		
		return dProgressPercent;
	}
	
	// Calculate XP required to progress to next level based on current XP
	static public int XPToNextLevel(int iXPCheck) {
		
		// Convert XP to current level
		int iCurrentLevel = XPtoLevel(iXPCheck);
		
		// How far have we progress from the start of the level towards the next level XP band?
		return (LeveltoXP(iCurrentLevel+1)-iXPCheck);

	}
	
	//  Calculate the modifier based on an Ability score
	static int abilityScoreToModifier(int iScore) {
		double dScore = (double) iScore;
		return (int) Math.floor((dScore-10)/2);
	}
}
