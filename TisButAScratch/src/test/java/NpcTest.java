

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;

import model.MoveCommand;
import model.Npc;
import model.Player;

import org.junit.Test;

public class NpcTest {
	
	private Npc npc;
	
	public NpcTest(){
		npc = new Npc();
	}

	@Test
	public void testNpcPoint() {
		npc = new Player(new Point(30, 30));
	}
	
	@Test
	public void testGetPos() {
		npc = new Npc();
		assertTrue(npc.getPosition().equals(new Point(320, 240)));
		npc = new Npc(new Point (25, 25));
		assertTrue(npc.getPosition().equals(new Point(25, 25)));		
	}
	
	@Test
	public void testGetMovementSpeed() {
		npc = new Npc();
		assertTrue(npc.getMovementSpeed() == 2);
	}
	
	@Test
	public void testGetDamage() {
		npc = new Npc();
		assertTrue(npc.getDamage() == 2);
	}
	
	@Test
	public void testGetRange() {
		npc = new Npc();
		assertTrue(npc.getRange() == 1);
	}
	
	@Test
	public void testCalculateNewPosition() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMove() {
		fail("Not yet implemented");
	}
}
