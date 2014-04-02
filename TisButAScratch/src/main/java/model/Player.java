package model;

import java.awt.Point;
import java.awt.Rectangle;

public class Player {
	private Point position;
	private int health;
	private Rectangle hitBox;
	private int id;
	private PlayerInput playerinput;
	private int movementSpeed=10;
	
	public Player(PlayerInput playerinput){
		this.playerinput=playerinput;
		position=new Point(0,0);
		movementSpeed = 2;
		health = 30;
	}
	
	public void move(MoveDirection direction){
		position=calculateNewPosition(direction);
	}
	public Point calculateNewPosition(MoveDirection direction){
			int deltaX;
			int deltaY;
			
			switch(direction){
			case NORTH:
				deltaX=0;
				deltaY=-movementSpeed;
			case SOUTH:
				deltaX=0;
				deltaY=+movementSpeed;				
			case WEST:
				deltaX=-movementSpeed;
				deltaY=0;
			case EAST:
				deltaX=+movementSpeed;
				deltaY=0;
			case NORTHWEST:
				deltaX=-movementSpeed;
				deltaY=-movementSpeed;
			case NORTHEAST:
				deltaX=+movementSpeed;
				deltaY=-movementSpeed;
			case SOUTHWEST:
				deltaX=-movementSpeed;
				deltaY=+movementSpeed;
			case SOUTHEAST:
				deltaX=+movementSpeed;
				deltaY=+movementSpeed;

			default:
				deltaX = 0;
				deltaY = 0;
			}
			return new Point((int)position.getX()+deltaX, (int)position.getY()+deltaY);
	}
	
	public void takeDamage(int dmg){
		health=health-dmg;
	}
	public int getHealth() {
		return health;
	}
	
	public Point getPosition() {
		return new Point(position);
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	public PlayerInput getPlayerInput(){
		return playerinput;
	}
	
}
