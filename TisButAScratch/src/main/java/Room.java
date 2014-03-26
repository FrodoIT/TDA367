import java.awt.Dimension;
import java.util.Set;
import java.util.TreeSet;


public class Room {
	private Dimension dimension = new Dimension();
	private Set<IObject> iObjectSet = new TreeSet<IObject>();
	private Set<Character> characterSet = new TreeSet<Character>();
	private Set<Player> playerSet = new TreeSet<Player>();
	private boolean isCompleted = false;
	
	public Room(){
		
	}
	public boolean isCompleted(){
		return isCompleted;
	}
	
	public boolean validatePosition(int x, int y){
		//check if the position is ok for a player to move to.
		if(x<0 || y<0 || x>dimension.width || y>dimension.height){
			return false;
		}
		for(Character c: characterSet){
			if(c.getPosition().getX() == x && c.getPosition().getY() == y){
				return false;
			}
		}
		return true;
				
	}
	
	public ICharacter areaUnderAttack(int x, int y){
		//Checks if any character is located at the specified position.
		//TODO nu kan inte spelare skadas eftersom de aldrig kollas.
		for(Character c: characterSet){
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
	
	
	
	
}
