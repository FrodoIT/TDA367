/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import com.esotericsoftware.kryo.Kryo;

/**
 *
 * @author Cannonbait
 */
public class Registration {
    public static void register(Kryo kryo){
        kryo.register(ScratchHandshake.class);
    }
    
    
}
