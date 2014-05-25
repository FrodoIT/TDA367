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
public class PacketNewConnection {
    private int id;
    private int roomId;
    
    public PacketNewConnection(){
        //used for serialization
    }
    
    public PacketNewConnection(int id, int roomId){
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
