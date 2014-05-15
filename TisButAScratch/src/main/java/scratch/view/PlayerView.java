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
    private final Player player;
    private final Graphics graphics;


    public PlayerView(Player player, GameContainer gameContainer, String imagePath) {
        this.player = player;
        this.graphics = gameContainer.getGraphics();
        try {
            spriteHandler = new SpriteDirectionRenderer( new TiledMap(imagePath));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public void render(GameContainer gameContainer) {
        Vector2D position = player.getPosition();
        IPlayerInput playerInput = player.getPlayerInput();

        if (player.isAttacking()) {
            Rectangle2D.Double attackArea = player.getAttackArea();
            graphics.setColor(Color.red);
            graphics.fill(new Rectangle((int) attackArea.getX(), (int) attackArea.getY(), (int) attackArea.getWidth(), (int) attackArea.getHeight()));
        }

        MoveDirection input= playerInput.getMoveInput();
        spriteHandler.render(graphics, input, position.getX(),position.getY());
    }
}
