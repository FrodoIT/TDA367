package scratch.model.mockModules;

import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;

/**
 * Created by pippin on 5/15/14.
 */
public class MockIWeapon implements IWeapon{
    @Override
    public int getDamage() {
        return 4;
    }

    @Override
    public int getRange() {
        return 1;
    }

    @Override
    public Rectangle2D.Double getAttackArea() {
        return new Rectangle2D.Double(0,0,100,100);
    }

    @Override
    public void startCooldown() {

    }

    @Override
    public boolean hasCooledDown() {
        return true;
    }
}
