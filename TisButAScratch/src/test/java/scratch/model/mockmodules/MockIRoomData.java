package scratch.model.mockmodules;

import org.newdawn.slick.SlickException;
import scratch.construction.SlickMap;
import scratch.construction.TiledMapPlus;
import scratch.model.IMap;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Player;

import java.util.ArrayList;
import java.util.List;
import scratch.model.AbstractCharacter;
import scratch.model.Vector2D;

public class MockIRoomData implements IRoomData {

    private final List<AbstractCharacter> characters = new ArrayList<>();
    private IMap map;

    public void setUp() {
        try {
            map = new SlickMap(new TiledMapPlus("res/untitled.tmx"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IMap getMap() {
        if (map == null) {
            setUp();
        }
        return map;
    }

    @Override
    public List<AbstractCharacter> getCharacters() {
        return characters;
    }

    @Override
    public Vector2D getClosestPlayerPosition(Vector2D position) {
        for (AbstractCharacter character : characters) {
            if (character instanceof Player) {
                return character.getPosition();
            }
        }
        return position;
    }
}
