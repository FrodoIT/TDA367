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
	private Dimension dimension;
	private Set<IObject> iObjectSet = new TreeSet<IObject>();
	//private Set<Npc> characterSet = new TreeSet<Npc>();
	private Set<Player> playerSet = new TreeSet<Player>();
	private boolean isCompleted = false;
	private Player player;
	
	public Room(){
		//TODO Player should be added separately with another method.
		dimension = new Dimension(640, 480);
		player = new Player(new Point((int)(dimension.getWidth()/2), (int)(dimension.getHeight()/2)));
	}
	public boolean isCompleted(){
		return isCompleted;
	}
	
	/*
	public boolean validatePosition(int x, int y){
		//check if the position is ok for a player to move to.
		if(x<0 || y<0 || x>dimension.width || y>dimension.height){
			return false;
		}
		for(Npc c: characterSet){
			if(c.getPosition().getX() == x && c.getPosition().getY() == y){
				return false;
			}
		}
		return true;
				
	}
	
	
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
		if (allowedPosition(player.calculateNewPosition(direction))){
			player.move(direction);
		}
	}
	
	/**
	 * Called to determine if a position is allowed in the room.
	 * @param point the position we want to check
	 * @return true if position is allowed, false otherwise
	 */
	private boolean allowedPosition(Point point){
		if (point.getX()<0 || point.getY()<0 || dimension.getWidth() < point.getX() || dimension.getHeight() < point.getY()){
			return false;
		}
		return true;
	}
	
	
	public void update (PlayerInput input){
		movePlayer(input.getMoveInput());
	}
	
	public Point getPlayerPosition() {
		return player.getPosition();
	}
	
}
