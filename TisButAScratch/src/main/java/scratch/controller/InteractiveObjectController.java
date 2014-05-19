package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import scratch.model.IInteractiveObject;
import scratch.model.InteractiveObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InteractiveObjectController extends Listener {
    private IInteractiveObject interactiveObject;
    private final PropertyChangeSupport listeners;
    private final InteractiveObjectView view;

    public InteractiveObjectController(final IInteractiveObject interactiveObject) {
        this.interactiveObject =  interactiveObject;
        listeners = new PropertyChangeSupport(this);
        view = new InteractiveObjectView(interactiveObject);
    }

    public void addListener(final PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void update() {
        listeners.firePropertyChange(null, null, interactiveObject);
    }

    public void render(GameContainer gameContainer) {
        view.render(gameContainer);
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof InteractiveObject) {
            InteractiveObject recievedObject = (InteractiveObject) object;
            if (recievedObject.getProperties().getProperty("id").compareTo(interactiveObject.getProperties().getProperty("id")) == 0) {
                setInteractiveObject(recievedObject);
            }
        }
    }

    public IInteractiveObject getInteractiveObject() {
        return interactiveObject;
    }

    public InteractiveObjectView getView() {
        return view;
    }

    public void setInteractiveObject(InteractiveObject interactiveObject) {
        this.interactiveObject = interactiveObject;
    }
}
