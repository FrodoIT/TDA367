/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import scratch.network.ScratchClient;
import scratch.network.ScratchServer;

/**
 *
 * @author Ivar
 */
public class Menu {

    public static void mainMenu() {
        String ip;
        do {
            ip = JOptionPane.showInputDialog(new JFrame(), "Enter IP to join or leave blank to host", "Ti's but a scratch", JOptionPane.QUESTION_MESSAGE);
        } while (false);
        if (ip.length() == 0){
            System.out.println("Starting host");
            ScratchServer server = new ScratchServer();
        } else {
            System.out.println("Starting client at: " + ip);
            ScratchClient client = new ScratchClient(ip);
        }
    }
    
    public static boolean realIPAddress(String ip){
        return true;
    }
}
