package scratch.controller;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import scratch.construction.TiledMapPlus;
import scratch.model.GameCharacter;
import scratch.model.IInteractiveObject;
import scratch.model.Room;
import scratch.network.NetworkServer;
import scratch.view.RoomView;

public class RoomController {

    private final Room room;
    private final RoomView roomView;
    private final List<CharacterController> characters;
    private final List<InteractiveObjectController> interactiveObjects;
    private NetworkServer server;


    public RoomController(Room room) {
        this.roomView = new RoomView((TiledMapPlus)room.getMap(), room);
        this.room = room;
        characters = new ArrayList<>();
        for (final GameCharacter character : room.getCharacters()){
            characters.add(new CharacterController(character));
        }
        
        interactiveObjects = new ArrayList<>();
        for (final IInteractiveObject interactiveObject : room.getInteractiveObjects()){
            interactiveObjects.add(new InteractiveObjectController(interactiveObject));
        }
    }
    
    public void setServer (NetworkServer server) {
        this.server = server;
        for (CharacterController characterController : characters){
            characterController.setServer(server);
        }
        for (InteractiveObjectController interactiveController : interactiveObjects){
            interactiveController.setServer(server);
        }
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
