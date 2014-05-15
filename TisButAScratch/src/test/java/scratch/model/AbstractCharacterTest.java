package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockModule;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;

public class AbstractCharacterTest extends TestCase {

    private Room room;
    private AbstractCharacter testCharacter;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Injector injector = Guice.createInjector(new MockModule());
        IPlayerInput playerInput = injector.getInstance(IPlayerInput.class);
        IMap map = injector.getInstance(IMap.class);
        room = new Room(map, new DoorHandler());
        testCharacter = new Player(playerInput, new Rectangle2D.Double(32, 32, 32, 32), 1, "/res/playerSprite.tmx");
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
        assertTrue(testCharacter.getHealth() == 6);

        testCharacter.takeDamage(100);
        assertTrue(testCharacter.getHealth() == 0);
    }

    @Test
    public void testSetPosition() throws Exception {
        testCharacter.setPosition(new Vector2D(0, 0));
        Vector2D expectedPosition = new Vector2D(0, 0);
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
        assertTrue(testCharacter.getHealth() == 10);
        testCharacter.takeDamage(100);
        assertTrue(testCharacter.getHealth() == 0);
    }

    @Test
    public void testGetPosition() throws Exception {
        assertTrue(testCharacter.getPosition().getX() == 32);
        assertTrue(testCharacter.getPosition().getY() == 32);
    }

    @Test
    public void testGetDamage() throws Exception {
        assertTrue(testCharacter.getDamage() == 2);
    }

    @Test
    public void testGetMovementSpeed() throws Exception {
        assertTrue(testCharacter.getMovementSpeed() == 2);
    }

    @Test
    public void testGetWeapon() throws Exception {
        assertTrue(testCharacter.getWeapon().equals(new DefaultWeapon()));
    }

    @Test
    public void testAttack() throws Exception {
        IWeapon weapon = testCharacter.getWeapon();
        testCharacter.attack();
        assertFalse(weapon.hasCooledDown());
    }

    @Test
    public void testGetID() throws Exception {
        assertTrue(testCharacter.getId() == 1);
    }

    @Test
    public void testGetUnitTile() throws Exception {
        assertTrue(testCharacter.getUnitTile().equals(
                new Rectangle2D.Double(32, 32, 32, 32)));
    }

    @Test
    public void testSetHealth() throws Exception {
        testCharacter.setHealth(40);
        assertTrue(testCharacter.getHealth() == 40);
    }

    @Test
    public void testGetId() throws Exception {
        assertTrue(testCharacter.getId() == 1);
    }

    @Test
    public void testGetMoveDirection() throws Exception {
        testCharacter.setMoveDirection(MoveDirection.NONE);
        assertTrue(testCharacter.getMoveDirection() == (MoveDirection.NONE));
    }

    @Test
    public void testEquals() throws Exception {
        EqualsVerifier.forClass(AbstractCharacter.class).verify();
    }

    @Test
    public void assertExpectedPosition(Vector2D a, Vector2D b) throws Exception {
        double epsilon = 0.0000000001;
        assertTrue(Math.abs(a.getX() - b.getX())< epsilon &&
                Math.abs(a.getY() - b.getY()) <epsilon);
    }

    public void testSetId() throws Exception {
        testCharacter.setId(4);
        assertTrue(testCharacter.getId() == 4);
    }

}
