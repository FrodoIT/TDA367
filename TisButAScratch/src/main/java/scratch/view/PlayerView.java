package scratch.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IPlayerInput;
import scratch.model.MoveDirection;
import scratch.model.Player;
import scratch.model.Vector2D;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author André Samuelsson
 * @RevisedBy Anna Nylander
 */

public class PlayerView {

    private Player player;
    private SpriteDirectionRenderer spriteHandler;
	private Rectangle2D.Double attackArea;

    public PlayerView(Player player) {
        this.player = player;
        animationSetUp();
    }

    private final void animationSetUp(){
        //TODO should probably be moved to another class later
        //we fetch the sprite through a tiledmap (like we do with the room map)

        try {
            spriteHandler = new SpriteDirectionRenderer( new TiledMap("res/playerSprite.tmx") );
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

	int i = 0;

    public void render(GameContainer c, Graphics g) {
        Vector2D position = player.getPosition();
        IPlayerInput playerInput = player.getPlayerInput();

	    if(!player.weaponHasCooledDown()) {
		    //saves attackArea every time player fights co be able to continue to render it until the weaponCD is down.
		    if(player.isAttacking()) {
			    attackArea = player.getAttackArea();
		    }
		    g.setColor(Color.red);
		    g.fill(new Rectangle((int) attackArea.getX(), (int) attackArea.getY(), (int) attackArea.getWidth(), (int) attackArea.getHeight()));
	    }

        MoveDirection input= playerInput.getMoveInput();

        spriteHandler.render(g, input, position.getX(),position.getY());
    }


}
