package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockModule;

import java.awt.geom.Rectangle2D;

public class PlayerTest extends TestCase {
    private IPlayerInput playerInput;
    private IRoomData roomData;


    @Before
    @Override
    public void setUp(){
        Injector injector = Guice.createInjector(new MockModule());
        playerInput = injector.getInstance(IPlayerInput.class);
        roomData = injector.getInstance(IRoomData.class);
    }

    @Test
    public void testIsAttacking() {
        playerInput.setAttackStatus(true);
        Player testPlayer = new Player(playerInput, new Rectangle2D.Double(0,0,32,32), 1);
        assertTrue(testPlayer.isAttacking());

        playerInput.setAttackStatus(false);
        assertFalse(testPlayer.isAttacking());
    }

    private void assertPlayerDirection(Player player, MoveDirection direction){
        playerInput.setMoveDirection(direction);
        player.update();
        assertTrue(player.getMoveDirection().equals(direction));
    }

    @Test
    public void testUpdate() throws Exception {
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        //Test None:
        assertPlayerDirection(player, MoveDirection.NONE);

        // Test North:
        assertPlayerDirection(player, MoveDirection.NORTH);

        //Test East:
        assertPlayerDirection(player, MoveDirection.EAST);

        //Test South:
        assertPlayerDirection(player, MoveDirection.SOUTH);

        //Test West:
        assertPlayerDirection(player, MoveDirection.WEST);

        //Test Northwest:
        assertPlayerDirection(player, MoveDirection.NORTHWEST);

        //Test Southwest
        assertPlayerDirection(player, MoveDirection.SOUTHWEST);

        //Test Northeast
        assertPlayerDirection(player, MoveDirection.NORTHEAST);

        //Test Southeast
        assertPlayerDirection(player, MoveDirection.SOUTHEAST);

    }

}
