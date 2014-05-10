/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.controller;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import org.newdawn.slick.SlickException;
import scratch.model.MoveDirection;
import scratch.network.ScratchClient;
import scratch.network.IScratchNetwork;
import scratch.network.ScratchHandshake;
import scratch.network.ScratchServer;

/**
 *
 * @author Cannonbait
 */
public class NetworkController {
    private Server server;
    private boolean host;
    private IScratchNetwork scratchNetwork;

    public NetworkController(String ip) {
        if (ip == null) {
            try {
                scratchNetwork = new ScratchClient(ip);
                host = false;
            } catch (SlickException e) {
                scratchNetwork = new ScratchServer();
                host = true;
                System.out.println("Unable to set up client, starting server instead");
            }
        } else {
            scratchNetwork = new ScratchServer();
            host = true;
        }
    }
}
