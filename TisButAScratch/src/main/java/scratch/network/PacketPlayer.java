/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import scratch.model.Player;
import scratch.model.Vector2D;

/**
 *
 * @author Cannonbait
 */
public class PacketPlayer {
    private Vector2D position;
    
    public PacketPlayer(){
        
    }
    
    public PacketPlayer(Player player){
        position = player.getPosition();
    }
}
