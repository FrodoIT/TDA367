package scratch.view;

import org.newdawn.slick.GameContainer;
import scratch.construction.TiledMapPlus;
import scratch.model.Room;

/**
 *
 * @author André Samuelsson
 *
 */

public final class RoomView {
    private final TiledMapPlus map;
    private final Room room;

    public RoomView(TiledMapPlus map, Room room) {
        this.map = map;
        this.room = room;

    }


    public void render(GameContainer gameContainer) {
        map.render(0, 0);
    }
}
