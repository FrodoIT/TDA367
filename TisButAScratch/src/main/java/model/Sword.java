package model;
/**
 * @author Alma Ottedag
 */
public class Sword implements IWeapon{
	private final int damage;
	private final int range;
	
	public Sword(){
		damage = 5;
		range = 1;
	}
	public Sword(int damage, int range){
		this.damage = damage;
		this.range = range;
	}
	
	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getRange() {
		return range;
	}
	
}
