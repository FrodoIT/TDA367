/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import scratch.model.MoveDirection;

/**
 *
 * @author Cannonbait
 */
public class ScratchServer implements IScratchNetwork{

    private Server server;
    

    public ScratchServer() {
        server = new Server();
        Kryo kryo = server.getKryo();
        Utilities.kryoRegister(kryo);

        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.addListener(new ServerListener());
    }
    
    public void update(){
        server.sendToAllTCP("update");
    }
}
