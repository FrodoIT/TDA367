package scratch.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;

/**
 * A class that represents a character not controlled by the player
 *
 * @author Ivar
 *
 */


@Root

public final class NpcType extends GameCharacter {

    @Element(type = INPCMove.class)
    private INPCMove movementPattern;

    public NpcType(Rectangle2D.Double unitTile, Weapon weapon,
            int health, int moveSpeed, String imagePath,
            int id, INPCMove movementPattern) {
        super(unitTile, weapon, health, moveSpeed, id, imagePath);
        this.movementPattern = movementPattern;
    }

    /**
     * Needed for Network serialization and xml parsing, should not be used.
     */
    private NpcType() {
        super();
    }
    
    @Override
    public void update(){
        setAttacking(movementPattern.isPromptingAnAttack(this));
        super.update();
    }

    @Override
    protected Vector2D calculateNewPosition() {
	    return movementPattern.calculateNewPosition(this);
    }

    public void setMovementPattern(INPCMove movementPattern) {
        this.movementPattern = movementPattern;
    }


    public INPCMove getMovementPattern() {
        return movementPattern;
    }
}
