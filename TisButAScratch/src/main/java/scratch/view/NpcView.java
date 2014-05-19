/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.MoveDirection;
import scratch.model.NpcType;
import scratch.model.Vector2D;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cannonbait
 */
public class NpcView {
    private NpcType character;
    private SpriteDirectionRenderer spriteHandler;
    
    public NpcView(NpcType character){
        this.character = character;
        try {
            spriteHandler = new SpriteDirectionRenderer(new TiledMap(character.getImagePath()));
        } catch (SlickException e){
            spriteHandler = null;
            e.printStackTrace();

        }
    }

    public void setCharacter (NpcType character){
        this.character = character;
    }

    public void render(GameContainer gameContainer){
        if (spriteHandler == null){
            try {
                spriteHandler = new SpriteDirectionRenderer(new TiledMap(character.getImagePath()));
            } catch (SlickException ex) {
                Logger.getLogger(NpcView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Vector2D position = character.getPosition();
        Graphics graphics = gameContainer.getGraphics();
        /*
        if (character.isPromptingAnAttack()) {
            Rectangle2D.Double attackArea = character.getAttackArea();
            graphics.setColor(Color.red);
            graphics.fill(new Rectangle((int) attackArea.getX(), (int) attackArea.getY(), (int) attackArea.getWidth(), (int) attackArea.getHeight()));
        }*/

        final MoveDirection input= character.getMoveDirection();
        spriteHandler.render(graphics, input, position.getX(),position.getY());
    }


}
