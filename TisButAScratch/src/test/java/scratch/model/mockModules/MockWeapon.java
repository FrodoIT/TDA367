package scratch.model.mockModules;

import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-05.
 */
//NOT yet implemented, will collect code from real implementation
public class MockWeapon implements IWeapon{
	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public int getRange() {
		return 0;
	}

	@Override
	public Rectangle2D.Double getAttackArea() {
		return null;
	}

	@Override
	public void attack() {

	}

	@Override
	public boolean isCooledDown() {
		return false;
	}
}
