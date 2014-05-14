/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import scratch.model.Game;
import scratch.model.MoveDirection;
import scratch.model.Vector2D;

/**
 *
 * @author Cannonbait
 */
public class ListenerClient extends Listener{
    
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof PacketMessage) {
            System.out.println(((PacketMessage)object).getText());
        } else if (object instanceof PacketGame){
            System.out.println("Recieved model");
        } else if (object instanceof PacketCharacter){
            PacketCharacter character = ((PacketCharacter)object);
        }
    }
}
