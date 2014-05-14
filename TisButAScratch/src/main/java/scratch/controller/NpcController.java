package scratch.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import scratch.model.Game;
import scratch.model.NpcType;
import scratch.view.NpcView;

/**
 * Created by pippin on 5/10/14.
 */
public class NpcController {
    private final NpcType npc;
    private final NpcView npcView;
    private final PropertyChangeSupport listeners;

    public NpcController(NpcType npc, NpcView npcView){
        this.npc = npc;
        this.npcView = npcView;
        listeners = new PropertyChangeSupport(this);
    }
    
    public void addListener(PropertyChangeListener listener){
        listeners.addPropertyChangeListener(listener);
    }

    public void updateNpc(){
        npc.update();
    }

    public NpcView getNpcView() {
        return npcView;
    }
}
