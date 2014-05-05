package scratch.model.weapons;

import com.google.inject.ImplementedBy;

import java.awt.geom.Rectangle2D;

/**
 * @author Alma Ottedag
 */
@ImplementedBy(DefaultWeapon.class)
public interface IWeapon {	
	public int getDamage();
	public int getRange();
        public Rectangle2D.Double getAttackArea();
        public void attack();
		public boolean isCooledDown();
}
