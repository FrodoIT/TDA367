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
public class NetworkServer implements IScratchNetwork {

    private Server server;

    public NetworkServer() {
        server = new Server();
        Utilities.kryoRegister(server.getKryo());

    }

    public void start() {
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            throw new RuntimeException("Could not bind to ports");
        }
        server.addListener(new ListenerServer());
    }

    public void update() {
        server.sendToAllTCP(new PacketMessage("update"));
    }
}
