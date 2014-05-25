/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cannonbait
 */
public class NetworkClient {
    private final String ip;
    private final Client client;

    public NetworkClient(String ip){
        this.ip = ip;
        client = new Client();
        Utilities.kryoRegister(client.getKryo());

    }

    public void start(Listener listener){
        client.start();

        try {
            client.connect(5000, ip, 54555, 54777);
        } catch (IOException e) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, "Could not connect to server, expect disaster", e);
        }
        addListener(listener);
    }
    
    public void send(Object object){
        client.sendTCP(object);
    }
    
    public void addListener(Listener listener){
        client.addListener(listener);
    }
}
