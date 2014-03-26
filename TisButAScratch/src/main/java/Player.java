import java.awt.Point;


public class Player implements ICharacter{

	private int health;
	private int damage;
	private Point position;
	private IWeapon weapon;
	private final int movementSpeed;

	public Player(){
		movementSpeed = 5;
		health = 30;
		weapon = new Knuckles();
		damage = getDamage();
		position = new Point(10,10);	
	}

	public Player(Point position){
		movementSpeed = 5;
		health = 30;
		weapon = new Knuckles();
		damage = getDamage();
		this.position = position;
	}

	@Override
	public int getHealth() {
		return health;
	}
	@Override
	public Point getPosition() {
		return position;
	}
	@Override
	public int getDamage() {
		return damage;
	}
	
	public IWeapon getWeapon(){
		return weapon;
	}
	
	@Override
	public void move(Direction direction) {
		//TODO: add method for diagonal movements.
		switch(direction){
		case NORTH: 
			position.move(0, -movementSpeed);
		case NORTHWEST:
			position.move(2, -movementSpeed);
		case WEST:
			position.move(-movementSpeed, 0);
		case SOUTHWEST:
			position.move(-movementSpeed, 2);
		case SOUTH:
			position.move(0, movementSpeed);
		case SOUTHEAST:
			position.move(2, movementSpeed);
		case EAST:
			position.move(movementSpeed, 0);
		case NORTHEAST:
			position.move(-movementSpeed, 2);
		}
	}
	@Override
	public int getMovementSpeed() {
		return movementSpeed;
	}


}
