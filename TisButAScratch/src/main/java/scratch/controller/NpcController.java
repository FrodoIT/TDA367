package scratch.controller;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.Game;
import scratch.model.NpcType;
import scratch.view.NpcView;
import scratch.view.SpriteDirectionRenderer;

/**
 * Created by pippin on 5/10/14.
 */
public class NpcController {
    private final NpcType npc;
    private final NpcView npcView;
    private SpriteDirectionRenderer spriteHandler;


    public NpcController(NpcType npc, NpcView npcView) {
        this.npc = npc;
        this.npcView = npcView;


    }

    public void updateNpc(){
        npc.update();
    }

    public NpcView getNpcView() {
        return npcView;
    }

    public NpcType getNpc() {
        return npc;
    }
}
