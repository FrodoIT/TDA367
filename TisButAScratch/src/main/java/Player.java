import java.awt.Point;


public class Player implements ICharacter{

	private int health;
	private Point position;
	private IWeapon weapon;
	private final int movementSpeed;
	private Room currentRoom;

	public Player(){
		movementSpeed = 5;
		health = 30;
		weapon = new Knuckles();
		position = new Point(10,10);	
	}

	public Player(Point position){
		movementSpeed = 5;
		health = 30;
		weapon = new Knuckles();
		this.position = position;
		currentRoom = new Room();
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
	
	public IWeapon getWeapon(){
		return weapon;
	}
	
	@Override
	public void move(Direction direction) {
		//TODO: add method for diagonal movements.
		switch(direction){
		case NORTH: 
			position.move((int) position.getX(), (int) position.getY()-movementSpeed);
			break;
		case NORTHWEST:
			position.move((int) position.getX()+2, (int) position.getY()-movementSpeed);
			break;
		case WEST:
			position.move((int) position.getX()-movementSpeed, (int) position.getY());
			break;
		case SOUTHWEST:
			position.move((int) position.getX()-movementSpeed, (int) position.getY()+2);
			break;
		case SOUTH:
			position.move((int) position.getX(), (int) position.getY()+movementSpeed);
			break;
		case SOUTHEAST:
			position.move((int) position.getX()+2, (int) position.getY()+movementSpeed);
			break;
		case EAST:
			position.move((int) position.getX()+movementSpeed, (int) position.getY());
			break;
		case NORTHEAST:
			position.move((int) position.getX()-movementSpeed, (int) position.getY()+2);
			break;
		}
	}
	@Override
	public int getMovementSpeed() {
		return movementSpeed;
	}


}
