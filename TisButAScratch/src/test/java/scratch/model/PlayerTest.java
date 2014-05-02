package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.controller.PlayerController;
import scratch.view.View;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Created by pippin on 5/1/14.
 */
public class PlayerTest extends TestCase {
    private IPlayerInput playerInput;

    @Before
    public void setup(){
        Injector injector = Guice.createInjector();
        playerInput = injector.getInstance(IPlayerInput.class);
    }
    @Test
    public void testCalculateMovementPosition() throws Exception {

    }

    @Test
    public void testIsAlive() throws Exception {
        Player player = new Player(playerInput, new Rectangle2D.Double(32,32,32,32), 1);
        assert(player.getHealth() > 0);
        assert(player.isAlive());
    }

    @Test
    public void testCalculateNewPosition() throws Exception {

    }

    public void testTakeDamage() throws Exception {

    }

    public void testGetHealth() throws Exception {

    }

    public void testGetPosition() throws Exception {

    }

    public void testGetDamage() throws Exception {

    }

    public void testGetMovementSpeed() throws Exception {

    }

    public void testGetWeapon() throws Exception {

    }

    public void testAttack() throws Exception {

    }

    public void testGetPlayerInput() throws Exception {

    }

    public void testGetID() throws Exception {

    }

    public void testGetUnitTile() throws Exception {

    }

    public void testSetPosition() throws Exception {

    }

    public void testGetAttackArea() throws Exception {

    }

    public void testIsInteracting() throws Exception {

    }

    public void testIsAttacking() throws Exception {

    }

    public void testWeaponHasCooledDown() throws Exception {

    }
}
