package scratch.model.weapons;

import java.awt.geom.Rectangle2D;

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
        private final Rectangle2D.Double attackArea;

	public Knuckles(){
		damage = 2;
		range = 1;
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
