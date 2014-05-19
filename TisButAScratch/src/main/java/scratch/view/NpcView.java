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
public class NpcView extends CharacterView{

    public NpcView(NpcType character){
        super(character);
    }

    public void render(GameContainer gameContainer){
        super.render(gameContainer);
		//TODO gör så healhbaren är över huvudet på varje npc.
    }


}
