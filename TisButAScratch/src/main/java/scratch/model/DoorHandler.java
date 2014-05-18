package scratch.model;

import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * handels all interactive objects with property:
 * objectType = door
 */
public class DoorHandler {

	private final Map<Room, Set<IInteractiveObject>> roomDoorsMap = new HashMap<>();
	private final Map<String, Set<IInteractiveObject>> doorMatchingMap = new HashMap<>();
	private final Random random = new Random();

	public void interactHappened(Room room, GameCharacter character, IInteractiveObject originDoor) {
        final Set<IInteractiveObject> connectedDoors = doorMatchingMap.get( originDoor.getProperties().get("connection") );
        final IInteractiveObject exitDoor = getOutDoor(connectedDoors, originDoor);

		for (final Map.Entry<Room, Set<IInteractiveObject>> roomSetEntry : roomDoorsMap.entrySet()) {
			if ( roomSetEntry.getValue().contains(exitDoor) ) {

				performTeleport(room, roomSetEntry.getKey(), exitDoor, character);

				return;
			}
		}
	}

	private void performTeleport(Room originRoom, Room targetRoom, IInteractiveObject exitDoor, GameCharacter character) {
		originRoom.removeCharacter(character);
		targetRoom.addCharacter(character);
        final Rectangle2D.Double doorArea = exitDoor.getUnitTile();
        final int x = (int) (doorArea.getX() - doorArea.getWidth()/2);
        final int y = (int) (doorArea.getY() - doorArea.getHeight()/2);
		character.setPosition(new Vector2D(x, y));
	}

	private IInteractiveObject getOutDoor(Set<IInteractiveObject> connectedDoors, IInteractiveObject originDoor) {
        final List<IInteractiveObject> connDoorsExcludingOrigin = new ArrayList<>(connectedDoors);
		connDoorsExcludingOrigin.remove(originDoor);
        if (connDoorsExcludingOrigin.isEmpty()) {
            return originDoor; //if door has no connection. then go out the same door you tried to go in
        }

        final int randomIndex = random.nextInt(connDoorsExcludingOrigin.size());
		return connDoorsExcludingOrigin.get(randomIndex);
	}

	public void addDoor(Room room, IInteractiveObject interactiveObject) {
		Set<IInteractiveObject> objectList = roomDoorsMap.get(room);

		if (objectList == null) {
			objectList = new HashSet<>();
			objectList.add(interactiveObject);
			roomDoorsMap.put(room, objectList);
		} else {
			objectList.add(interactiveObject);
		}


        final String connection = (String)interactiveObject.getProperties().get("connection");
		Set<IInteractiveObject> connectedDoors = doorMatchingMap.get(connection);

		if (connectedDoors == null) {
			connectedDoors = new HashSet<>();
			connectedDoors.add(interactiveObject);
			doorMatchingMap.put(connection, connectedDoors);
		} else {
			connectedDoors.add(interactiveObject);
		}
	}

	public void addDoors(Room room, List<IInteractiveObject> interactiveObjects) {
		for (final IInteractiveObject interactiveObject : interactiveObjects) {
			addDoor(room, interactiveObject);
		}
	}
}
