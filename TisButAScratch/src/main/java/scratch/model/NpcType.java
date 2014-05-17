package scratch.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;

/**
 * A class that represents a character not controlled by the player
 *
 * @author Ivar
 *
 */


@Root

public final class NpcType extends GameCharacter {

    @Element
    private boolean hostile;

    @Element(type = INPCMove.class)
    private INPCMove movementPattern;

    public NpcType(Rectangle2D.Double unitTile, IWeapon weapon,
            int health, int moveSpeed, String imagePath,
            int id, INPCMove movementPattern) {
        super(unitTile, weapon, health, moveSpeed, id, imagePath);
        this.movementPattern = movementPattern;
        hostile = true;

    }

    /**
     * Needed for Network serialization and xml parsing, should not be used.
     */
    private NpcType() {
        super();
    }

    /**
     * Call to determine if NPC is hostile
     *
     * @return
     */
    public boolean isHostile() {
        return hostile;
    }

    @Override
    public boolean isInteracting() {
        return false;
    }

    @Override
    public void performInteractCooldown() {
        //Npc don't do interact atm
    }

    @Override
    public boolean isPromptingAnAttack() {

        return getWeapon().hasCooledDown() && movementPattern.isPromptingAnAttack(this);
    }

    @Override
    public void update() {
        final Vector2D newPosition = movementPattern.calculateNewPosition(this);
        for (final CharacterChangeListener characterListener : getListeners()) {
            characterListener.handleCharacterMovement(this, newPosition);
        }
        if (isPromptingAnAttack()) {
            performAttack();
        }
    }



    public void setMovementPattern(INPCMove movementPattern) {
        this.movementPattern = movementPattern;
    }


    public INPCMove getMovementPattern() {
        return movementPattern;
    }
}
