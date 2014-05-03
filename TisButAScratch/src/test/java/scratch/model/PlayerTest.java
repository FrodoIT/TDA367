package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockModule;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;

public class PlayerTest extends TestCase {
    private IPlayerInput playerInput;
    private IRoomData roomData;


    @Before
    public void setup(){
        Injector injector = Guice.createInjector(new MockModule());
        playerInput = injector.getInstance(IPlayerInput.class);
        roomData = injector.getInstance(IRoomData.class);

    }
    @Test
    public void testCalculateMovementPosition() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        // Test North:
        Vector2D expectedPosition = new Vector2D(32,30);
        Vector2D movementTestVector = player.calculateMovementPosition(roomData);
        assertExpectedPosition(movementTestVector, expectedPosition);
        //Test East:
        playerInput.setMoveDirection(MoveDirection.EAST);
        movementTestVector = player.calculateMovementPosition(roomData);
        expectedPosition = new Vector2D(34, 32);
        assertExpectedPosition(movementTestVector, expectedPosition);
        //Test South:
        playerInput.setMoveDirection(MoveDirection.SOUTH);
        movementTestVector = player.calculateMovementPosition(roomData);
        expectedPosition = new Vector2D(32, 34);
        assertExpectedPosition(movementTestVector, expectedPosition);
        //Test West:
        playerInput.setMoveDirection(MoveDirection.WEST);
        movementTestVector = player.calculateMovementPosition(roomData);
        expectedPosition = new Vector2D(30, 32);
        assertExpectedPosition(movementTestVector, expectedPosition);

    }

    public void assertExpectedPosition(Vector2D a, Vector2D b){
        assert (a.getX() == b.getX());
        assert (a.getY() == b.getY());

    }

    @Test
    public void testIsAlive() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert(player.getHealth() > 0);
        assert(player.isAlive());
    }

    @Test
    public void testCalculateNewPosition() throws Exception {
        //Unnecessary. The Method in Player is also unnecessary. Should be refactored.
    }

    public void testTakeDamage() throws Exception {
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        System.out.println(player.getHealth());
        player.takeDamage(4);
        System.out.println(player.getHealth());
    }

    public void testGetHealth() throws Exception {
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert(player.getHealth() == 10);
        player.takeDamage(100);
        assert(player.getHealth() == 0);
    }

    public void testGetPosition() throws Exception {
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert (player.getPosition().getX() == 32);
        assert (player.getPosition().getY() == 32);
    }

    public void testGetDamage() throws Exception {
        //Might need to polish this up after we fix the Weapon in the model.
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert (player.getDamage() == 2);
    }

    public void testGetMovementSpeed() throws Exception {
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert (player.getMovementSpeed() == 2);
    }

    public void testGetWeapon() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        IWeapon expectedWeapon = new DefaultWeapon();
        System.out.println(player.getWeapon());
        System.out.println(expectedWeapon);
        assert (player.getWeapon().equals(expectedWeapon));
    }

    public void testAttack() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert(player.attack().equals(player.getAttackArea()));
    }

    public void testGetPlayerInput() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert (player.getPlayerInput().equals(playerInput));
    }

    public void testGetID() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert (player.getID() == 1);
    }

    public void testGetUnitTile() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert(player.getUnitTile().equals(new Rectangle2D.Double(32,32,32,32)));
    }

    public void testSetPosition() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        player.setPosition(new Vector2D(0,0));
        Vector2D expectedPosition = new Vector2D(0,0);
        assertExpectedPosition(expectedPosition, player.getPosition());
    }

    public void testGetAttackArea() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert(player.getAttackArea().equals(new Rectangle2D.Double(player.getUnitTile().x+(32*player.getWeapon().getRange()*player.getLookingDirection().getX()), player.getUnitTile().y+(32*player.getWeapon().getRange()*player.getLookingDirection().getY()), player.getWeapon().getAttackArea().width, player.getWeapon().getAttackArea().height)));
    }


    public void testIsInteracting() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert(player.isInteracting());
    }

    public void testIsAttacking() throws Exception {
        //Player 'attack' should be true when attacking. Currently it doesn't work that way...
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert (!player.isAttacking());
    }

    public void testWeaponHasCooledDown() throws Exception {
        setup();
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert (player.weaponHasCooledDown());
        player.attack();
        assert (!player.weaponHasCooledDown());
    }
}
