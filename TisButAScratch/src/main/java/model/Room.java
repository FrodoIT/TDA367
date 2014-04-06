package model;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a single room and the contents in it.
 * @author Ivar Josefsson
 *
 */
public class Room {
	
	private List<Player> players;
	private IMap map;
	
	public Room(IMap collisionMap){
		//TODO Player should be added separately with another method.
	
		this.map = collisionMap;
		players = new ArrayList();
	}
	
	/**
	 * Adds the specified player from the Room
	 * @param player
	 */
	public void enterRoom(Player player){
		players.add(player);
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
		player.setPosition(allowedPosition(player.getPosition(), newPosition));
	}
	
	
	/**
	 * Called to determine the best allowed position
	 * @param fromPosition the position we start from
	 * @param toPosition the position we want to end at
	 * @return the best allowed position
	 */
	private Point allowedPosition(Point fromPosition, Point toPosition){
		int oldX = (int)fromPosition.getX();
		int oldY = (int)fromPosition.getY();
		int newX = (int)toPosition.getX();
		int newY = (int)toPosition.getY();
		int returnX = oldX;
		int returnY = oldY;
				
		if (0 <= newX && newX <= getMapWidth() && !mapCollision(newX, oldY)){
			returnX = newX;
		}
		if (0 <= newY && newY <= getMapHeight() && !mapCollision(oldX, newY)){
			//TODO Causes ArrayIndexOutOfBoundsException in mapCollision trying to index a mapTile that does not exist
			if (!(!mapCollision(newX, oldY) && !mapCollision(oldX, newY) && mapCollision(newX,newY)));{
				returnY = newY;
			}
		}
		return new Point(returnX, returnY);
		
	}
	
	/**
	 * Checks if there's a collision at the given coordinates
	 * @param x is the coordinate on the X axis
	 * @param y is the coordinate on the Y axis
	 * @return true if there is a collision
	 */
	private boolean mapCollision(int x, int y){
                return map.collisionAt(new Point(x,y));
	}
	
	/**
	 * 
	 * @return: the total height of the map in pixels
	 */
	private double getMapHeight() {
		return map.getHeight();
	}
	
	/**
	 * 
	 * @return: the total width of the map in pixels
	 */
	private double getMapWidth() {
		return map.getWidth();
	}
	
	public void update(){
		for (Player player:players){
			movePlayer(player);
		}
	}	
}
