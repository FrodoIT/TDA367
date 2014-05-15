package scratch.controller;

import org.newdawn.slick.GameContainer;
import scratch.model.NpcType;
import scratch.view.CharacterView;

/**
 * Created by pippin on 5/10/14.
 */
public class NpcController {
    private final NpcType npc;
    private final CharacterView view;


    public NpcController(NpcType npc, CharacterView view) {
        this.npc = npc;
        this.view = view;
    }

    public void updateNpc(){
        npc.update();
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
