import java.awt.Dimension;
import java.util.Set;
import java.util.TreeSet;


public class Room {
	private Dimension dimension = new Dimension();
	Set<IObjects> iObjectList = new TreeSet<IObjects>();
	Set<Character> characterList = new TreeSet<Character>();
	Set<Player> playerList = new TreeSet<Player>();
	
	public Room(){
		
	}
	
	public boolean isActive(){
		return !playerList.isEmpty();
	}
	
	
	
	
}
