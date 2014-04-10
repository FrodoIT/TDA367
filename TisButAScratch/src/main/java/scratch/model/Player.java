package scratch.model;

import scratch.model.weapons.IWeapon;

import java.awt.Point;
import java.awt.Rectangle;
/**
 * Logical representation of the Player in the game.
 * @author Anna Nylander
 *
 */
public class Player implements ICharacter {
	private int health;
	private final int id;
	private int movementSpeed;
	private Rectangle unitTile;
	private IPlayerInput playerInput;

	public Player(IPlayerInput playerInput, Rectangle unitTile, int id){
		this.playerInput=playerInput;
		movementSpeed = 2;
		this.id = id;
		//TODO: Can we rely on clone here? Not certain that the copy will be deep enough
		this.unitTile = new Rectangle((int)unitTile.getX(), (int)unitTile.getY(), (int)unitTile.getWidth(), (int)unitTile.getHeight());
	}

	@Override
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

	@Override
	public void takeDamage(int dmg){
		health=health-dmg;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public Point getPosition() {
		//Copy to protect from unintentional modification
		return new Point(unitTile.getLocation());
	}

	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public int getMovementSpeed() {
		return movementSpeed;
	}

	@Override
	public IWeapon getWeapon() {
		return null;
	}

	public IPlayerInput getPlayerInput(){
		return playerInput;
	}

	public int getID(){
		return id;
	}

	@Override
	public Rectangle getUnitTile(){
		return unitTile;
	}

	@Override
	public void setPosition(Point newPosition){
		unitTile.setLocation(new Point(newPosition));
	}

}
