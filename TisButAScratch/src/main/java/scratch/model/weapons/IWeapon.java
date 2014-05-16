package scratch.model.weapons;

import com.esotericsoftware.kryo.KryoSerializable;
import com.google.inject.ImplementedBy;

import java.awt.geom.Rectangle2D;

/**
 * @author Alma Ottedag
 */
@ImplementedBy(DefaultWeapon.class)
public interface IWeapon extends KryoSerializable {
    int getDamage();
    int getRange();
    Rectangle2D.Double getAttackArea();
    void startCooldown();
    boolean hasCooledDown();

}
