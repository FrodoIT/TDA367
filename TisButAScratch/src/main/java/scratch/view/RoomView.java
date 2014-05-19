package scratch.view;

import org.newdawn.slick.GameContainer;
import scratch.construction.TiledMapPlus;
import scratch.model.Room;

/**
 *
 * @author André Samuelsson
 *
 */

public final class RoomView {
    private final TiledMapPlus map;
    private final Room room;

    public RoomView(TiledMapPlus map, Room room) {
        this.map = map;
        this.room = room;
        setupObjectImageMap();

    }


    private void setupObjectImageMap(){

    }


    public void render(GameContainer gameContainer) {
        map.render(0, 0);
        /*for(final IInteractiveObject interactiveObject :  room.getInteractiveObjects()) {
            if ( "box".compareTo(interactiveObject.getProperties().getProperty("objectType")) == 0) {
                final String imagePath = interactiveObject.getProperties().getProperty("imagePath");
                final Rectangle2D.Double unitTile = interactiveObject.getUnitTile();
                final double y = unitTile.getY();
                final double x = unitTile.getX();
                System.out.println(x + " x " + y + " y");
                try {
                    SpriteDirectionRenderer spriteHandler = new SpriteDirectionRenderer(new TiledMap(imagePath));
                    spriteHandler.render(gameContainer.getGraphics(), MoveDirection.SOUTH, x, y);
                } catch (SlickException e){
                    e.printStackTrace();

                }
                // graphics.drawImage(map.getTileImage((int) x, (int) y, map.getLayerIndex("background")), (float) x, (float) y) ;
            }
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
