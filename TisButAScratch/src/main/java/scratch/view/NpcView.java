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
import scratch.model.Vector2D;

/**
 *
 * @author Cannonbait
 */
public class NpcView extends CharacterView {

    private NpcType npc;
    private Vector2D pos;
    private int fulllenght, maxHealth, height;
    private double currentHealth;

    public NpcView(NpcType character) {
        super(character);
        npc = character;
        maxHealth = npc.getHealth();
        fulllenght = 50;
        height = 5;
    }

    public void render(GameContainer gameContainer) {
        super.render(gameContainer);
        //healthbar over npc
        pos = npc.getPosition();

        //get percentage of life left;
        currentHealth = (double) (npc.getHealth()) / maxHealth;

        Rectangle lifeGreen = new Rectangle((float) pos.getX() - 10, (float) pos.getY() - 10, (float) currentHealth * fulllenght, height);
        Rectangle lifeRed = new Rectangle((float) pos.getX() - 10, (float) pos.getY() - 10, fulllenght, height);

        Graphics g = gameContainer.getGraphics();
        g.setColor(Color.red);
        g.fill(lifeRed);
        g.setColor(Color.green);
        g.fill(lifeGreen);

    }

}
