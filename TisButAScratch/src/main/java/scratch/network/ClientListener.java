/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 *
 * @author Cannonbait
 */
public class ClientListener extends Listener{

    public void received(Connection connection, Object object) {
        if (object instanceof ScratchHandshake) {
            System.out.println("Server connection made");
        } else if {object instanceof String){
            System.out.println((String)object);
        }
    }
}
