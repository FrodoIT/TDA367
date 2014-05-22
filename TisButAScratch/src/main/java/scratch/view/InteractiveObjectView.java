package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IInteractiveObject;
import scratch.model.MoveDirection;

import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InteractiveObjectView {

    private SpriteDirectionRenderer spriteHandler;
    private final IInteractiveObject interactiveObject;
    private Rectangle2D.Double unitTile;

    public InteractiveObjectView(IInteractiveObject interactiveObject) {
        this.interactiveObject = interactiveObject;
    }

    public void render(GameContainer gameContainer) {
        //This should not be needed
        if (interactiveObject.getProperties().get("imagePath") != null) {
            if (spriteHandler == null) {
                try {
                    spriteHandler = new SpriteDirectionRenderer(new TiledMap(interactiveObject.getProperties().get("imagePath")));
                } catch (SlickException ex) {
                    Logger.getLogger(NpcView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            spriteHandler.render(gameContainer.getGraphics(), MoveDirection.NORTH, interactiveObject.getUnitTile().getX(), interactiveObject.getUnitTile().getY());
        }
    }
}
