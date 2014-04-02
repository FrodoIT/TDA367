package model;

import java.awt.Point;
import java.awt.Rectangle;

public class Player {
	private Point position;
	private int Health;
	private Rectangle hitBox;
	private int id;
	private PlayerInput playerinput;
	private int movementSpeed;
	
	public Player(PlayerInput playerinput){
		this.playerinput=playerinput;
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
		//TODO implement the method
	}
	public void interract(){
		//TODO implemnt the method
	}
	public void attack(){
		//TODO implement the mehtod
	}
	
	public Point getPosition() {
		return new Point(position);
	}
}
