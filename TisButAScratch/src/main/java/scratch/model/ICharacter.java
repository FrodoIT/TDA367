package scratch.model;

import scratch.model.weapons.IWeapon;

import java.awt.*;

/**
 * The interface for all Characters. Every character has a given 
 * health, position, damage, movement speed and weapon. 
 * @author Alma Ottedag
 * revised 2014-03-27 by Ivar Josefsson
 */

public interface ICharacter {
	public int getHealth();
	public int getMovementSpeed();
	public void takeDamage(int dmg);
	public int getDamage();
	public IWeapon getWeapon();
	public Point getPosition();
	public void setPosition(Point position);
	public Rectangle getUnitTile();
	//Called by room to se where the caracter WANTS to go.
	public Point calculateMovementPosition();
}
