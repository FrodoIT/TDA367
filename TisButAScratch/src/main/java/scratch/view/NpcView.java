/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import scratch.model.INpc;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Ivar
 */
public class NpcView{
    private SpriteDirectionRenderer spriteHandler;

    public NpcView(String imagePath){
        try {
            spriteHandler = new SpriteDirectionRenderer( new TiledMap(imagePath) );
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(INpc npc, Graphics g){
        spriteHandler.render(g, npc.getMoveDirection(), npc.getPosition().getX(), npc.getPosition().getY());

    }


}
