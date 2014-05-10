/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
    private final NpcType npc;
    private final GameContainer gameContainer;
    private final Graphics graphics;


    public NpcView(NpcType npc, GameContainer gameContainer, String imagePath){
        try {
            spriteHandler = new SpriteDirectionRenderer(new TiledMap(imagePath));
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.npc = npc;
        this.gameContainer = gameContainer;
        graphics = this.gameContainer.getGraphics();
    }

    public void render(){
	    if(npc.isAlive()) {
		    if(!npc.getWeapon().hasCooledDown()) {
			    //saves attackArea every time player fights co be able to continue to render it until the weaponCD is down.
			    if(npc.isAttacking()) {
				    attackArea = npc.getAttackArea();
			    }
			    graphics.setColor(Color.blue);
			    graphics.fill(new Rectangle(
                        (int) attackArea.getX(),
                        (int) attackArea.getY(),
                        (int) attackArea.getWidth(),
                        (int) attackArea.getHeight()));
		    }
		    spriteHandler.render(graphics,
                    npc.getMoveDirection(),
                    npc.getPosition().getX(),
                    npc.getPosition().getY());
	    }

    }


}
