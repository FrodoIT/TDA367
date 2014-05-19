/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import scratch.model.GameCharacter;
import scratch.model.NpcType;

/**
 *
 * @author Cannonbait
 */
public class NpcView extends CharacterView{
	NpcType npc;
    public NpcView(NpcType character){
        super(character);
	    npc= character;

    }
	@Override
	public void setCharacter (GameCharacter character){
		super.setCharacter(character);
		this.npc = (NpcType) character;
	}
    public void render(GameContainer gameContainer){
        super.render(gameContainer);
		//TODO gör så healhbaren är över huvudet på varje npc.
	 //   System.out.println(npc.getPosition());
	    Rectangle lifeGreen = new Rectangle((float)npc.getPosition().getX()-10, (float) npc.getPosition().getY()-10, npc.getHealth()*5, 5);
	    Rectangle lifeRed = new Rectangle((float)npc.getPosition().getX()-10, (float) npc.getPosition().getY()-10, 10*5, 5);
	    Graphics g = gameContainer.getGraphics();
	    g.setColor(Color.red);
		g.fill(lifeRed);
	    g.setColor(Color.green);
	    g.fill(lifeGreen);

    }


}
