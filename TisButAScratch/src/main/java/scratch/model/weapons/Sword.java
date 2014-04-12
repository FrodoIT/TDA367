package scratch.model.weapons;

import java.awt.geom.Rectangle2D;

/**
 * The weapon Sword:
 * A short-ranged weapon with the following stats:
 * Damage = 5
 * Range = 1
 * @author Alma Ottedag
 */
public class Sword implements IWeapon{
	private final int damage;
	private final int range;
        private final Rectangle2D.Double attackArea;
	
	public Sword(){
		damage = 5;
		range = 1;
                attackArea = new Rectangle2D.Double(0, 0, 3, 3);
	}
	public Sword(int damage, int range){
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
