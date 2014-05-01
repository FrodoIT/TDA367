package scratch.model.weapons;

import java.awt.geom.Rectangle2D;
import java.util.concurrent.*;

/**
 * The weapon DefaultWeapon:
 The default starting-weapon for all characters with the following stats:
 Damage = 2
 Range = 1
 * @author Alma Ottedag
 */
public final class DefaultWeapon implements IWeapon {

    private final int damage;
    private final int range;
    private final Rectangle2D.Double attackArea;
    //Minimum time between attacks in milliseconds
    private final int attackInterval;
	private boolean hasCooledDown=true;

	private ScheduledExecutorService schdoodle = Executors.newScheduledThreadPool(1);

    public DefaultWeapon(){
        damage = 2;
        range = 1;
        attackArea = new Rectangle2D.Double(0, 0, 32, 32);
        attackInterval = 400;
    }
	public void cooldown() {
		hasCooledDown=false;
		schdoodle.schedule(new Runnable() {
			                   @Override
			                   public void run() {
				                   hasCooledDown = true;
			                   }
		                   },
				attackInterval,
				TimeUnit.MILLISECONDS
		);
	}

	/**
	 * Tell the weapon to execute an attack
	 * @return true if attack was done
	 */
	@Override
	public void attack(){
		if(hasCooledDown) {
			cooldown();
		}
	}
	public boolean isCooledDown(){
		return hasCooledDown;
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
