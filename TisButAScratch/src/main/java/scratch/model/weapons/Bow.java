package scratch.model.weapons;

import java.awt.geom.Rectangle2D;

/**
 * The weapon Bow:
 * A long-ranged weapon with the following stats:
 * Damage = 4
 * Range = 10
 * @author Alma Ottedag
 */
public class Bow implements IWeapon{

	private final int damage;
	private final int range;
        private final Rectangle2D.Double attackArea;

	public Bow(){
		damage = 4;
		range = 10;
                attackArea = new Rectangle2D.Double(0, 0, 3, 3);
	}
	public Bow(int damage, int range){
		this.damage = damage;
		this.range = range;
                attackArea = new Rectangle2D.Double(0, 0, 3, 3);
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getRange() {
		return range;
	}
        
        @Override
        public Rectangle2D.Double getAttackArea(){
            return (Rectangle2D.Double)attackArea.clone();
        }

}

