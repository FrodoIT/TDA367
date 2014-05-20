package scratch.model;

import scratch.network.NetworkServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by cannonbait on 2014-05-20.
 */
public class DoorController implements PropertyChangeListener{

    private final NetworkServer server;
    public DoorController (NetworkServer server){
        this.server = server;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("We should be sending shit, DoorController");
    }
}
