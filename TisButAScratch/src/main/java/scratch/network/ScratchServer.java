/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import com.esotericsoftware.kryonet.Server;
import java.io.IOException;

/**
 *
 * @author Cannonbait
 */
public class ScratchServer {
    private Server kryoServer;
    
    public ScratchServer(){
        kryoServer = new Server();
        kryoServer.start();
        try {
            kryoServer.bind(54555, 54777);
        } catch (IOException e){
            e.printStackTrace();
        }   
    }
}
