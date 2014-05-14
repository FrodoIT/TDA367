/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import scratch.model.NpcType;
import scratch.model.Vector2D;
import java.awt.geom.Rectangle2D;

public class NpcView{


    public void render(NpcType npc, SpriteDirectionRenderer spriteHandler, Graphics graphics){
	    if(npc.isAlive()) {
            Vector2D npcPos = npc.getPosition();
            spriteHandler.render(graphics,
                    npc.getMoveDirection(),
                    npcPos.getX(),
                    npcPos.getY());
		    if(!npc.getWeapon().hasCooledDown()) {
			    //saves attackArea every time player fights co be able to continue to render it until the weaponCD is down.
			    if(npc.isAttacking()) {
                    return;
                }

                final Rectangle2D.Double attackArea = npc.getAttackArea();

                graphics.setColor(Color.blue);
                graphics.fill(new Rectangle(
                        (int) attackArea.getX(),
                        (int) attackArea.getY(),
                        (int) attackArea.getWidth(),
                        (int) attackArea.getHeight()));
		    }
	    }
    }
}
