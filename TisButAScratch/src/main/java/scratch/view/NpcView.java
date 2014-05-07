/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.NpcType;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author Ivar
 */
public class NpcView{
    private SpriteDirectionRenderer spriteHandler;
	private Rectangle2D.Double attackArea;

    public NpcView(String imagePath){
        try {
            spriteHandler = new SpriteDirectionRenderer( new TiledMap(imagePath) );
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(NpcType npc, Graphics g){
	    if(npc.isAlive()) {
		    if(!npc.weaponHasCooledDown()) {
			    //saves attackArea every time player fights co be able to continue to render it until the weaponCD is down.
			    if(npc.isAttacking()) {
				    attackArea = npc.getAttackArea();
			    }
			    g.setColor(Color.blue);
			    g.fill(new Rectangle((int) attackArea.getX(), (int) attackArea.getY(), (int) attackArea.getWidth(), (int) attackArea.getHeight()));
		    }
		    spriteHandler.render(g, npc.getMoveDirection(), npc.getPosition().getX(), npc.getPosition().getY());
	    }

    }


}
