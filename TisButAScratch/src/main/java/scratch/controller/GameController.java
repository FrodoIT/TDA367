/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import scratch.model.Game;

/**
 *
 * @author Ivar
 */
public class GameController implements org.newdawn.slick.Game {

    private boolean host;
    private String ip;
    private ServerController server;
    private ClientController client;

    public GameController(String ip) {
        host = false;
        this.ip = ip;
        client = new ClientController(ip);
    }

    public GameController(Game game) {
        host = true;
        server = new ServerController(game);
        client = new ClientController("127.0.0.1");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        container.setTargetFrameRate(60);
        if (host) {
            server.init(container);
        }
        client.init(container);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (host) {
            server.update(container, delta);
        }
        client.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        client.render(container, g);
    }

    @Override
    public boolean closeRequested() {
        return true;
    }

    @Override
    public String getTitle() {
        return "'Tis but a scratch";
    }
}
