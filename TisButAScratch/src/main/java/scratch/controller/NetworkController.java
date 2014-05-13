/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.controller;

import scratch.model.Game;
import scratch.network.NetworkClient;
import scratch.network.IScratchNetwork;
import scratch.network.NetworkServer;

/**
 *
 * @author Cannonbait
 */
public class NetworkController {

    private boolean host;
    private IScratchNetwork scratchNetwork;

    public NetworkController(Game game, String ip) {

        if (ip != null) {
            scratchNetwork = new NetworkClient(game, ip);
            host = false;

        } else {
            scratchNetwork = new NetworkServer(game);
            host = true;
        }
    }

    public void start() {
        scratchNetwork.start();
    }
    
    public void update() {
        scratchNetwork.update();
    }
    
    public boolean isHost(){
        return host;
    }
}
