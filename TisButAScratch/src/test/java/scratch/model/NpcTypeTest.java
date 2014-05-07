package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import scratch.model.mockModules.MockModule;
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

	public void testTakeDamage() throws Exception {
		assertTrue(npcType.getHealth()==1);
		npcType.takeDamage(2);
		assertTrue(npcType.getHealth()==(-1));
	}

	public void testSetPosition() throws Exception {
		npcType.setPosition(new Vector2D(10,10));
		assertTrue(npcType.getPosition().equals(new Vector2D(10,10)));
		npcType.setPosition(new Vector2D(-1,-1));
		assertTrue(npcType.getPosition()==new Vector2D(-1,-1));
	}

	public void testIsAttacking() throws Exception {
		//TODO if NPCMove says Attack, so should the NPC.
	}

	public void testCalculateMovementPosition() throws Exception {
		//this is also wtf
	}

	public void testGetAttackArea() throws Exception {

	}

	public void testAttack() throws Exception {

	}
}
