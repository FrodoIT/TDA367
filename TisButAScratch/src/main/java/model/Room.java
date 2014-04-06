package model;
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
		player.setPosition(allowedPosition(player.getUnitTile(), newPosition));
	}
	
	
	/**
	 * Called to determine the best allowed position
	 * @param unitTile the position we start from
	 * @param toPosition the position we want to end at
	 * @return the best allowed position
	 */
	private Point allowedPosition(Rectangle unitTile, Point toPosition){

		int oldX = (int)unitTile.getX();
		int oldY = (int)unitTile.getY();
		int newX = (int)toPosition.getX();
		int newXRight = (int)toPosition.getX() + (int)unitTile.getWidth();
		int newY = (int)toPosition.getY();
		int newYDown = (int)toPosition.getY() + (int)unitTile.getHeight();
		int returnX = oldX;
		int returnY = oldY;
		
		//Check if both new X positions are allowed
		if(0 < newX && newXRight < getMapWidth()){
			returnX = newX;
		}
			//Set new X position
		
		//Check if both new Y position are allowed
		if(0 < newY && newYDown < getMapHeight()){
			returnY = newY;
				}
			//Check that special case no present where not both allowed
				//Set new Y position
		
		
		
		//TODO: Make the horizontal outer map restriction a denied new position.(Currently causes IndexOutOfBoundsException).		
		
		return new Point(returnX, returnY);
		
	}
	
	/**
	 * Checks if there's a collision at the given coordinates
	 * @param x is the coordinate on the X axis
	 * @param y is the coordinate on the Y axis
	 * @return true if there is a collision
	 */
	private boolean mapCollision(int x, int y){
                return map.isColliding(new Point(x,y));
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
	}	
}
