package model;
import java.awt.Point;

/**
 * @author Alma Ottedag
 * revised 2014-03-27 Ivar Josefsson
 */
public class Player implements ICharacter{

	private int health;
	private Point position;
	private IWeapon weapon;
	private final int movementSpeed;

    /**
     * Default constructor for Player.
     * Default health is currently 30pts.
     * Default position is at x = 10, y = 10.
     */
	public Player(){
		this(new Point(320, 240));	
	}

    /**
     * Constructor for Player with a specific starting position.
     * @param position The position of the character given as a Point.
     */
	public Player(Point position){
		movementSpeed = 2;
		health = 30;
		weapon = new Knuckles();
		this.position = position;
	}

	public void interact(){
		//TODO.
	}
	
	@Override
	public int getHealth() {
		return health;
	}
	
	public int getRange() {
		return weapon.getRange();
	}
	
	@Override
	public Point getPosition() {
		return position;
	}
	@Override
	public int getDamage() {
		return weapon.getDamage();
	}
	
	@Override
	public IWeapon getWeapon(){
		return weapon;
	}
	
	/**
	 * Calculates the new coordinates the with movement in the defined direction.
	 * @param direction determines the coordinate changes.
	 * @return A point containing the position we would get when moving in the defined direction
	 */
	public Point calculateNewPosition(MoveDirection direction) {
		int xChange;
		int yChange;
		switch(direction){
		case NORTH: 
			xChange = 0;
			yChange = -movementSpeed;
			break;
		case NORTHWEST:
			xChange = -movementSpeed;
			yChange = -movementSpeed;
			break;
		case WEST:
			xChange = -movementSpeed;
			yChange = 0;
			break;
		case SOUTHWEST:
			xChange = -movementSpeed;
			yChange = movementSpeed;
			break;
		case SOUTH:
			xChange = 0;
			yChange = movementSpeed;
			break;
		case SOUTHEAST:
			xChange = movementSpeed;
			yChange = movementSpeed;
			break;
		case EAST:
			xChange = movementSpeed;
			yChange = 0;
			break;
		case NORTHEAST:
			xChange = movementSpeed;
			yChange = -movementSpeed;
			break;
		default:
			xChange = 0;
			yChange = 0;
		}
		return new Point((int)position.getX()+xChange, (int)position.getY()+yChange);
	}
	

	//TODO Should be removed and setPosition should be used instead
	public void move(MoveDirection direction) {
		position = calculateNewPosition(direction);
	}
	
	/**
	 * Makes a copy of the supplied point and set the position of the Player to those coordinates
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = new Point((int)position.getX(), (int)position.getY());
	}
	
	
	@Override
	public int getMovementSpeed() {
		return movementSpeed;
	}


}
