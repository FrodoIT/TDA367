/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 *
 * @author Cannonbait
 */
public class ListenerClient extends Listener{

    public void received(Connection connection, Object object) {
        if (object instanceof NetworkStringMessage) {
            System.out.println(((NetworkStringMessage)object).getText());
        }
    }
}
