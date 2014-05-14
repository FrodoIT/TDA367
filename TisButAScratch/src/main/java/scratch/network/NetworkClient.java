/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import scratch.model.Game;

/**
 *
 * @author Cannonbait
 */
public class NetworkClient {
    private final String ip;
    private final Client client;
    private final Game game;

    public NetworkClient(Game game, String ip){
        this.game = game;
        this.ip = ip;
        client = new Client();
        Utilities.kryoRegister(client.getKryo());

    }

    public void start() {
        client.start();

        try {
            client.connect(5000, ip, 54555, 54777);
        } catch (IOException e) {
            throw new RuntimeException("Could not connect to IP");
        }
        client.addListener(new ListenerClient(game));
        PacketMessage message = new PacketMessage("Client message");
        client.sendTCP(message);
    }

    public void update() {

    }
}
