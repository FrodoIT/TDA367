/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import scratch.model.MoveDirection;

/**
 *
 * @author Cannonbait
 */
public class ListenerServer extends Listener {


    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof NetworkStringMessage) {
            
            NetworkStringMessage recievedHandshake = (NetworkStringMessage) object;
            System.out.println(recievedHandshake.text);

            NetworkStringMessage response = new NetworkStringMessage();
            response.text = "Server response";
            connection.sendTCP(response);
        }
    }
}
