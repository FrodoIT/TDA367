package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.GameCharacter;
import scratch.model.MoveDirection;
import scratch.model.Vector2D;

/**
 *
 * @author André Samuelsson
 * @RevisedBy Anna Nylander
 */
public class PlayerView {

    private SpriteDirectionRenderer spriteHandler;
    private final GameCharacter player;
    private final Graphics graphics;


    public PlayerView(GameCharacter player, GameContainer gameContainer, String imagePath) {
        this.player = player;
        this.graphics = gameContainer.getGraphics();
        animationSetUp(imagePath);
    }

    private final void animationSetUp(String imagePath) {
        //TODO should probably be moved to another class later
        //we fetch the sprite through a tiledmap (like we do with the room map)

        try {
            spriteHandler = new SpriteDirectionRenderer(new TiledMap(imagePath));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        final Vector2D position = player.getPosition();

/*        if (player.isPromptingAnAttack()) {
            attackArea = player.getAttackArea();
            graphics.setColor(Color.red);
            graphics.fill(new Rectangle((int) attackArea.getX(), (int) attackArea.getY(), (int) attackArea.getWidth(), (int) attackArea.getHeight()));
        }*/

        final MoveDirection input = player.getMoveDirection();
        spriteHandler.render(graphics, input, position.getX(), position.getY());
    }
}
