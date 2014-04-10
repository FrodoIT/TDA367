package scratch.model;

import java.awt.Point;
import java.awt.Rectangle;
/**
 * Logical representation of the Player in the game.
 * @author Anna Nylander
 *
 */
public class Player {
	private int health;
        private int id;
        private int movementSpeed;
	private Rectangle unitTile;
        private IPlayerInput playerInput;
	
	public Player(IPlayerInput playerInput, Rectangle unitTile){
		this.playerInput=playerInput;
		movementSpeed = 2;
                //TODO: Can we rely on clone here? Not certain that the copy will be deep enough
                this.unitTile = new Rectangle((int)unitTile.getX(), (int)unitTile.getY(), (int)unitTile.getWidth(), (int)unitTile.getHeight());
	}
	
	
	public Point calculateMovementPosition(){
		return calculateNewPosition(playerInput.getMoveInput());
	}
	
	
	public Point calculateNewPosition(MoveDirection direction){
			int deltaX;
			int deltaY;
			
			switch(direction){
			case NORTH:
				deltaX=0;
				deltaY=-movementSpeed;
				break;
			case SOUTH:
				deltaX=0;
				deltaY=+movementSpeed;
				break;
			case WEST:
				deltaX=-movementSpeed;
				deltaY=0;
				break;
			case EAST:
				deltaX=+movementSpeed;
				deltaY=0;
				break;
			case NORTHWEST:
				deltaX=-movementSpeed;
				deltaY=-movementSpeed;
				break;
			case NORTHEAST:
				deltaX=+movementSpeed;
				deltaY=-movementSpeed;
				break;
			case SOUTHWEST:
				deltaX=-movementSpeed;
				deltaY=+movementSpeed;
				break;
			case SOUTHEAST:
				deltaX=+movementSpeed;
				deltaY=+movementSpeed;
				break;
			default:
				deltaX = 0;
				deltaY = 0;
			}
			return new Point((int)getPosition().getX()+deltaX, (int)getPosition().getY()+deltaY);
	}
	
	public void takeDamage(int dmg){
		health=health-dmg;
	}
	public int getHealth() {
		return health;
	}
	
	public Point getPosition() {
                //Copy to protect from unintentional modification
		return new Point(unitTile.getLocation());
	}
	

	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	public IPlayerInput getPlayerInput(){
		return playerInput;
	}
	public int getID(){
		return id;
	}
	public Rectangle getUnitTile(){
		return unitTile;
	}
        
	public void setPosition(Point newPosition){
		unitTile.setLocation(new Point(newPosition));
	}
	
}
