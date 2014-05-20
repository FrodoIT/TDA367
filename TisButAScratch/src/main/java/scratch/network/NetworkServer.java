/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import scratch.model.GameCharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;



/**
 *
 * @author Cannonbait
 */
public class NetworkServer implements PropertyChangeListener{

    private Server server;

    public NetworkServer() {
        server = new Server();
        Utilities.kryoRegister(server.getKryo());

    }

    public void start(Listener listener){
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            System.out.println("Could not bind to ports, multiplayer will not be available");
        }
        server.addListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        if (newValue instanceof GameCharacter){
            server.sendToAllTCP(newValue);
        } else if (evt.getPropertyName().equals("RemoveCharacter")){
        }
    }


    public void addListener(Listener listener){
        server.addListener(listener);
    }
}
