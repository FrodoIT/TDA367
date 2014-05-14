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

    private IPlayerInput playerInput;
    private Room room;



    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Injector injector = Guice.createInjector(new MockModule());
        playerInput = injector.getInstance(IPlayerInput.class);
        IMap map = injector.getInstance(IMap.class);
        room = new Room(map, new DoorHandler());
    }

    @Test
    public void testRegisterListener() throws Exception {
        AbstractCharacter testCharacter = new Player(
                playerInput, new Rectangle2D.Double(0,0,32,32), 1);
        testCharacter.registerListener(room);
        assertFalse(testCharacter.getListenerList().isEmpty());
    }

    @Test
    public void testRemoveListener() throws Exception {
        AbstractCharacter testCharacter = new Player(
                playerInput, new Rectangle2D.Double(0,0,32,32), 1);
        testCharacter.registerListener(room);
        testCharacter.removeListener(room);
        assertTrue(testCharacter.getListenerList().isEmpty());
    }

    @Test
    public void testTakeDamage() throws Exception {
        AbstractCharacter character = new Player(playerInput,
                new Rectangle2D.Double(32,32,32,32), 1);
        character.takeDamage(4);
        assertTrue(character.getHealth() == 6);

        character.takeDamage(100);
        assertTrue(character.getHealth() == 0);
    }


    @Test
    public void testSetPosition() throws Exception {
        AbstractCharacter character = new Player(playerInput,
                new Rectangle2D.Double(32,32,32,32), 1);
        character.setPosition(new Vector2D(0, 0));
        Vector2D expectedPosition = new Vector2D(0,0);
        assertExpectedPosition(expectedPosition, character.getPosition());
    }

    @Test
    public void testIsAlive() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.isAlive());
        character.takeDamage(100);
        assertFalse(character.isAlive());
    }

    @Test
    public void testGetHealth() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.getHealth() == 10);
        character.takeDamage(100);
        assertTrue(character.getHealth() == 0);
    }

    @Test
    public void testGetPosition() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.getPosition().getX() == 32);
        assertTrue(character.getPosition().getY() == 32);
    }

    @Test
    public void testGetDamage() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.getDamage() == 2);
    }

    @Test
    public void testGetMovementSpeed() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue (character.getMovementSpeed() == 2);
    }

    @Test
    public void testGetWeapon() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.getWeapon().equals(new DefaultWeapon()));
    }

    @Test
    public void testAttack() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        IWeapon weapon = character.getWeapon();
        character.attack();
        assertFalse(weapon.hasCooledDown());
    }

    @Test
    public void testGetID() throws Exception {
        AbstractCharacter character = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.getId() == 1);
    }

    @Test
    public void testGetUnitTile() throws Exception {
        AbstractCharacter character = new Player(playerInput,
                new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.getUnitTile().equals(
                new Rectangle2D.Double(32, 32, 32, 32)));
    }

    @Test
    public void testSetHealth() throws Exception {
        AbstractCharacter character = new Player(playerInput,
                new Rectangle2D.Double(32,32,32,32), 1);
        character.setHealth(40);
        assertTrue(character.getHealth() == 40);
    }

    @Test
    public void testGetId() throws Exception {
        AbstractCharacter character = new Player(playerInput,
                new Rectangle2D.Double(32,32,32,32), 1);
        assertTrue(character.getId() == 1);
    }

    @Test
    public void testGetMoveDirection() throws Exception {
        AbstractCharacter character = new Player(playerInput,
                new Rectangle2D.Double(32,32,32,32), 1);
        character.setMoveDirection(MoveDirection.NONE);
        assertTrue(character.getMoveDirection() == (MoveDirection.NONE));
    }

    @Test
    public void testEquals() throws Exception {
        EqualsVerifier.forClass(AbstractCharacter.class).verify();
    }

    @Test
    public void assertExpectedPosition(Vector2D a, Vector2D b) throws Exception {
        assertTrue (a.getX() == b.getX() && a.getY() == b.getY());
    }
}
