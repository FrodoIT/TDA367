package scratch.model;

import com.google.inject.ImplementedBy;

import java.util.List;
import java.util.Map;

/**
 * Created by tejp on 2014-04-11.
 */
@ImplementedBy(Room.class)
public interface IRoomData {
	List<Player> getPlayers();
	Map<Integer, NpcType> getNpcs();
	IMap getMap();

}
