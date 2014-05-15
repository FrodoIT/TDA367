package scratch.model.weapons;

import com.google.inject.Inject;
import scratch.utils.Cooldown;

import java.awt.geom.Rectangle2D;

/**
 * The weapon DefaultWeapon:
 The default starting-weapon for all characters with the following stats:
 Damage = 2
 Range = 1
 * @author Alma Ottedag
 */

public final class DefaultWeapon implements IWeapon {
	@Inject
    private final int damage;
    private final int range;
    private final Rectangle2D.Double attackArea;
    //Minimum time between attacks in milliseconds
    private final int attackInterval;
	private boolean cooledDown = true;
	private static Runnable runnable;

    public DefaultWeapon(){
        damage = 2;
        range = 1;
        attackArea = new Rectangle2D.Double(0, 0, 32, 32);
        attackInterval = 400;



	      runnable = new Runnable() {
		    public void run() {
                cooledDown = true;
		    }
	    };
     }

    @Override
	public void startCooldown() {
        if (cooledDown) {
	        cooledDown = false;
	        Cooldown.cooldown(attackInterval, runnable);
        }
	}

	/**
	 * Tell the weapon to execute an attack
	 * @return true if attack was done
	 */
	@Override
	public boolean hasCooledDown(){
		return cooledDown;
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

    public int getAttackInterval() {
		return attackInterval;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DefaultWeapon that = (DefaultWeapon) o;

		if (attackInterval != that.attackInterval) return false;
		if (cooledDown != that.cooledDown) return false;
		if (damage != that.damage) return false;
		if (range != that.range) return false;
		if (attackArea != null ? !attackArea.equals(that.attackArea) : that.attackArea != null) return false;

		return true;
	}

	@Override
    public int hashCode() {
        int result = damage;
        result = 31 * result + range;
        result = 31 * result + (attackArea != null ? attackArea.hashCode() : 0);
        result = 31 * result + attackInterval;
        result = 31 * result + (cooledDown ? 1 : 0);
        return 31 * result + (runnable != null ? runnable.hashCode() : 0);
    }
}
