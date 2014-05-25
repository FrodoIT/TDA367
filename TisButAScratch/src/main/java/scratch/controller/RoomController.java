package scratch.controller;

import org.newdawn.slick.GameContainer;
import scratch.construction.TiledMapPlus;
import scratch.model.GameCharacter;
import scratch.model.InteractiveObject;
import scratch.model.Room;
import scratch.network.NetworkClient;
import scratch.network.NetworkServer;
import scratch.view.RoomView;

import java.util.ArrayList;
import java.util.List;

public class RoomController {

    private final Room room;
    private final RoomView roomView;
    private final List<CharacterController> characters;
    private final List<InteractiveObjectController> interactiveObjects;

    public RoomController(Room room) {
        this.roomView = new RoomView((TiledMapPlus) room.getMap());
        this.room = room;
        characters = new ArrayList<>();
        for (final GameCharacter character : room.getCharacters()) {
            characters.add(new CharacterController(character));
        }

        interactiveObjects = new ArrayList<>();
        for (final InteractiveObject interactiveObject : room.getInteractiveObjects()) {
            interactiveObjects.add(new InteractiveObjectController(interactiveObject));
        }
    }

    public void setServer(NetworkServer server) {
        for (final CharacterController characterController : characters) {
            characterController.setServer(server);
        }
        for (final InteractiveObjectController interactiveController : interactiveObjects) {
            interactiveController.setServer(server);
        }
    }

    public void setClient(NetworkClient client) {
        for (final CharacterController characterController : characters) {
            characterController.setClient(client);
        }
        for (final InteractiveObjectController interactiveController : interactiveObjects) {
            interactiveController.setClient(client);
        }
    }

    public void updateRoom() {
        for (final CharacterController characterController : characters) {
            characterController.update();
        }

        for (final InteractiveObjectController interactiveObjectController : interactiveObjects) {
            interactiveObjectController.update();
        }
        room.update();
    }

    public synchronized void render(GameContainer gameContainer) {
        roomView.render(gameContainer);

        for (final CharacterController characterController : characters) {
            characterController.render(gameContainer);
        }

        for (final InteractiveObjectController interactiveObjectController : interactiveObjects) {
            interactiveObjectController.render(gameContainer);
        }
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public int getId() {
        return room.getId();
    }

    public synchronized void addCharacter(CharacterController characterController) {
        room.addCharacter(characterController.getCharacter());
        characters.add(characterController);
    }

    public synchronized void removeCharacter(CharacterController characterController) {
        room.removeCharacter(characterController.getCharacter());
        characters.remove(characterController);
    }

    public synchronized void moveCharacter(int characterId, RoomController roomController) {
        final CharacterController characterToMove = getCharacterController(characterId);

        if (characterToMove != null) {
            removeCharacter(characterToMove);
            roomController.addCharacter(characterToMove);
        }
    }

    private CharacterController getCharacterController(int characterId) {
        for (final CharacterController character : characters) {
            if (character.getId() == characterId) {
                return character;
            }
        }
        return null;
    }
    
    public Room getRoom(){
        return room;
    }

    public boolean hasId(int id) {
        for (final CharacterController characterController : characters) {
            if (characterController.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public synchronized void addInteractiveObjectController(InteractiveObjectController interactiveObjectController) {
        interactiveObjects.add(interactiveObjectController);
    }

    public synchronized void addInteractiveObject(InteractiveObjectController interactiveObjectController) {
        room.addInteractiveObject(interactiveObjectController.getInteractiveObject());
        interactiveObjects.add(interactiveObjectController);
    }
}
