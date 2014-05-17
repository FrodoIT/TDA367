package scratch.controller;

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

    public RoomController(Room room, RoomView roomView){
        this.roomView = roomView;
        this.room = room;
        characters = new ArrayList<>();
        for (GameCharacter character : room.getCharacters()){
            characters.add(new CharacterController(character));
        }

    }


    public void updateRoom(){
        if(room.isActive()){
            room.update();
        }
    }

    public void render(GameContainer gameContainer) {
        roomView.render();
        for (CharacterController characterController : characters){
            characterController.render(gameContainer);
        }
        
    }

    public RoomView getRoomView() {
        return roomView;
    }
    
    public int getId() {
        return room.getId();
    }
}
