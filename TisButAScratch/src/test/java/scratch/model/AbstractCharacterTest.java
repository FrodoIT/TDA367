package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.IWeapon;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;

public class AbstractCharacterTest extends TestCase {

    private final double epsilon = Math.pow(10, -10);
    private Room room;
    private GameCharacter testCharacter;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        final Injector injector = Guice.createInjector(new MockModule());
        final IMap map = injector.getInstance(IMap.class);
        room = new Room(map, new DoorHandler());
        testCharacter = new GameCharacter(new Rectangle2D.Double(32, 32, 32, 32), new Weapon(), 10, 2, 1, "/res/playerSprite.tmx");
    }

    @Test
    public void testRegisterListener() throws Exception {
        testCharacter.registerListener(room);
        assertFalse(testCharacter.getListeners().isEmpty());
    }

    @Test
    public void testRemoveListener() throws Exception {
        testCharacter.registerListener(room);
        testCharacter.removeListener(room);
        assertTrue(testCharacter.getListeners().isEmpty());
    }

    @Test
    public void testTakeDamage() throws Exception {
        testCharacter.takeDamage(4);
        assertEquals(6, testCharacter.getHealth());

        testCharacter.takeDamage(100);
        assertEquals(0, testCharacter.getHealth());
    }

    @Test
    public void testSetPosition() throws Exception {
        testCharacter.setPosition(new Vector2D(0, 0));
        final Vector2D expectedPosition = new Vector2D(0, 0);
        assertExpectedPosition(expectedPosition, testCharacter.getPosition());
    }

    @Test
    public void testIsAlive() throws Exception {
        assertTrue(testCharacter.isAlive());
        testCharacter.takeDamage(100);
        assertFalse(testCharacter.isAlive());
    }

    @Test
    public void testGetHealth() throws Exception {
        assertEquals(10, testCharacter.getHealth());
        testCharacter.takeDamage(100);
        assertEquals(0, testCharacter.getHealth());
    }

    @Test
    public void testGetPosition() throws Exception {
        Vector2D pos = testCharacter.getPosition();
        assertEquals(32, pos.getX(), epsilon);
        assertEquals(32, pos.getY(), epsilon);
    }

    @Test
    public void testGetDamage() throws Exception {
        assertEquals(2, testCharacter.getDamage());
    }

    @Test
    public void testGetMovementSpeed() throws Exception {
        assertEquals(2, testCharacter.getMovementSpeed());
    }

    @Test
    public void testGetWeapon() throws Exception {
        assertTrue(testCharacter.getWeapon().equals(new Weapon()));
    }

    @Test
    public void testAttack() throws Exception {
        final IWeapon weapon = testCharacter.getWeapon();
        testCharacter.performAttack();
        assertFalse(weapon.hasCooledDown());
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testCharacter.getId());
    }

    @Test
    public void testGetUnitTile() throws Exception {
        assertTrue(testCharacter.getUnitTile().equals(
                new Rectangle2D.Double(32, 32, 32, 32)));
    }

    @Test
    public void testSetHealth() throws Exception {
        testCharacter.setHealth(40);
        assertEquals(40, testCharacter.getHealth());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(1, testCharacter.getId());
    }

    @Test
    public void testGetMoveDirection() throws Exception {
        final MoveDirection preSetMoveDirection = testCharacter.getMoveDirection();
        testCharacter.setNextMoveDirection(new Vector2D());
        assertSame(testCharacter.getMoveDirection(), preSetMoveDirection);
    }

    @Test
    public void testEquals() throws Exception {
        EqualsVerifier.forClass(GameCharacter.class).verify();
    }

    @Test
    public void assertExpectedPosition(Vector2D a, Vector2D b) {
        assertEquals(a.getX(), b.getX(), epsilon);
        assertEquals(a.getY(), b.getY(), epsilon);
    }

    @Test
    public void testSetId() {
        testCharacter.setId(4);
        assertEquals(4, testCharacter.getId());
    }

}
