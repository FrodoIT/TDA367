package scratch.view;

import java.awt.Point;

import scratch.model.IPlayerInput;
import scratch.model.MoveDirection;
import scratch.model.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author André Samuelsson
 * @RevisedBy Anna Nylander
 */

public class PlayerView {
	
	private Player player;
	private SpriteDirectionRenderer spriteHandler;
	
	public PlayerView(Player player) {
		this.player = player;
		animationSetUp();
	}
	
	public void animationSetUp(){
		//TODO should probably be moved to another class later
		//we fetch the sprite through a tiledmap (like we do with the room map)
		
		try {
			spriteHandler = new SpriteDirectionRenderer( new TiledMap("res/playerSprite.tmx") );
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}		
	
	public void render(GameContainer c, Graphics g) {
		Point p = player.getPosition();
		IPlayerInput playerInput = player.getPlayerInput();
		
		MoveDirection input= playerInput.getMoveInput();
		
		spriteHandler.render(g, input, p.x, p.y);
	}
	

}
