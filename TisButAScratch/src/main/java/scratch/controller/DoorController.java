package scratch.controller;

import scratch.network.NetworkServer;

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
        listeners.firePropertyChange("RemoveCharacter", null, evt.getOldValue());
        listeners.firePropertyChange("AddCharacter", null, evt.getNewValue());
    }
}
