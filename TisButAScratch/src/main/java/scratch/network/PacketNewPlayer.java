/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

/**
 *
 * @author Ivar
 */
public class PacketNewPlayer {
    private int id;
    
    public PacketNewPlayer(){
        
    }
    
    public PacketNewPlayer(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
}
