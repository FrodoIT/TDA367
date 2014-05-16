package scratch.model.mockmodules;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
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
    public void startCooldown() {

    }

    @Override
    public boolean hasCooledDown() {
        return false;
    }

    @Override
    public void write(Kryo kryo, Output output) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void read(Kryo kryo, Input input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
