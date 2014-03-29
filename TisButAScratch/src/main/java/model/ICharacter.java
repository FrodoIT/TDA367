package model;
import java.awt.Point;

/**
 * The interface for all Characters. Every character has a given 
 * health, position, damage, movement speed and weapon. 
 * @author Alma Ottedag
 * revised 2014-03-27 by Ivar Josefsson
 */

public interface ICharacter {
	public int getHealth();
	public Point getPosition();
	public int getDamage();
	public int getMovementSpeed();
	public IWeapon getWeapon();
	
	

    /**
     * Moves the character once in the given direction.
     * Takes into consideration movement speed.
     * @param direction Direction of the movement.
     */
	public void move(MoveCommand direction);
}
