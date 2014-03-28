package model;
import java.awt.Point;

/**
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
	public void move(Direction direction);
}
