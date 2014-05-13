package scratch.controller;

import scratch.model.Game;
import scratch.model.NpcType;
import scratch.view.NpcView;

/**
 * Created by pippin on 5/10/14.
 */
public class NpcController {
    private final NpcType npc;
    private final NpcView npcView;

    public NpcController(NpcType npc, NpcView npcView){
        this.npc = npc;
        this.npcView = npcView;
    }

    public void updateNpc(){
        npc.update();
    }

    public NpcView getNpcView() {
        return npcView;
    }
}
