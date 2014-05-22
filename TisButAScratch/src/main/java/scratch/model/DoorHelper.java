package scratch.model;

import scratch.model.GameCharacter;

/**
 * Created by tejp on 2014-05-22.
 */
public interface DoorHelper {
    public boolean addCharacter(GameCharacter character);
    public boolean removeCharacter(GameCharacter character);
    public int getId();
}
