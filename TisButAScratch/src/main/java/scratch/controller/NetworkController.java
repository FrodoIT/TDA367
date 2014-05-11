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
    private IScratchNetwork scratchNetwork;

    public NetworkController(String ip) {

        if (ip != null) {
            scratchNetwork = new NetworkClient(ip);

        } else {
            scratchNetwork = new NetworkServer();
        }
    }

    public void start() {
        scratchNetwork.start();
    }
    
    public void update() {
        scratchNetwork.update();
    }
}
