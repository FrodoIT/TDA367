/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import java.util.List;
import scratch.model.Game;

/**
 *
 * @author Ivar
 */
public class PacketGame {
    private List<PacketPlayer> npcs;
    private Game game;
    
    
    //Kryonet requires an empty constructor
    public PacketGame (){
        game = new Game();
    }
    
    public PacketGame (Game game){
        this.game = game;
    }
    
    public Game getModel(){
        return game;
    }
}
