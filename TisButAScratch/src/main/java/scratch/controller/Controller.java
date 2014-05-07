package scratch.controller;

import scratch.construction.InteractiveObjectFactory;
import scratch.construction.NpcFactory;
import scratch.construction.RoomFactory;

import java.util.ArrayList;
import java.util.List;

import scratch.model.IInteractiveObject;
import scratch.model.INpc;
import scratch.model.Model;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import scratch.view.View;

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
