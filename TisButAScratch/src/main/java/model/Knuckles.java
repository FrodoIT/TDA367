package model;

public class Knuckles implements IWeapon {
	private final int damage;
	private final int range;

	public Knuckles(){
		damage = 2;
		range = 1;
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
