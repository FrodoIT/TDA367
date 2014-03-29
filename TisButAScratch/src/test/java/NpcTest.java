

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;

import model.Knuckles;
import model.Npc;
import model.Sword;

import org.junit.Test;

public class NpcTest {

	private Npc npc;

	public NpcTest(){
		npc = new Npc();
	}

	@Test
	public void testNpcPoint() {
		npc = new Npc(new Point(30, 30));
	}

	@Test
	public void testNpcPointAndWeapon(){
		npc = new Npc(new Point(10,10), new Sword());
	}

	public void testNpcPointWeaponHealthMovespeed(){
		npc = new Npc(new Point(10,10), new Sword(), 100, 3);
	}

	@Test
	public void testGetPos() {
		npc = new Npc();
		assertTrue(npc.getPosition().equals(new Point(320, 240)));
		npc = new Npc(new Point (25, 25));
		assertTrue(npc.getPosition().equals(new Point(25, 25)));		
	}

	@Test
	public void testGetHealth(){
		npc.setHealth(10);
		assertTrue(npc.getHealth() == 10);
	}
	public void testsetHealth(){
		npc.setHealth(100);
		assertTrue(npc.getHealth() == 100);
	}
	@Test
	public void testDefaultMovementSpeed() {
		npc = new Npc();
		assertTrue(npc.getMovementSpeed() == 1);
	}
	@Test
	public void testGetMovementSpeed(){
		npc = new Npc(new Point(10,10), new Sword(), 100, 3);
		assertTrue(npc.getMovementSpeed() == 3);
	}

	@Test
	public void testGetDamage() {
		npc = new Npc(new Point(10,10), new Knuckles(), 100, 3);
		assertTrue(npc.getDamage() == 2);
	}

	@Test
	public void testGetRange() {
		npc = new Npc(new Point(10,10), new Sword(), 100, 3);
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
