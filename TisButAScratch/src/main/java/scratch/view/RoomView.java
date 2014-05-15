package scratch.view;

import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author André Samuelsson
 *
 */

public final class RoomView {
    private final TiledMap map;

    public RoomView(TiledMap map) {
        this.map = map;

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
