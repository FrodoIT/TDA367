/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.controller;

import com.esotericsoftware.kryonet.Server;
import org.newdawn.slick.SlickException;
import scratch.network.NetworkClient;
import scratch.network.IScratchNetwork;
import scratch.network.NetworkServer;

/**
 *
 * @author Cannonbait
 */
public class NetworkController {
    private Server server;
    private boolean host;
    private IScratchNetwork scratchNetwork;

    public NetworkController(String ip) {
        if (ip != null) {
            try {
                
                scratchNetwork = new NetworkClient(ip);
                host = false;
                System.out.println("Started Client");
            } catch (SlickException e) {
                scratchNetwork = new NetworkServer();
                host = true;
                System.out.println("Unable to set up client, starting server instead");
            }
        } else {
            scratchNetwork = new NetworkServer();
            host = true;
            System.out.println("Started Server");
        }
    }
    
    public void update(){
        scratchNetwork.update();
    }
}
