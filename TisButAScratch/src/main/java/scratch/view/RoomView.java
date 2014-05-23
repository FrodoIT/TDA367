package scratch.view;

import org.newdawn.slick.GameContainer;
import scratch.construction.TiledMapPlus;

/**
 *
 * @author André Samuelsson
 *
 */

public final class RoomView {
    private final TiledMapPlus map;

    public RoomView(TiledMapPlus map) {
        this.map = map;

    }


    public void render(GameContainer gameContainer) {
        map.render(0, 0);
    }
}
