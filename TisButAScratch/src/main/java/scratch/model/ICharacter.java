package scratch.model;
import scratch.model.weapons.IWeapon;
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
	public void setPosition(Point position);
}
