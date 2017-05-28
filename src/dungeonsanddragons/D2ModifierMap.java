/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonsanddragons;

import java.util.EnumMap;
import java.util.LinkedList;

/**
 *
 * @author JaneW
 */
public class D2ModifierMap extends Object {
    
    // This is a map of an ability to list of modifiers for that ability
    private EnumMap<D2CharacterStats.D2Abilities,LinkedList<D2Modifier>> mapModifiers; 
    
    public D2ModifierMap() {
        mapModifiers = new EnumMap<D2CharacterStats.D2Abilities,LinkedList<D2Modifier>>(D2CharacterStats.D2Abilities.class);
    }
    
    public D2ModifierMap(D2ModifierMap mapNew) {
        mapModifiers = new EnumMap<D2CharacterStats.D2Abilities,LinkedList<D2Modifier>>(D2CharacterStats.D2Abilities.class);
        this.addModifiers(mapNew);
    }
    
    // Add a modifier to the correct ability list
    public void addModifier(D2Modifier modNewModifier) {
        
        // Get the list of modifiers for the specific ability from the EnumMap
        LinkedList <D2Modifier> list = mapModifiers.get(modNewModifier.getAbility());
        
        // If you can't find one then create a new one and add is to the map
        if(list==null) {
            list = new LinkedList<D2Modifier>();
            mapModifiers.put(modNewModifier.getAbility(),list);
        }
        
        // Add a copy of the modifer to the list
        list.add(new D2Modifier(modNewModifier));    
        
    }
    
    public void removeModifier(D2Modifier modNewModifier) {
        
         
        // Get the list of modifiers for the specific ability from the EnumMap
        LinkedList <D2Modifier> list = mapModifiers.get(modNewModifier.getAbility());       
        // If there is a list
        if(list !=null) {
            // Look for where the modifier in the list
             int iLocation = list.indexOf(modNewModifier);
             
             // If it was found then remove it.
             if(iLocation != -1) {
                 list.remove(iLocation);
             }
         }
        
    }
    
    // Add all of the modifiers from another map...
    public void addModifiers(D2ModifierMap mapNewModifiers) {
        
        // For each type of ability in the map...
        for(D2CharacterStats.D2Abilities enumAbility : D2CharacterStats.D2Abilities.values()) {
            
            // Get the list of modifies from this object...
            LinkedList<D2Modifier> listDestination = this.mapModifiers.get(enumAbility);
            
            // If there is no list for this ability then create one and add it to the map...
            if(listDestination == null) {
                listDestination = new LinkedList<D2Modifier>();
                this.mapModifiers.put(enumAbility, listDestination);
            }
            
            // Get the list of modifies from the source  object...
            LinkedList<D2Modifier> listSource = mapNewModifiers.getModifiers(enumAbility);
            
            // If there is a list of modifiers to copy over from the source list...
            if(listSource != null) {
                
                // Go thrpough each modifier in the source list...
                for(int i=0;i<listSource.size();i++) {
                    // Get the next modifier...
                    D2Modifier modNew = listSource.get(i);
                    
                    // See is the modifier is already in the target list...
                    if(listDestination.indexOf(modNew) == -1) {
                        // If not then add a copy of it to the destination list
                        listDestination.add(new D2Modifier(modNew));
                    }
                }
            }
        }
    }
        
    public LinkedList<D2Modifier> getModifiers(D2CharacterStats.D2Abilities enumAbility) {
        return this.mapModifiers.get(enumAbility);

    }
    
    
    @Override public String toString() {
        String sDescription = "Total Modifiers=";
        for(D2CharacterStats.D2Abilities e : D2CharacterStats.D2Abilities.values()) {
            int iAbilityModifier = this.getTotalModifiers(e);
            sDescription += e.toString() + "=" + Integer.toString(iAbilityModifier) + ", ";
        }
        
        return sDescription;
    }
    
    // Get the total of the modifiers for a specified ability
    public int getTotalModifiers(D2CharacterStats.D2Abilities enumAbility) {
        int iTotalModifer = 0;
        // Has this character got a map of modifiers?
        if(mapModifiers!=null) {
            // Has the map got a list for the specified Ability...
            LinkedList<D2Modifier> list = mapModifiers.get(enumAbility);
            // Does the map contain any modifiers for this Ability?
            if(list != null) {
                // Loop through the list and total up the modifiers
                for(int i=0;i<list.size();i++) {
                    D2Modifier mod = list.get(i);
                    iTotalModifer += mod.getModifier();
                    System.out.println(mod.getName() + "(" + enumAbility + mod.getModifier() + ")");
                    
                }
            }
        }
        return iTotalModifer;
    }
    
}
