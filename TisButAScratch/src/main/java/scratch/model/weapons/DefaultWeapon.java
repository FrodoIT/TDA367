package scratch.model.weapons;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.inject.Inject;
import scratch.utils.Cooldown;

import java.awt.geom.Rectangle2D;

/**
 * The weapon DefaultWeapon: The default starting-weapon for all characters with
 * the following stats: Damage = 2 Range = 1
 *
 * @author Alma Ottedag
 */
public final class DefaultWeapon implements IWeapon {

    @Inject
    private int damage;
    private int range;
    private Rectangle2D.Double attackArea;
    //Minimum time between attacks in milliseconds
    private int attackInterval;
    private boolean cooledDown = true;
    private final Runnable runnable;

    public DefaultWeapon() {
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
     * Tell the weapon to execute an performAttack.
     *
     * @return true if performAttack was done
     */
    @Override
    public boolean hasCooledDown() {
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
    public Rectangle2D.Double getAttackArea() {
        return (Rectangle2D.Double) attackArea.clone();
    }

    public int getAttackInterval() {
        return attackInterval;
    }

	public boolean equals(Object o) {
		if (this == o) {return true;}
		if (o == null || getClass() != o.getClass()) {return false;}

		final DefaultWeapon that = (DefaultWeapon) o;


        final boolean attackAreaEquals = attackArea == null ? attackArea == that.attackArea : attackArea.equals(that.attackArea);

        return attackAreaEquals
                && attackInterval == that.attackInterval
                && damage == that.damage
                && range == that.range;
    }

    @Override
    public int hashCode() {
        return 31 * damage +
                31 * range +
                31 * (attackArea == null ? 0: attackArea.hashCode()) +
                31* attackInterval;

        /*
         int result = damage;
         result = 31 * result + range;
         result = 31 * result + (attackArea != null ? attackArea.hashCode() : 0);
         result = 31 * result + attackInterval;
         return 31 * result + (runnable != null ? runnable.hashCode() : 0);
         */
    }

    @Override
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, damage);
        kryo.writeObject(output, range);
        kryo.writeObject(output, attackArea);
        kryo.writeObject(output, attackInterval);

    }

    @Override
    public void read(Kryo kryo, Input input) {
        damage = kryo.readObject(input, Integer.class);
        range = kryo.readObject(input, Integer.class);
        attackArea = kryo.readObject(input, Rectangle2D.Double.class);
        attackInterval = kryo.readObject(input, Integer.class);
    }

}
