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
        if (Menu.validIP(ip)) {
            controller = new ClientController(ip);
        } else {
            controller = new ServerController(game);
        }
        AppGameContainer app = new AppGameContainer(controller);
        app.start();
    }

    private static boolean validIP(String ip) {
        try {
            if (ip == null || ip.isEmpty()) {
                return false;
            }
            ip = ip.trim();
            String[] parts = ip.split("\\.");
            if (parts.length != 4){
                return false;
            }
            for (String s : parts){
                int i = Integer.parseInt(s);
                if (i < 0 || 255 < i){
                    return false;
                }
            }
            if (ip.endsWith(".")){
                return false;
            }
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
