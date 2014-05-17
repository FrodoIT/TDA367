package scratch.model;

import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * Created by tejp on 2014-04-11.
 */
@ImplementedBy(Room.class)
public interface IRoomData {
	List<GameCharacter> getCharacters();
        Vector2D getClosestPlayerPosition(Vector2D position);
	IMap getMap();

}
