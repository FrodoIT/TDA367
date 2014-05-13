/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import scratch.model.Game;

/**
 *
 * @author Ivar
 */
public class PacketModel {
    private Game game;
    
    
    //Kryonet requires an empty constructor
    public PacketModel (){
        game = new Game();
    }
    
    public PacketModel (Game game){
        this.game = game;
    }
    
    public Game getModel(){
        return game;
    }
}
