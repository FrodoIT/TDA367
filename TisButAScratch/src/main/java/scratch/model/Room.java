package scratch.model;

import scratch.construction.NpcFactory;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a single room and the contents in it.
 * @author Ivar Josefsson
 *
 */
public final class Room implements IRoomData{

    private List<Player> players;
    private List<INpc> npcs;
	private Map<ICharacter, Rectangle2D.Double> areaUnderAttack;
    private final IMap map;
    private final NpcFactory npcFactory = new NpcFactory();

    public Room(IMap collisionMap){
        this.map = collisionMap;
        players = new ArrayList();
        npcs = new ArrayList();

		areaUnderAttack= new HashMap<ICharacter, Rectangle2D.Double>();
        npcs.add(npcFactory.createEnemy(0, 32, 32));

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

	public void update(){
		for (Player player:players){
			updateCharacter(player);
		}
		for (INpc npc : npcs){
			updateCharacter(npc);
		}
		takeDamage();
		areaUnderAttack.clear();
	}

    private void updateCharacter(ICharacter character) {

        if (character.isAlive()) {
	        Vector2D newPosition = character.calculateMovementPosition(this);
	        character.setPosition(allowedPosition(character.getUnitTile(), newPosition));

	        if (character.isAttacking()){ //checks button press
		        if(character.weaponHasCooledDown()) {
			        Rectangle2D.Double attackArea = character.attack();
			        areaUnderAttack.put(character, attackArea);
			        System.out.println("Attack added: " + attackArea);
		        }
	        }
            if(character.isInteracting() && map.hasInteractiveObject()){
                npcs.add(npcFactory.createEnemy(0, 420, 420));
            }
        }
    }

    private boolean takeDamage(){
	    for (Map.Entry<ICharacter, Rectangle2D.Double> attackEntry : areaUnderAttack.entrySet()) {
		    for(INpc npc:npcs){
			    if((npc.getUnitTile().intersects( attackEntry.getValue()))){
				    npc.takeDamage(attackEntry.getKey().getDamage());
			    }
		    }
		    for(Player player: players){
			    if((player.getUnitTile().intersects( attackEntry.getValue()))){
				    player.takeDamage(attackEntry.getKey().getDamage());
			    }
            }
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


    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public IMap getMap() {
        return map;
    }
}
