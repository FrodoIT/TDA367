package model;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a single room and the contents in it.
 * @author Anna Chikki Nylander
 * Revised Ivar Cannonbait Josefsson 2014-03-28
 *
 */
public class Room {
	
	// Map is constructed by a grid of tiles
	private Dimension gridDim;
	// These tiles have the dimension
	private Dimension tileDim;
	
	private Set<IObject> iObjectSet = new TreeSet<IObject>();
	//private Set<Npc> characterSet = new TreeSet<Npc>();
	private Set<Player> playerSet = new TreeSet<Player>();
	private boolean isCompleted = false;
	private Player player;
	
	private int[][] mapRepresentation;
	
	public Room(int[][] mapRepresentation, Dimension gridDim, Dimension tileDim){
		//TODO Player should be added separately with another method.
		this.mapRepresentation = mapRepresentation;
		this.gridDim = gridDim;
		this.tileDim = tileDim;
		player = new Player(new Point((int)(gridDim.getWidth()/2), (int)(gridDim.getHeight()/2)));
	}
	public boolean isCompleted(){
		return isCompleted;
	}
	
	/*
	
	public ICharacter areaUnderAttack(int x, int y){
		//Checks if any character is located at the specified position.
		//TODO nu kan inte spelare skadas eftersom de aldrig kollas.
		for(Npc c: characterSet){
			if(c.getPosition().getX() == x && c.getPosition().getY() == y){
				return c;
			}
		}
		for(Player p: playerSet){
			if(p.getPosition().getX() == x && p.getPosition().getY() == y){
				return p;
			}
		}
		return null; //better to avoid null and use a "null-character" that simply implies that no object was found.
	}
	
	public IObject interaction(int x, int y){
		//check if any object is located at the specified position.
		for(IObject i: iObjectSet){
			if(i.getPosition().getX() == x && i.getPosition().getY()==y){
				return i;
			}
		}
		return null; 
	}
	*/
	
	private void movePlayer(MoveCommand direction){
		Point newPosition = player.calculateNewPosition(direction);
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
				
		//TODO: It is possible to get stuck inside an object, either X or Y coordinate should get preference
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
		//TODO The catch part will trigger when player is on the edge of the map.
		try {
			return mapRepresentation[pixelToGridX(x)][pixelToGridY(y)] != 0;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		

		
	}
	
	/**
	 * 
	 * @return: the total height of the map in pixels
	 */
	private double getMapHeight() {
		return gridDim.getHeight() * tileDim.getHeight();
	}
	
	/**
	 * 
	 * @return: the total width of the map in pixels
	 */
	private double getMapWidth() {
		return gridDim.getWidth() * tileDim.getWidth();
	}
	
	
	/**
	 * Calculates what tileID a pixel belongs as an X coordinate
	 * @param pixel to calculate from
	 * @return the tileID as an integer
	 */
	private int pixelToGridX(int pixel) {
		return (int)(pixel/tileDim.getWidth());
	}
	
	/**
	 * Calculates what tileID a pixel belongs as an Y coordinate
	 * @param pixel to calculate from
	 * @return the tileID as an integer
	 */
	private int pixelToGridY(int pixel) {
		return (int)(pixel/tileDim.getHeight());
	}
	
	public void update (PlayerInput input){
		movePlayer(input.getMoveInput());
	}
	
	public Point getPlayerPosition() {
		return player.getPosition();
	}
	
}
