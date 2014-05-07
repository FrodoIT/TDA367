/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

/**
 *
 * @author Cannonbait
 */
public class ScratchClient {

	Client kryoClient;

	public ScratchClient() {

		kryoClient = new Client();
		kryoClient.start();

		try {
			kryoClient.connect(5000, "192.168.0.4", 54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}

		kryoClient.sendTCP("Test meddelande lite så va kul. ^.^");

		kryoClient.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof String) {
					String response = (String)object;
					System.out.println(response);
				}
			}
		});
	}
}
