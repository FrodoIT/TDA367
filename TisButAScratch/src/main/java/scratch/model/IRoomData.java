package scratch.model;

import java.util.List;

/**
 * Created by tejp on 2014-04-11.
 */
public interface IRoomData {
	List<Player> getPlayers();
	List<INpc> getNpcs();
	IMap getMap();

}
