package model;

import java.awt.Point;

/**
 * A class that represents a character not controlled by the player
 * @author Ivar
 *
 */
public class Npc {

	private Point position;
	private IWeapon weapon;
	private int health;
	private int movementSpeed;

	
	/**
	 * Should not be used, default parameters for all values.
	 * position = 10, 10
	 * weapon = Knuckles
	 * health = 10
	 * moveSpeed = 1
	 */
	public Npc() {
		this (new Point(320, 240), new Knuckles(), 10, 1);
	}
	
	/**
	 * Initialises NPC with default values
	 * @param pos of the NPC in coordinates from top left corner
	 * weapon = Knuckles
	 * health = 10
	 * moveSpeed = 1
	 */
	public Npc(Point pos) {
		this (pos, new Knuckles(), 10, 1);
	}
	
	/**
	 * Initialises NPC with parameter values
	 * @param pos of the NPC
	 * @param weapon that the NPC should have
	 * health = 10
	 * moveSpeed = 1
	 */
	public Npc(Point pos, IWeapon weapon) {
		this (pos, weapon, 10, 1);
	}
	
	public Npc(Point pos, IWeapon weapon, int health) {
		this (pos, weapon, health, 1);
	}
	
	public Npc(Point pos, IWeapon weapon, int health, int moveSpeed){
		position = new Point((int)pos.getX(), (int)pos.getY());
		this.weapon = weapon;
		this.health = health;
		this.movementSpeed = moveSpeed;
	}
	
	/**
	 * Call to get a Point with the position of the NPC
	 * @return a copy of the pointer holding the position
	 */
	public Point getPosition() {
		return new Point((int)position.getX(), (int)position.getY());
	}
	
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	public int getDamage() {
		return weapon.getDamage();
	}
	
	public int getRange() {
		return weapon.getRange();
	}
	
	
}
