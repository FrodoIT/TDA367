/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Cannonbait
 */
public class NetworkServer {

    private final Server server;

    public NetworkServer() {
        server = new Server(32768, 8196);
        Utilities.kryoRegister(server.getKryo());

    }

    public void start(Listener listener){
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, "Could not bind to ports, multiplayer will not be available", e);
        }
        server.addListener(listener);
    }

    public void sendTCP (Object object){
            server.sendToAllTCP(object);
    }

    public void addListener(Listener listener){
        server.addListener(listener);
    }
    
    public void closeRequested(){
        server.close();
    }
}
