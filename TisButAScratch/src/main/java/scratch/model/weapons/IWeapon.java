package scratch.model.weapons;

import java.awt.geom.Rectangle2D;

/**
 * @author Alma Ottedag
 */
public interface IWeapon {	
	public int getDamage();
	public int getRange();
        public Rectangle2D.Double getAttackArea();
}
