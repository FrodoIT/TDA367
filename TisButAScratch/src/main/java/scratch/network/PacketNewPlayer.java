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
    private int roomId;
    
    public PacketNewPlayer(){
        
    }
    
    public PacketNewPlayer(int id, int roomId){
        this.id = id;
        this.roomId = roomId;
    }
    
    public int getId(){
        return id;
    }
    
    public int getRoomId() {
        return roomId;
    }
}
