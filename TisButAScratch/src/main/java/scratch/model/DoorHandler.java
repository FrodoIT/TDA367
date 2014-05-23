package scratch.model;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * handels all interactive objects with property:
 * objectType = "door"
 */
public class DoorHandler {

	private final Map<DoorHelper, Set<InteractiveObject>> roomDoorsMap = new HashMap<>();
	private final Map<String, Set<InteractiveObject>> doorMatchingMap = new HashMap<>();
	private final Random random = new Random(System.nanoTime());
    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    public void addListener(PropertyChangeListener listener){
        listeners.addPropertyChangeListener(listener);
    }

	public void interactHappened(DoorHelper room, GameCharacter character, InteractiveObject originDoor) {
        final Set<InteractiveObject> connectedDoors = doorMatchingMap.get( originDoor.getProperties().get("connection") );
        final InteractiveObject exitDoor = getOutDoor(connectedDoors, originDoor);

		for (final Map.Entry<DoorHelper, Set<InteractiveObject>> roomSetEntry : roomDoorsMap.entrySet()) {
			if ( roomSetEntry.getValue().contains(exitDoor) ) {

				performTeleport(room, roomSetEntry.getKey(), exitDoor, character);
                listeners.firePropertyChange(new PropertyChangeEvent(character.getId(), "DoorUse", room.getId(), roomSetEntry.getKey().getId()));
				return;
			}
		}
	}

	private void performTeleport(DoorHelper originRoom, DoorHelper targetRoom, InteractiveObject exitDoor, GameCharacter character) {
		originRoom.removeCharacter(character);
		targetRoom.addCharacter(character);
        final Rectangle2D.Double doorArea = exitDoor.getUnitTile();
        final int x = (int) (doorArea.getX() - doorArea.getWidth()/2);
        final int y = (int) (doorArea.getY() - doorArea.getHeight()/2);
		character.setPosition(new Vector2D(x, y));
	}

	private InteractiveObject getOutDoor(Set<InteractiveObject> connectedDoors, InteractiveObject originDoor) {
        final List<InteractiveObject> connDoorsExcludingOrigin = new ArrayList<>(connectedDoors);
		connDoorsExcludingOrigin.remove(originDoor);
        if (connDoorsExcludingOrigin.isEmpty()) {
            return originDoor; //if door has no connection. then go out the same door you tried to go in
        }

        final int randomIndex = random.nextInt(connDoorsExcludingOrigin.size());
		return connDoorsExcludingOrigin.get(randomIndex);
	}

	public void addDoor(DoorHelper room, InteractiveObject interactiveObject) {
		Set<InteractiveObject> objectList = roomDoorsMap.get(room);

		if (objectList == null) {
			objectList = new HashSet<>();
			objectList.add(interactiveObject);
			roomDoorsMap.put(room, objectList);
		} else {
			objectList.add(interactiveObject);
		}


        final String connection = (String)interactiveObject.getProperties().get("connection");
		Set<InteractiveObject> connectedDoors = doorMatchingMap.get(connection);

		if (connectedDoors == null) {
			connectedDoors = new HashSet<>();
			connectedDoors.add(interactiveObject);
			doorMatchingMap.put(connection, connectedDoors);
		} else {
			connectedDoors.add(interactiveObject);
		}
	}

	public void addDoors(DoorHelper room, List<InteractiveObject> interactiveObjects) {
		for (final InteractiveObject interactiveObject : interactiveObjects) {
			addDoor(room, interactiveObject);
		}
	}
}
