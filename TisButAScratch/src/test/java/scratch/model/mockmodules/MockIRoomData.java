package scratch.model.mockmodules;

import org.newdawn.slick.SlickException;
import scratch.construction.TiledMapPlus;
import scratch.model.*;

import java.util.ArrayList;
import java.util.List;
import scratch.model.GameCharacter;

public class MockIRoomData implements IRoomData {

    private final List<GameCharacter> characters = new ArrayList<>();
    private IMap map;

    public void setUp() {
        try {
            map = new TiledMapPlus("res/untitled.tmx");
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
    public List<GameCharacter> getCharacters() {
        return characters;
    }

    @Override
    public Vector2D getClosestPlayerPosition(Vector2D position) {
        for (GameCharacter character : characters) {
            if (character instanceof GameCharacter) {
                return character.getPosition();
            }
        }
        return position;
    }
}
