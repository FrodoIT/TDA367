package scratch.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IInteractiveObject;
import scratch.model.MoveDirection;
import scratch.view.SpriteDirectionRenderer;

import java.awt.geom.Rectangle2D;

public class InteractiveObjectView {
    private SpriteDirectionRenderer spriteHandler;
    private IInteractiveObject interactiveObject;

    public InteractiveObjectView(IInteractiveObject interactiveObject) {
        this.interactiveObject = interactiveObject;


        //else {
         //   try {
           //     spriteHandler = new SpriteDirectionRenderer(new TiledMap("/res/playerSprite.tmx"));
            //} catch(SlickException e){
              //  e.printStackTrace();
           // }
       // }
    }

    public void render(GameContainer gameContainer) {
            final String imagePath = interactiveObject.getProperties().get("imagePath");
        if ( "box".compareTo(interactiveObject.getProperties().get("objectType")) == 0) {
            final Rectangle2D.Double unitTile = interactiveObject.getUnitTile();
            final double y = unitTile.getY();
            final double x = unitTile.getX();
            try {
                spriteHandler = new SpriteDirectionRenderer(new TiledMap(imagePath));
                spriteHandler.render(gameContainer.getGraphics(), MoveDirection.NORTH, interactiveObject.getUnitTile().getX(), interactiveObject.getUnitTile().getY());
            } catch (SlickException e){
                e.printStackTrace();
            }
        }
    }
}
