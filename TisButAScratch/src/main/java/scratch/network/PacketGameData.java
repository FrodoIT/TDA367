/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import scratch.controller.RoomController;
import scratch.model.GameCharacter;

/**
 *  This class is for serialization of gameData
 * 
 */
public class PacketGameData{
    private final Map<Integer, List<GameCharacter>> rooms = new HashMap<>();    
    
    public PacketGameData(){
        
    }
    
    public PacketGameData(Collection<RoomController> roomControllers){
        for (final RoomController roomController : roomControllers){
            rooms.put(roomController.getId(), roomController.getRoom().getCharacters());
        }
    }
    
    public Map<Integer, List<GameCharacter>> getRooms(){
        return rooms;
    }
}
