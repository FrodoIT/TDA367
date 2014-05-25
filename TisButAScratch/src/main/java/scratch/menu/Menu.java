/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package scratch.menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import scratch.controller.GameController;
import scratch.model.Game;

import javax.swing.*;

/**
 *
 * @author Ivar
 */
public final class Menu {

    private Menu() {
    }
    
    public static void mainMenu() throws SlickException {

        final JFrame menu = new MainMenu();
        menu.setVisible(true);

    }

    public static void startGame(String ip) throws SlickException {
        final Game game = new Game();
        final org.newdawn.slick.Game controller;
        if (scratch.network.Utilities.validIP(ip)) {
            controller = new GameController(ip);
        } else {
            controller = new GameController(game);
        }
        final AppGameContainer app = new AppGameContainer(controller);
        app.start();
    }
}
