package scratch.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import scratch.network.NetworkServer;
import scratch.network.PacketMoveCharacter;


/**
 * Created by cannonbait on 2014-05-20.
 */
public class DoorController implements PropertyChangeListener{
    
    private final NetworkServer server;
    private final Map<Integer, RoomController> rooms;
    
    
    public DoorController (NetworkServer server, Map<Integer, RoomController> rooms) {
        this.server = server;
        this.rooms = rooms;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        rooms.get((int)evt.getOldValue()).moveCharacter((int)evt.getSource(), rooms.get((int)evt.getNewValue()));
        server.sendTCP(new PacketMoveCharacter((int)evt.getSource(), (int)evt.getOldValue(), (int)evt.getNewValue()));
    }
}
