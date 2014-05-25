/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import scratch.model.GameCharacter;

/**
 *
 * @author Cannonbait
 */
public class PacketNewCharacter {
    private int roomId;
    private GameCharacter character;
    
    public PacketNewCharacter(){
        //used for serialization
    }
    
    public PacketNewCharacter(int roomId, GameCharacter character){
        this.roomId = roomId;
        this.character = character;
    }
    
    public int getRoomId(){
        return roomId;
    }
    
    public GameCharacter getCharacter(){
        return character;
    }
}
