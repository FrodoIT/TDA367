package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import scratch.model.InteractiveObject;
import scratch.network.NetworkClient;
import scratch.network.NetworkServer;
import scratch.view.InteractiveObjectView;

public class InteractiveObjectController extends Listener {
    final private InteractiveObject interactiveObject;
    private final InteractiveObjectView view;
    private NetworkServer server;

    public InteractiveObjectController(final InteractiveObject interactiveObject) {
        super();
        this.interactiveObject =  interactiveObject;
        view = new InteractiveObjectView(interactiveObject);
    }
    
    public void setServer (NetworkServer server){
        this.server = server;
    }
    
    public void setClient (NetworkClient client){
        client.addListener(this);
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
            final InteractiveObject recievedObject = (InteractiveObject) object;
            if (recievedObject.getProperties().get("id").equals(interactiveObject.getProperties().get("id"))) {
                interactiveObject.setObject(recievedObject);
            }
        }
    }

    public InteractiveObject getInteractiveObject() {
        return interactiveObject;
    }

    public InteractiveObjectView getView() {
        return view;
    }


}
