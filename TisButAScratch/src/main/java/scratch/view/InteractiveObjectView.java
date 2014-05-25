package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.Direction;
import scratch.model.InteractiveObject;

import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InteractiveObjectView {

    private SpriteDirectionRenderer spriteHandler;
    private final InteractiveObject interactiveObject;

    public InteractiveObjectView(InteractiveObject interactiveObject) {
        this.interactiveObject = interactiveObject;
    }

    public void render(GameContainer gameContainer) {
        //This should not be needed
        final String imagePath = interactiveObject.getProperties().get("imagePath");
        if (imagePath != null) {
            if (spriteHandler == null) {
                try {
                    spriteHandler = new SpriteDirectionRenderer(new TiledMap(imagePath));
                } catch (SlickException ex) {
                    Logger.getLogger(NpcView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            final Rectangle2D.Double unitTile = interactiveObject.getUnitTile();
            spriteHandler.render(gameContainer.getGraphics(), Direction.NORTH, unitTile.getX(), unitTile.getY());
        }
    }
}
