package scratch.controller;

import scratch.model.Room;
import scratch.view.RoomView;

public class RoomController {
    private final Room room;
    private final RoomView roomView;

    public RoomController(Room room, RoomView roomView){
        this.roomView = roomView;
        this.room = room;

    }


    public void updateRoom(){
        if(room.isActive()){
            room.update();
        }
    }

    public void render() {
        roomView.render();
        
    }

    public RoomView getRoomView() {
        return roomView;
    }
    
    public int getId() {
        return room.getId();
    }
}
