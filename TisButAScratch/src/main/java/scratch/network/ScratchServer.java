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
public class ScratchServer implements ScratchNetwork{

    private Server server;
    private PlayerController controller;

    public ScratchServer() throws SlickException{
        server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        kryo.register(MoveDirection.class);

        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof SomeRequest) {
                    SomeRequest request = (SomeRequest) object;
                    System.out.println(request.text);

                    SomeResponse response = new SomeResponse();
                    response.text = "Thanks";
                    connection.sendTCP(response);
                }
                else if (object instanceof MoveDirection){
                    
                    System.out.println("Setting movedirection: " + (MoveDirection)object);
                    controller.setMoveDirection((MoveDirection)object);
                }
            }
        });

    }
    
    public void setPlayerController(PlayerController controller){
        this.controller = controller;
    }
    
    
    public void update(){
        
    }
}
