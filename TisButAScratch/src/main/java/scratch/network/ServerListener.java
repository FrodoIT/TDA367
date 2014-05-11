/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import scratch.model.MoveDirection;

/**
 *
 * @author Cannonbait
 */
public class ServerListener extends Listener {
    private Connection connection;
    
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof ScratchHandshake) {
            this.connection = connection;
            ScratchHandshake recievedHandshake = (ScratchHandshake) object;
            System.out.println(recievedHandshake.text);

            ScratchHandshake response = new ScratchHandshake();
            response.text = "Server connection made";
            connection.sendTCP(response);
        } else if (object instanceof MoveDirection) {

        }
    }
}
