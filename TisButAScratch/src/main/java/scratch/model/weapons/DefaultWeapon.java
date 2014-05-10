package scratch.model.weapons;

import com.google.inject.Inject;

import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	Runnable runnable;

	private ScheduledExecutorService schdoodle = Executors.newScheduledThreadPool(1);

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
        if (!cooledDown)
            return;
		cooledDown =false;
		schdoodle.schedule(runnable,
				attackInterval,
				TimeUnit.MILLISECONDS
		);
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
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null)
            return false;
        if(o instanceof DefaultWeapon){
            DefaultWeapon test = (DefaultWeapon) o;
            return (this.getDamage() == test.getDamage() &&
                    this.getAttackArea() == test.getAttackArea() &&
                    this.getAttackInterval() == test.getAttackInterval() &&
                    this.getRange() == test.getRange());
        }
        return false;
    }
}
