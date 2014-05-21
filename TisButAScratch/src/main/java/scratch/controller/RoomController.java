package scratch.controller;

import org.newdawn.slick.GameContainer;
import scratch.model.Room;
import scratch.view.RoomView;

import java.util.ArrayList;
import java.util.List;

public class RoomController {

    private final Room room;
    private final RoomView roomView;
    private final List<CharacterController> characters;
    private final List<InteractiveObjectController> interactiveObjects;


    public RoomController(Room room, RoomView roomView) {
        this.roomView = roomView;
        this.room = room;
        interactiveObjects = new ArrayList<>();
        characters = new ArrayList<>();
    }

    public void updateRoom() {
        if (room.isActive()) {
            for (CharacterController characterController : characters) {
                characterController.update();
            }

            for(InteractiveObjectController interactiveObjectController : interactiveObjects) {
                interactiveObjectController.update();
            }

            room.update();
        }
    }

    public void render(GameContainer gameContainer) {
        roomView.render(gameContainer);

        for (CharacterController characterController : characters) {
            characterController.render(gameContainer);
        }

        for (InteractiveObjectController interactiveObjectController : interactiveObjects) {
            interactiveObjectController.render(gameContainer);
        }
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public int getId() {
        return room.getId();
    }

    public void addCharacter(CharacterController characterController) {
        room.addCharacter(characterController.getCharacter());
        characters.add(characterController);
    }

    public void removeCharacter(CharacterController characterController) {
        room.removeCharacter(characterController.getCharacter());
        characters.remove(characterController);
    }

    public void moveCharacter(int characterId, RoomController roomController) {
        CharacterController characterToMove = getCharacterController(characterId);

        if (characterToMove != null) {
            removeCharacter(characterToMove);
            roomController.addCharacter(characterToMove);
        }
    }

    private CharacterController getCharacterController(int characterId) {
        for (CharacterController character : characters) {
            if (character.getId() == characterId) {
                return character;
            }
        }
        return null;
    }

    public void addCharacterController(CharacterController characterController) {
        characters.add(characterController);
    }
    
    public boolean hasId(int id){
        for (CharacterController characterController : characters) {
            if (characterController.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void addInteractiveObjectController(InteractiveObjectController interactiveObjectController) {
        interactiveObjects.add(interactiveObjectController);
    }
    public void addInteractiveObject(InteractiveObjectController interactiveObjectController) {
        room.addInteractiveObject(interactiveObjectController.getInteractiveObject());
        interactiveObjects.add(interactiveObjectController);
    }
}
