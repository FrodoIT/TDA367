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
 *
 * @author Anna Nylander
 *
 */
public final class Controller implements Game {

    private final Model model;
    private final View view;
    private final NetworkController networkController;
    private List<PlayerController> playerControllerList;


    public Controller(Model model, View view, String ip) throws SlickException {
        this.view = view;
        this.model = model;
        networkController = new NetworkController(ip);
        playerControllerList = new ArrayList<PlayerController>();
        
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        container.setTargetFrameRate(60);

        view.addNpcView(0, "/res/playerSprite.tmx");

        RoomFactory trf = new RoomFactory();
        view.addRoomView(trf.getRooms().get(0), trf.getTiledMap());
        model.setMap(trf.getRooms());
        
        PlayerController playerController = new PlayerController(model, view);
        playerControllerList.add(playerController);
        networkController.start();
        
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        view.render(container, g);

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        for (PlayerController pc : playerControllerList) {
            pc.registerAllInput(container.getInput());
        }
        model.update();
        networkController.update();
    }

    public List<PlayerController> getPlayerControllerList() {
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
