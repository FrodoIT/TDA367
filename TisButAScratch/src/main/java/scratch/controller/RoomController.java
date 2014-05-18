package scratch.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.model.Room;
import scratch.view.RoomView;

public class RoomController {

    private final Room room;
    private final RoomView roomView;
    private final List<CharacterController> characters;

    public RoomController(Room room, RoomView roomView) {
        this.roomView = roomView;
        this.room = room;
        characters = new ArrayList<>();
    }

    public void updateRoom() {
        if (room.isActive()) {
            for (CharacterController characterController : characters) {
                characterController.update();
            }
            room.update();
        }
    }

    public void render(GameContainer gameContainer) {
        roomView.render();
        for (CharacterController characterController : characters) {
            characterController.render(gameContainer);
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

    public void addCharacterController(CharacterController characterController) {
        characters.add(characterController);
    }
    
    public boolean hasId(int id){
        for (CharacterController characterController : characters){
            if (characterController.getId() == id){
                return true;
            }
        }
        return false;
    }
}
