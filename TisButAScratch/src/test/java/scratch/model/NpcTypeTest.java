package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import scratch.model.mockModules.MockModule;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-02.
 */
public class NpcTypeTest extends TestCase {
	private NpcType npcType;
	private INPCMove NPCMove;
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new MockModule());
		IWeapon weapon= injector.getInstance(IWeapon.class);
		INPCMove NPCMove = injector.getInstance(INPCMove.class);
		npcType = new NpcType(new Rectangle2D.Double(32,32,32,32), weapon, 1, 1, "/res/playerSprite.tmx", 0, NPCMove);

	}

	public void testIsAlive() throws Exception {

	}

	public void testTakeDamage() throws Exception {

	}

	public void testSetPosition() throws Exception {

	}

	public void testIsAttacking() throws Exception {

	}

	public void testCalculateMovementPosition() throws Exception {

	}

	public void testCreateCopy() throws Exception {

	}

	public void testGetAttackArea() throws Exception {

	}

	public void testAttack() throws Exception {

	}
}
