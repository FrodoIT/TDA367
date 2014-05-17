package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;

public class PlayerTest extends TestCase {
    private IPlayerInput playerInput;
    private GameCharacter player;

    @Before
    @Override
    public void setUp(){
        final Injector injector = Guice.createInjector(new MockModule());
        playerInput = injector.getInstance(IPlayerInput.class);
        player = new GameCharacter(new Rectangle2D.Double(0,0,32,32), new DefaultWeapon(), 10, 2, 1, "/res/playerSprite.tmx");
    }

    @Test
    public void testIsAttacking() {
        playerInput.setAttackStatus(true);

        assertTrue(player.isPromptingAnAttack());
    }

    private void assertPlayerDirection(GameCharacter player, MoveDirection direction){
        playerInput.setMoveDirection(direction);
        player.update();
        assertSame(player.getMoveDirection(), direction);
    }

    @Test
    public void testUpdate() throws Exception {
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
