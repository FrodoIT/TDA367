/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import java.awt.geom.Rectangle2D;
import scratch.model.AbstractCharacter;
import scratch.model.MoveDirection;
import scratch.model.Player;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

/**
 *
 * @author Cannonbait
 */
public class Utilities {
    public static void kryoRegister(Kryo kryo){
        
        //Classes required for Player
        kryo.register(Player.class);
        
        //Classes required for AbstractCharacter
        kryo.register(AbstractCharacter.class);
        kryo.register(Rectangle2D.Double.class);
        kryo.register(IWeapon.class);
        kryo.register(DefaultWeapon.class);
        kryo.register(MoveDirection.class);
        
        
    }
}
