package scratch.controller;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import scratch.construction.RoomFactory;
import scratch.model.Model;
import scratch.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 * @author Anna Nylander
 *
 */
public final class Controller implements Game{
    private final Model model;
    private final View view;
    private List<PlayerController> playerControllerList;

    public Controller(Model model, View view){
        this.view = view;
        this.model = model;
        playerControllerList=new ArrayList<PlayerController>();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        container.setTargetFrameRate(60);

        
        view.addNpcView(0, "/res/playerSprite.tmx");
        
        RoomFactory trf = new RoomFactory();
        view.addRoomView(trf.getRooms().get(0), trf.getTiledMap());
        model.setMap(trf.getRooms());
        playerControllerList.add(new PlayerController(model, view));




    }
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        view.render(container, g);

    }
    @Override
    public void update(GameContainer container, int delta)throws SlickException {
        for (PlayerController pc: playerControllerList){
            pc.registerAllInput(container.getInput());
        }
        model.update();

    }

    public List<PlayerController> getPlayerControllerList(){
        return playerControllerList;
    }

    @Override
    public boolean closeRequested() {
        return true;
    }
    @Override
    public String getTitle() {
        return "'Tis but a Scratch";
    }
}
