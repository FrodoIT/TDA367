package model;

import java.awt.Point;
import java.awt.Rectangle;
/**
 * Logical representation of the Player in the game.
 * @author Anna Nylander
 *
 */
public class Player {
	private Point position;
	private int health;
	private Rectangle hitBox;
	private int id;
	private PlayerInput playerInput;
	private int movementSpeed=10;
	
	public Player(PlayerInput playerInput){
		this.playerInput=playerInput;
		position=new Point(0,0);
		movementSpeed = 2;
		health = 30;
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
		return playerInput;
	}
	public int getID(){
		return id;
	}
	public Rectangle getHitBox(){
		return hitBox;
	}
	
	//Setter
	public void setPosition(Point newPosition){
		position.setLocation(newPosition);
	}
	
}
