/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import java.awt.geom.Point2D;
import scratch.controller.PlayerInput;
import scratch.model.*;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Cannonbait
 */
public class Utilities {

    public static void kryoRegister(Kryo kryo) {
        kryo.register(Room.class);
        kryo.register(ArrayList.class);
        kryo.register(PlayerInput.class);
        kryo.register(Player.class);
        kryo.register(NpcType.class);
        kryo.register(AbstractCharacter.class);
        kryo.register(Rectangle2D.Double.class);
        kryo.register(IWeapon.class);
        kryo.register(DefaultWeapon.class);
        kryo.register(MoveDirection.class);
        kryo.register(PacketNewPlayer.class);
        
        kryo.register(PacketPlayerInput.class);
        kryo.register(Vector2D.class);
        kryo.register(Point2D.class);
        kryo.register(java.awt.geom.Point2D.Double.class);

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
