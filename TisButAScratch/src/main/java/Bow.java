
public class Bow implements IWeapon{

	private final int damage;
	private final int range;

	public Bow(){
		damage = 4;
		range = 10;
	}
	public Bow(int damage, int range){
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

