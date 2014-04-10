package scratch.model.weapons;
/**
 * The weapon Knuckles:
 * The default starting-weapon for all characters with the following stats:
 * Damage = 2
 * Range = 1
 * @author Alma Ottedag
 */
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
