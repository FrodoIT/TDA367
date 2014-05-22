package scratch.model.weapons;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.inject.Inject;
import org.simpleframework.xml.Element;
import scratch.utils.Cooldown;

import java.awt.geom.Rectangle2D;

/**
 * The weapon Weapon: The default starting-weapon for all characters with the
 * following stats: Damage = 2 Range = 1
 *
 * @author Alma Ottedag
 */
public final class Weapon implements KryoSerializable{

    @Inject
    @Element
    private int damage;
    @Element
    private int range;
    @Element(type = Rectangle2D.class, required = false)
    private Rectangle2D.Double attackArea = new Rectangle2D.Double(0, 0, 32, 32);
    //Minimum time between attacks in milliseconds
    @Element
    private int attackInterval;
    @Element(required = false)
    private boolean cooledDown = true;
    private final Runnable runnable = new Runnable() {
        public void run() {
            cooledDown = true;
        }
    };

    public Weapon() {
        damage = 2;
        range = 1;
        attackInterval = 400;
    }

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

    public boolean hasCooledDown() {
        return cooledDown;
    }


    public int getDamage() {
        return damage;
    }


    public int getRange() {
        return range;
    }


    public Rectangle2D.Double getAttackArea() {
        return (Rectangle2D.Double) attackArea.clone();
    }

    public int getAttackInterval() {
        return attackInterval;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Weapon that = (Weapon) o;

        final boolean attackAreaEquals = attackArea == null ? attackArea == that.attackArea : attackArea.equals(that.attackArea);

        return attackAreaEquals
                && attackInterval == that.attackInterval
                && damage == that.damage
                && range == that.range;
    }


    public int hashCode() {
        return 31 * damage
                + 31 * range
                + 31 * (attackArea == null ? 0 : attackArea.hashCode())
                + 31 * attackInterval;

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
