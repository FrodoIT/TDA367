package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;

public class PlayerTest extends TestCase {
    private GameCharacter player;

    @Before
    @Override
    public void setUp(){
        final Injector injector = Guice.createInjector(new MockModule());
        player = new GameCharacter(new Rectangle2D.Double(0,0,32,32), new Weapon(), 10, 2, 1, "/res/playerSprite.tmx");
    }

    @Test
    public void testIsAttacking() {
        player.setAttacking(true);
        assertTrue(player.isPromptingAnAttack());
    }

    private void assertPlayerDirection(GameCharacter player, Vector2D vectorDirection, MoveDirection moveDirection){
        player.setNextMoveDirection(vectorDirection);
        player.update();
        assertSame(moveDirection, player.getMoveDirection());
    }

    @Test
    public void testUpdate() throws Exception {
        //Test None:
        assertPlayerDirection(player, new Vector2D(0, 0), MoveDirection.NONE);

        // Test North:
        assertPlayerDirection(player, new Vector2D(0, -1), MoveDirection.NORTH);

        //Test East:
        assertPlayerDirection(player, new Vector2D(1, 0), MoveDirection.EAST);

        //Test South:
        assertPlayerDirection(player, new Vector2D(0, 1), MoveDirection.SOUTH);

        //Test West:
        assertPlayerDirection(player, new Vector2D(-1, 0), MoveDirection.WEST);

        //Test Northwest:
        assertPlayerDirection(player, new Vector2D(-1, -1), MoveDirection.NORTHWEST);

        //Test Southwest
        assertPlayerDirection(player, new Vector2D(-1, 1), MoveDirection.SOUTHWEST);

        //Test Northeast
        assertPlayerDirection(player, new Vector2D(1, -1), MoveDirection.NORTHEAST);

        //Test Southeast
        assertPlayerDirection(player, new Vector2D(1, 1), MoveDirection.SOUTHEAST);

    }

}
