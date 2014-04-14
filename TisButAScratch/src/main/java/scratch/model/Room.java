package scratch.model;

import scratch.construction.NpcFactory;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single room and the contents in it.
 * @author Ivar Josefsson
 *
 */
public final class Room implements IRoomData{

    private List<Player> players;
    private List<INpc> npcs;
    private final IMap map;
    private final NpcFactory npcFactory = new NpcFactory();

    public Room(IMap collisionMap){
        this.map = collisionMap;
        players = new ArrayList();
        npcs = new ArrayList();

        npcs.add(npcFactory.createEnemy(0));

    }

    /**
     * Adds the specified player from the Room
     * @param player
     */
    public void enterRoom(Player player){
        players.add(player);
    }
    public List<INpc> getNpcs(){
        return npcs;
    }
    /**
     * Removes the specified player from the Room
     * @param player
     */
    public void exitRoom(Player player){
        players.remove(player);
    }

    private void updateCharacter(ICharacter character) {
        if (character.alive()){
            Vector2D newPosition = character.calculateMovementPosition(this);
            character.setPosition(allowedPosition(character.getUnitTile(), newPosition));
            takeDamage(character);
            if(character.isInteracting() && map.hasInteractiveObject()){
                npcs.add(npcFactory.createEnemy(0));
            }

        }
    }

    private boolean takeDamage(ICharacter character){
        if (character instanceof Player){
            for (INpc npc:npcs){
                if (npc.isHostile() && npc.getUnitTile().intersects(character.getUnitTile())){
                    character.takeDamage(npc.getDamage());
                }
            }
            //TODO: WHAT. Nu gör den ju inte ett smack...? attack ska ju vara området som tar skada eller? ..
            character.attack();
        }
        return false;
    }

    /**
     * Called to determine the best allowed position
     * @param unitTile the position we start from
     * @param toPosition the position we want to end at
     * @return the best allowed position
     */
    private Vector2D allowedPosition(Rectangle2D.Double unitTile, Vector2D toPosition){

        double newX = toPosition.getX();
        double newXRight = newX + unitTile.getWidth();
        double newY = toPosition.getY();
        double newYDown = newY + unitTile.getHeight();
        double oldX = unitTile.getX();
        double oldY = unitTile.getY();
        double returnX = oldX;
        double returnY = oldY;

        //Check if new X position is allowed
        if(0 < newX && newXRight < getMapWidth() && !mapCollision(unitTile, new Vector2D(newX, oldY))){
            returnX = newX;
        }

        //Check if new Y position is allowed
        if(0 < newY && newYDown < getMapHeight() && !mapCollision(unitTile, new Vector2D(oldX, newY))){
            returnY = newY;
        }
        return new Vector2D(returnX, returnY);
    }

    /**
     * Checks if there's a collision at the given coordinates
     * @param objectToPlace A "hitbox" of the object to place at placeToPut
     * @param placeToPut the place to put objectToPlace at
     * @return true if there is a collision
     */
    private boolean mapCollision(Rectangle2D.Double objectToPlace, Vector2D placeToPut){

        Vector2D northWest = new Vector2D (placeToPut.getX() + 1, placeToPut.getY() + 1);
        Vector2D northEast = new Vector2D (placeToPut.getX() + objectToPlace.getWidth() - 1, placeToPut.getY() + 1);
        Vector2D southWest = new Vector2D(placeToPut.getX()+ 1, placeToPut.getY() + objectToPlace.getHeight() - 1);
        Vector2D southEast = new Vector2D (placeToPut.getX() + objectToPlace.getWidth() - 1, placeToPut.getY() + objectToPlace.getHeight() - 1);

        return map.isColliding(northWest) || map.isColliding(northEast) || map.isColliding(southEast) || map.isColliding(southWest);
    }

    /**
     *
     * @return: the total height of the map in pixels
     */
    private int getMapHeight() {
        return map.getHeight();
    }

    /**
     *
     * @return: the total width of the map in pixels
     */
    private int getMapWidth() {
        return map.getWidth();
    }

    public void update(){
        for (Player player:players){
            updateCharacter(player);
        }
        for (INpc npc : npcs){
            updateCharacter(npc);
        }

    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public IMap getMap() {
        return map;
    }
}
