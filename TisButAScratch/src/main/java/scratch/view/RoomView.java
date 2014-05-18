package scratch.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import scratch.construction.TiledMapPlus;
import scratch.model.IInteractiveObject;

import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author André Samuelsson
 *
 */

public final class RoomView {
    private final TiledMapPlus map;
    private final Map<Integer, String> imageMap;

    public RoomView(TiledMapPlus map) {
        imageMap = new TreeMap<Integer, String>();
        this.map = map;
        setupObjectImageMap();

    }


    private void setupObjectImageMap(){

    }


    public void render(GameContainer gameContainer) {
        map.render(0, 0);
        /*Graphics graphics = gameContainer.getGraphics();
        for(final IInteractiveObject object :  map.getInteractiveObjects()){
            if ( "box".compareTo(object.getProperties().getProperty("objectType")) == 0) {
                final String imagePath = object.getProperties().getProperty("imagePath");
                try {
                  TiledMapPlus map = new TiledMapPlus(imagePath);
                } catch (SlickException e) {
                  e.printStackTrace();
                }
                //final Rectangle2D.Double unitTile = object.getUnitTile();
                //final double y = unitTile.getY();
                //final double x = unitTile.getX();

                //graphics.drawImage(map.getTileImage((int) x, (int) y, map.getLayerIndex("background")), (float) x, (float) y) ;
            }
        
    */}

    /*
    for(Map.Entry<Integer, NpcType> npcEntry : room.getNpcs().entrySet()){
        int id = npcEntry.getValue().getId();
        if(npcViews.containsKey(id)){
            npcViews.get(id).render(npcEntry.getValue(), g);
        } else {
            npcViews.get(0).render(npcEntry.getValue(), g);
        }
        */
    }

