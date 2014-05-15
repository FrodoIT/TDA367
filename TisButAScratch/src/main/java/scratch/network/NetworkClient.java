/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

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

    public void start(Listener listener) {
        client.start();

        try {
            client.connect(5000, ip, 54555, 54777);
        } catch (IOException e) {
            throw new RuntimeException("Could not connect to IP");
        }
        client.addListener(listener);
    }

    public void update() {

    }
}
