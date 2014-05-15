/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import java.awt.geom.Rectangle2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.AbstractCharacter;
import scratch.model.IPlayerInput;
import scratch.model.MoveDirection;
import scratch.model.Vector2D;

/**
 *
 * @author Cannonbait
 */
public class CharacterView {
    private final AbstractCharacter character;
    private SpriteDirectionRenderer spriteHandler;
    
    public CharacterView (AbstractCharacter character){
        this.character = character;
        try {
            String imagePath = character.getImagePath();
            System.out.println("Trying to load imagepath: " + imagePath);
            spriteHandler = new SpriteDirectionRenderer(new TiledMap(imagePath));
        } catch (SlickException e){
            System.out.println(e.toString());
        }
    }
    
    public void render(GameContainer gameContainer){
        Vector2D position = character.getPosition();
        Graphics graphics = gameContainer.getGraphics();
        
        if (character.isAttacking()) {
            Rectangle2D.Double attackArea = character.getAttackArea();
            graphics.setColor(Color.red);
            graphics.fill(new Rectangle((int) attackArea.getX(), (int) attackArea.getY(), (int) attackArea.getWidth(), (int) attackArea.getHeight()));
        }

        MoveDirection input= character.getMoveDirection();
        spriteHandler.render(graphics, input, position.getX(),position.getY());
    }
    
    
}
