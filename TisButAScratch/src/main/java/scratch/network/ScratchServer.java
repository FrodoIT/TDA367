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
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import scratch.controller.Controller;
import scratch.controller.PlayerController;
import scratch.model.Model;
import scratch.model.MoveDirection;
import static scratch.network.Registration.register;
import scratch.view.View;

/**
 *
 * @author Cannonbait
 */
public class ScratchServer implements IScratchNetwork{

    private Server server;

    public ScratchServer() {
        server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(ScratchHandshake.class);
        kryo.register(MoveDirection.class);

        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof ScratchHandshake) {
                    ScratchHandshake recievedHandshake = (ScratchHandshake) object;
                    System.out.println(recievedHandshake.text);

                    ScratchHandshake response = new ScratchHandshake();
                    response.text = "Server connection made";
                    connection.sendTCP(response);
                }
                else if (object instanceof MoveDirection){
                    
                }
            }
        });

    }
    
    public void update(){
        
    }
}
