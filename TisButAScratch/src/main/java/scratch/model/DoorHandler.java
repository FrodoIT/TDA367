package scratch.model;

import javax.xml.transform.sax.SAXSource;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * handels all interactive objects with a property
 * objectType = door
 */
public class DoorHandler {

	private final Map<Room, Set<IInteractiveObject>> roomDoorsMap = new HashMap<>();
	private final Map<String, Set<IInteractiveObject>> doorMatchingMap = new HashMap<>();
	private final Random random = new Random();

	public void interactHappened(Room room, Player player, IInteractiveObject originDoor) {
		Set<IInteractiveObject> connectedDoors = doorMatchingMap.get( originDoor.getProperties().get("connection") );
		IInteractiveObject exitDoor = getOutDoor(connectedDoors, originDoor);


		for (Map.Entry<Room, Set<IInteractiveObject>> roomSetEntry : roomDoorsMap.entrySet()) {
			if ( roomSetEntry.getValue().contains(exitDoor) ) {

				performTeleport(room, roomSetEntry.getKey(), exitDoor, player);

				return;
			}
		}
	}

	private void performTeleport(Room originRoom, Room targetRoom, IInteractiveObject exitDoor, Player player) {
		originRoom.exitRoom(player);
		targetRoom.enterRoom(player);
		Rectangle2D.Double doorArea = exitDoor.getArea();
		int x = (int) (doorArea.getX() - doorArea.getWidth()/2);
		int y = (int) (doorArea.getY() - doorArea.getHeight()/2);
		player.setPosition( new Vector2D(x, y) );
	}

	private IInteractiveObject getOutDoor(Set<IInteractiveObject> connectedDoors, IInteractiveObject originDoor) {
		List<IInteractiveObject> connDoorsExcludingOrigin = new ArrayList<>(connectedDoors);
		connDoorsExcludingOrigin.remove(originDoor);
        if (connDoorsExcludingOrigin.isEmpty()) {
            return originDoor; //if door has no connection. then go out the same door you tried to go in
        }

		int randomIndex = random.nextInt(connDoorsExcludingOrigin.size());
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


		String connection = (String)interactiveObject.getProperties().get("connection");
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
		for (IInteractiveObject interactiveObject : interactiveObjects) {
			addDoor(room, interactiveObject);
		}
	}
}
