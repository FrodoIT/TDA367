package scratch.controller;

import org.newdawn.slick.GameContainer;
import scratch.model.NpcType;
import scratch.view.CharacterView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by pippin on 5/10/14.
 */
public class NpcController {
    private final NpcType npc;
    private final PropertyChangeSupport listeners;
    private final CharacterView view;


    public NpcController(NpcType npc, CharacterView view) {
        this.npc = npc;
        this.view = view;
        listeners = new PropertyChangeSupport(this);
    }
    
    public void addListener(PropertyChangeListener listener){
        listeners.addPropertyChangeListener(listener);

    }

    public void updateNpc(){
        npc.update();
        listeners.firePropertyChange(null, null, npc);
    }

    public CharacterView getView() {
        return view;
    }
    
    public void render(GameContainer gameContainer){
        view.render(gameContainer);
    }

    public NpcType getNpc() {
        return npc;
    }
}
