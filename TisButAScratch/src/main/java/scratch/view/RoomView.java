package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.NpcType;
import scratch.model.Room;

import java.util.Map;

/**
 *
 * @author André Samuelsson
 *
 */

public final class RoomView {
    private final Room room;
    private final TiledMap map;
    private final GameContainer gameContainer;

    public RoomView(GameContainer gameContainer, Room room, TiledMap map) {
        this.room = room;
        this.map = map;
        this.gameContainer = gameContainer;

    }

    public void render() {
        map.render(0, 0);
/*
        for(Map.Entry<Integer, NpcType> npcEntry : room.getNpcs().entrySet()){
            int id = npcEntry.getValue().getId();
            if(npcViews.containsKey(id)){
                npcViews.get(id).render(npcEntry.getValue(), g);
            } else {
                npcViews.get(0).render(npcEntry.getValue(), g);
            }
            */
        }
}
