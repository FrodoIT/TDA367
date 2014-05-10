/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import scratch.controller.Controller;
import scratch.model.Model;
import scratch.view.View;

/**
 *
 * @author Ivar
 */
public class Menu {

    public static void mainMenu() throws SlickException {
        String ip;
        ip = JOptionPane.showInputDialog(new JFrame(), "Enter IP to join or leave blank to host", "Ti's but a scratch", JOptionPane.QUESTION_MESSAGE);
        
        Model model = new Model();
        View view = new View();
        final Controller controller;
        if (Menu.validIP(ip)) {
            controller = new Controller(model, view, ip);
        } else {
            controller = new Controller(model, view, null);
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
