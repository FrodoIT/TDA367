package scratch.model;

import scratch.model.weapons.IWeapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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
	public Vector2D getPosition();
	public void setPosition(Vector2D position);
	public Rectangle2D.Double getUnitTile();
	//Called by room to se where the caracter WANTS to go.
	public Vector2D calculateMovementPosition(IRoomData roomData);
        public Rectangle2D.Double getAttackArea();
        public boolean alive();
        public boolean isAttacking();
}
