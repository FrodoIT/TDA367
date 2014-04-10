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
	public void takeDamage(int dmg);
	public Point getPosition();
	public int getDamage();
	public int getMovementSpeed();
	public IWeapon getWeapon();
	public void setPosition(Point position);
	//Called by room to se where the caracter WANTS to go.
	public Point calculateMovementPosition();
}
