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
import static scratch.network.Registration.register;

/**
 *
 * @author Cannonbait
 */
public class ScratchServer {
    private Server kryoServer;
    
    public ScratchServer(){
        
        kryoServer = new Server();
        register(kryoServer.getKryo());
        
        kryoServer.start();
        try {
            kryoServer.bind(54555, 54777);
        } catch (IOException e){
            e.printStackTrace();
        }
        
        kryoServer.addListener(new Listener(){
            public void recieved (Connection connection, Object object){
                System.out.println("Recieved");
            }
        
        });
    }
}
