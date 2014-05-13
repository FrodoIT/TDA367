/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import java.util.ArrayList;
import java.util.List;
import scratch.model.AbstractCharacter;
import scratch.model.Game;
import scratch.model.Player;

/**
 *
 * @author Ivar
 */
public class PacketGame {
    private List<PacketCharacter> characters;
    
    
    //Kryonet requires an empty constructor
    public PacketGame (){
        
    }
    
    public PacketGame (Game game){
        characters = new ArrayList<>();
        for (AbstractCharacter character: game.getCharacters()){
            characters.add(new PacketCharacter(character));
        }
    }
    
}
