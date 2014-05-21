package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.newdawn.slick.GameContainer;
import scratch.model.IInteractiveObject;
import scratch.model.InteractiveObject;
import scratch.network.NetworkServer;

public class InteractiveObjectController extends Listener {
    private IInteractiveObject interactiveObject;
    private final InteractiveObjectView view;
    private NetworkServer server;

    public InteractiveObjectController(final IInteractiveObject interactiveObject) {
        this.interactiveObject =  interactiveObject;
        view = new InteractiveObjectView(interactiveObject);
    }
    
    public void setServer (NetworkServer server){
        this.server = server;
    }

    public void update() {
	    server.sendTCP(interactiveObject);
    }

    public void render(GameContainer gameContainer) {
        view.render(gameContainer);
    }

    @Override
    public void received(Connection connection, Object object) {
	    if (object instanceof InteractiveObject) {
            InteractiveObject recievedObject = (InteractiveObject) object;
            if (recievedObject.getProperties().get("id").equals(interactiveObject.getProperties().get("id"))) {
                interactiveObject.setObject(recievedObject);
            }
        }
    }

    public IInteractiveObject getInteractiveObject() {
        return interactiveObject;
    }

    public InteractiveObjectView getView() {
        return view;
    }


}
