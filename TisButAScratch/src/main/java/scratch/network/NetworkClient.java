/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import org.newdawn.slick.SlickException;
import scratch.model.MoveDirection;

/**
 *
 * @author Cannonbait
 */
public class NetworkClient implements IScratchNetwork{

    private final Client client;

    public NetworkClient(String ip) throws SlickException{
        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(NetworkStringMessage.class);
        client.start();
        
        try {
            client.connect(5000, ip, 54555, 54777);
        } catch (IOException e) {
            throw new SlickException("Unable to link with server");
        }
        client.addListener(new ListenerClient());
        NetworkStringMessage message =  new NetworkStringMessage();
        message.text = "Setting up connection from client";
        client.sendTCP(message);
    }
    
    public void update(){
        
    }
}
