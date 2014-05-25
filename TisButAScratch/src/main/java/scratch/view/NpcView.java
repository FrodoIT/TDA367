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
import scratch.model.Vector2D;

/**
 *
 * @author Cannonbait
 */
public class NpcView extends CharacterView {

    private final GameCharacter character;
    private final int maxHealth;

    public NpcView(GameCharacter character) {
        super(character);
        this.character = character;
        maxHealth = character.getHealth();
    }

    @Override
    public void render(GameContainer gameContainer) {
        super.render(gameContainer);
        //healthbar over npc
        final Vector2D pos = character.getPosition();

        //get percentage of life left;
        final double currentHealth = (double) (character.getHealth()) / maxHealth;
        final int fullLength = 50;
        final int height = 5;

        final Rectangle lifeGreen = new Rectangle((float) pos.getX() - 10, (float) pos.getY() - 10, (float) currentHealth * fullLength, height);
        final Rectangle lifeRed = new Rectangle((float) pos.getX() - 10, (float) pos.getY() - 10, fullLength, height);

        final Graphics g = gameContainer.getGraphics();
        g.setColor(Color.red);
        g.fill(lifeRed);
        g.setColor(Color.green);
        g.fill(lifeGreen);

    }

}
