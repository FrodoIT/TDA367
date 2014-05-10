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
public class ScratchClient implements IScratchNetwork{

    private final Client client;

    public ScratchClient(String ip) throws SlickException{
        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(ScratchHandshake.class);
        kryo.register(MoveDirection.class);
        
        client.start();
        try {
            client.connect(5000, ip, 54555, 54777);
        } catch (IOException e) {
            throw new SlickException("Unable to link with server");
        }
        
        ScratchHandshake request = new ScratchHandshake();
        request.text = "Client connection made";
        client.sendTCP(request);

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof ScratchHandshake) {
                    System.out.println("Server connection made");
                }
            }
        });
    }
    
    public void update(){
        
    }
}
