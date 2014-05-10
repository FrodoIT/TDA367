/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.controller;

import scratch.network.ScratchClient;
import scratch.network.ScratchNetwork;
import scratch.network.ScratchServer;

/**
 *
 * @author Cannonbait
 */
public class NetworkController {
    //private final ScratchNetwork scratchNetwork;
    
    public NetworkController (String ip){
        if (ip == null){
            //scratchNetwork = new ScratchClient(ip);
        } else {
            //scratchNetwork= new ScratchServer();
        }
    }
}
