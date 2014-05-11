/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import scratch.model.Model;
import scratch.model.Room;

/**
 *
 * @author Cannonbait
 */
public class Utilities {
    public static void kryoRegister(Kryo kryo){
        kryo.register(PacketMessage.class);
        //Looks ugly as shit, should be different solution
        kryo.register(PacketModel.class);
        kryo.register(Model.class);
        kryo.register(Room.class);
    }
}
