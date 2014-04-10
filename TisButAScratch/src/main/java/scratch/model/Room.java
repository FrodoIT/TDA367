package scratch.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single room and the contents in it.
 * @author Ivar Josefsson
 *
 */
public class Room {
	
	private List<Player> players;
        private List<INpc> npcs;
	private IMap map;
	
	public Room(IMap collisionMap){
		this.map = collisionMap;
		players = new ArrayList();
                npcs = new ArrayList();
                npcs.add(new NpcType(new Rectangle(50,50,32,32), null, 1, 1, null, 0));
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
	
	
	
	private void movePlayer(Player player){
		Point newPosition = player.calculateMovementPosition();
		player.setPosition(allowedPosition(player.getUnitTile(), newPosition));
	}
	
	
	/**
	 * Called to determine the best allowed position
	 * @param unitTile the position we start from
	 * @param toPosition the position we want to end at
	 * @return the best allowed position
	 */
	private Point allowedPosition(Rectangle unitTile, Point toPosition){

		int newX = (int)toPosition.getX();
		int newXRight = newX + (int)unitTile.getWidth();
		int newY = (int)toPosition.getY();
		int newYDown = newY + (int)unitTile.getHeight();
		int oldX = (int)unitTile.getX();
		int oldY = (int)unitTile.getY();
		int returnX = oldX;
		int returnY = oldY;

		//Check if new X position is allowed
		if(0 < newX && newXRight < getMapWidth() && !mapCollision(unitTile, new Point(newX, oldY))){
			returnX = newX;
		}

		//Check if new Y position is allowed
		if(0 < newY && newYDown < getMapHeight() && !mapCollision(unitTile, new Point(oldX, newY))){
			returnY = newY;
		}
		return new Point(returnX, returnY);

	}
	
	/**
	 * Checks if there's a collision at the given coordinates
	 * @param objectToPlace A "hitbox" of the object to place at placeToPut
	 * @param placeToPut the place to put objectToPlace at
	 * @return true if there is a collision
	 */
	private boolean mapCollision(Rectangle objectToPlace, Point placeToPut){

        Point northWest = new Point((int)placeToPut.getX() + 1, (int)placeToPut.getY()+1);
        Point northEast = new Point((int)(placeToPut.getX() + objectToPlace.getWidth()-1), (int)placeToPut.getY()+1);
        Point southWest = new Point((int)placeToPut.getX() + 1, (int)(placeToPut.getY() + objectToPlace.getHeight()-1));
        Point southEast = new Point((int)(placeToPut.getX() + objectToPlace.getWidth()-1), (int)(placeToPut.getY() + objectToPlace.getHeight()-1));

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
			movePlayer(player);       
		}
                for (INpc npc : npcs){
                    npc.update(players.get(0).getPosition());
                }
	}	
}
