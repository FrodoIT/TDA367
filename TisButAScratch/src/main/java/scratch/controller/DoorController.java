package scratch.controller;

import scratch.model.GameCharacter;
import scratch.network.NetworkServer;
import scratch.network.PacketAddCharacter;
import scratch.network.PacketMoveCharacter;
import scratch.network.PacketRemoveCharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by cannonbait on 2014-05-20.
 */
public class DoorController implements PropertyChangeListener{

    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
    public DoorController (PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        listeners.firePropertyChange(null, null, new PacketMoveCharacter((int)evt.getSource(), (int)evt.getOldValue(), (int)evt.getNewValue()));
    }
}
