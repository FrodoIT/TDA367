/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import com.esotericsoftware.kryo.Kryo;
import scratch.construction.*;
import scratch.model.*;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Cannonbait
 */
public class Utilities {
    public static void kryoRegister(Kryo kryo){
        
        //Looks ugly as shit, is there a different solution?
        kryo.register(Character.class);
        kryo.register(Constants.class);
        kryo.register(DoorHandler.class);
        kryo.register(IInteractiveObject.class);
        kryo.register(IMap.class);
        kryo.register(INPCMove.class);
        kryo.register(IPlayerInput.class);
        kryo.register(IRoomData.class);
        kryo.register(Game.class);
        kryo.register(MoveDirection.class);
        kryo.register(NpcType.class);
        kryo.register(Player.class);
        kryo.register(Room.class);
        kryo.register(Vector2D.class);
        kryo.register(DefaultWeapon.class);
        kryo.register(IWeapon.class);
        kryo.register(HashMap.class);
        kryo.register(HashSet.class);
        kryo.register(ArrayList.class);
        
        kryo.register(Vector2D.class);
        kryo.register(Point2D.Double.class);
        
        //Register constructionpackage
        kryo.register(INPCMoveTransformer.class);
        kryo.register(MyMatcher.class);
        kryo.register(NpcFactory.class);
        kryo.register(AbstractPluginUserFactory.class);
        kryo.register(RectangleTransformer.class);
        kryo.register(RoomFactory.class);
        kryo.register(SlickMap.class);
        kryo.register(TiledMapPlus.class);
        kryo.register(WeaponTranformer.class);
        
        
    }
}
