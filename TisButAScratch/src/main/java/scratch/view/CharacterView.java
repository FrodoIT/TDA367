/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.AbstractCharacter;

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
            spriteHandler = new SpriteDirectionRenderer(new TiledMap(character.getImagePath()));
        } catch (SlickException e){
            throw new RuntimeException("Error loading sprite");
        }
    }
    
    public void render(GameContainer gameContainer){
        System.out.println("We are rendering a character");
    }
    
    
}
