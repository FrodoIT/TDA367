/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import scratch.controller.PlayerInput;
import scratch.model.AbstractCharacter;
import scratch.model.MoveDirection;
import scratch.model.NpcType;
import scratch.model.Player;
import scratch.model.Room;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

/**
 *
 * @author Cannonbait
 */
public class Utilities {

    public static void kryoRegister(Kryo kryo) {
        //Classes required for Room
        kryo.register(Room.class);
        kryo.register(ArrayList.class);

        //Required for PlayerInput
        kryo.register(PlayerInput.class);

        //Classes required for Player
        kryo.register(Player.class);

        //Classes required for NpcType
        kryo.register(NpcType.class);

        //Classes required for AbstractCharacter
        kryo.register(AbstractCharacter.class);
        kryo.register(Rectangle2D.Double.class);
        kryo.register(IWeapon.class);
        kryo.register(DefaultWeapon.class);
        kryo.register(MoveDirection.class);

    }

    public static boolean validIP(String ip) {
        try {
            if (ip == null || ip.isEmpty()) {
                return false;
            }
            ip = ip.trim();
            String[] parts = ip.split("\\.");
            if (parts.length != 4) {
                return false;
            }
            for (String s : parts) {
                int i = Integer.parseInt(s);
                if (i < 0 || 255 < i) {
                    return false;
                }
            }
            if (ip.endsWith(".")) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
