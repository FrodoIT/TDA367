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

    private SpriteDirectionRenderer spriteHandler;
    private Rectangle2D.Double attackArea;
    private final Player player;
    private final Graphics graphics;
    private final GameContainer gameContainer;
    private final String imagePath;
    

    public PlayerView(Player player, GameContainer gameContainer, String imagePath) {
        this.player = player;
        this.gameContainer = gameContainer;
        this.graphics = this.gameContainer.getGraphics();
        this.imagePath = imagePath;
        animationSetUp();
    }

    private final void animationSetUp() {
        //TODO should probably be moved to another class later
        //we fetch the sprite through a tiledmap (like we do with the room map)

        try {
            spriteHandler = new SpriteDirectionRenderer(new TiledMap(imagePath));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        Vector2D position = player.getPosition();

/*        if (player.isAttacking()) {
            attackArea = player.getAttackArea();
            graphics.setColor(Color.red);
            graphics.fill(new Rectangle((int) attackArea.getX(), (int) attackArea.getY(), (int) attackArea.getWidth(), (int) attackArea.getHeight()));
        }*/

        MoveDirection input = player.getMoveDirection();
        spriteHandler.render(graphics, input, position.getX(), position.getY());
    }
}
