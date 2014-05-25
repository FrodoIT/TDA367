/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import scratch.model.*;
import scratch.model.weapons.Weapon;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Cannonbait
 */
public final class Utilities {

    private static final String IP_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private Utilities() {
    }

    public static void kryoRegister(Kryo kryo) {
        kryo.register(Room.class);
        kryo.register(ArrayList.class);
        kryo.register(GameCharacter.class);
        kryo.register(NpcType.class);
        kryo.register(GameCharacter.class);
        kryo.register(Rectangle2D.Double.class);
        kryo.register(Weapon.class);
        kryo.register(Direction.class);
        kryo.register(Vector2D.class);
        kryo.register(Point2D.class);
        kryo.register(Point2D.Double.class);
        kryo.register(InteractiveObject.class);
        kryo.register(HashMap.class);
        kryo.register(MovableObject.class);
        kryo.register(Attack.class);
        kryo.register(Class.class);
        
        //Packets
        kryo.register(PacketAddCharacter.class);
        kryo.register(PacketAttacks.class);
        kryo.register(PacketGameData.class);
        kryo.register(PacketMoveCharacter.class);
        kryo.register(PacketNewCharacter.class);
        kryo.register(PacketNewConnection.class);
        kryo.register(PacketPlayerInput.class);
        kryo.register(PacketRemoveCharacter.class);
    }

    //använd nätverksgrejerna i java: köra genom en metod som gör om från hostname till

    public static boolean validIP(String ip) {
        if (ip == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
