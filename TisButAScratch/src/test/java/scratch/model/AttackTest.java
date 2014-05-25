/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.model;

import java.awt.geom.Rectangle2D;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.weapons.Weapon;

/**
 *
 * @author Ivar
 */
public class AttackTest extends TestCase{
    private Attack attack;
    
    @Before
    @Override
    public void setUp() throws Exception {
        attack = new Attack(new Rectangle2D.Double(40, 40, 32, 32), new Vector2D(10, 5), new Weapon(), GameCharacter.class);
    }
    
    @Test
    public void testUpdate(){
        attack.update();
        assertEquals(attack.getPosition(), new Vector2D(50, 45));
        attack.setPosition(new Vector2D(200, 200));
        attack.update();
        assertFalse(attack.isRemaining());
    }
    
    
    
}
