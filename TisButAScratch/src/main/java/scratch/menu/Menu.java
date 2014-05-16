/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import scratch.controller.ClientController;
import scratch.controller.ServerController;
import scratch.model.Game;

import javax.swing.*;
import scratch.network.Utilities;

/**
 *
 * @author Ivar
 */
public class Menu {

    public static void mainMenu() throws SlickException {
        String ip;
        ip = JOptionPane.showInputDialog(new JFrame(), "Enter IP to join or leave blank to host", "Ti's but a scratch", JOptionPane.QUESTION_MESSAGE);
        
        Game game = new Game();
        final org.newdawn.slick.Game controller;
        
        if (Utilities.validIP(ip)) {
            controller = new ClientController(ip);
        } else {
            controller = new ServerController(game);
            
        }
        AppGameContainer app = new AppGameContainer(controller);
        app.start();
    }
}
