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

    public static void mainMenu() throws SlickException{
        String ip;
        ip = JOptionPane.showInputDialog(new JFrame(), "Enter IP to join or leave blank to host", "Ti's but a scratch", JOptionPane.QUESTION_MESSAGE);
        
        if (ip.length() == 0) {
            System.out.println("Starting host");
            Model model = new Model();
            View view = new View();
            final Controller controller = new Controller(model, view, null);
            AppGameContainer app = new AppGameContainer(controller);
            app.start();
        } else {
            System.out.println("Starting client at: " + ip);
            Model model = new Model();
            View view = new View();
            final Controller controller = new Controller(model, view, ip);
            AppGameContainer app = new AppGameContainer(controller);
            app.start();

        }
    }
}
