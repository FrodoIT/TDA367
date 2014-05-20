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
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isAnyLocalAddress()) {
                controller = new GameController(ip);
            } else {
                controller = new GameController(game);
            }
            AppGameContainer app = new AppGameContainer(controller);
            app.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
