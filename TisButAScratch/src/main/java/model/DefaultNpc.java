package model;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * A class that represents a character not controlled by the player
 * @author Ivar
 *
 */
public class DefaultNpc implements INpc{

	private Rectangle unitTile;
	private IWeapon weapon;
	private int health;
	private int movementSpeed;


	public DefaultNpc(Rectangle unitTile, IWeapon weapon, int health, int moveSpeed){
		this.unitTile = new Rectangle((int)unitTile.getX(), (int)unitTile.getY(), (int)unitTile.getWidth(), (int)unitTile.getHeight());
		this.weapon = weapon;
		this.health = health;
		this.movementSpeed = moveSpeed;
	}
        
        public void update(Point playerPos){
            move(playerPos);
        }
        
        public void move(Point playerPos){
            //TODO: Movement logic
        }
	
	//Getters
	
	/**
	 * Call to get a Point with the position of the NPC
	 * @return a copy of the pointer holding the position
	 */
	public Point getPosition() {
		return unitTile.getLocation();
	}
	
	public IWeapon getWeapon() {
		return weapon;
	}
	
	public int getDamage() {
		return weapon.getDamage();
	}
	
	public int getRange() {
		return weapon.getRange();
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	
	
	//Setters
	public void setPosition(Point position) {
		unitTile.setLocation(position);
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

    @Override
    public int getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

	
}
