package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
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

public final class NpcType extends AbstractCharacter {

    @Element
    private boolean hostile;

    @Element(type = INPCMove.class)
    private INPCMove movementPattern;

    public NpcType(Rectangle2D.Double unitTile, IWeapon weapon,
            int health, int moveSpeed, String imagePath,
            int id, INPCMove movementPattern, CharacterChangeListener listener) {
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
    public void doInteractCooldown() {
        //Npc don't do interact atm
    }

    @Override
    public boolean isAttacking() {

        return getWeapon().hasCooledDown() && movementPattern.isAttacking(this);
    }

    @Override
    public void update() {
        Vector2D newPosition = movementPattern.calculateNewPosition(this);
        calculateMoveDirection(newPosition);
        for (CharacterChangeListener characterListener : getListenerList()) {
            characterListener.handleCharacterMovement(this, newPosition);
        }
        if (isAttacking()) {
            attack();
        }
    }

    private void calculateMoveDirection(Vector2D newPosition) {
        double diffX = newPosition.getX() - getUnitTile().x;
        double diffY = newPosition.getY() - getUnitTile().y;
        final double toDegrees = 180 / Math.PI;
        double theta = Math.atan(diffY / diffX) * toDegrees;
        MoveDirection moveDirection = MoveDirection.NONE;

        final boolean negativeOrNoXMovement = 0 <= diffX;
        final boolean negativeXMovement = diffX < 0;

        if (-22.5 <= theta && theta <= 22.5 && negativeOrNoXMovement) {
            moveDirection = MoveDirection.EAST;
        } else if (-22.5 <= theta && theta <= 22.5 && negativeXMovement) {
            moveDirection = MoveDirection.WEST;
        } else if (22.5 <= theta && theta <= 67.5 && negativeOrNoXMovement) {
            moveDirection = MoveDirection.SOUTHEAST;
        } else if (-67.5 <= theta && theta <= -22.5 && negativeXMovement) {
            moveDirection = MoveDirection.SOUTHWEST;
        } else if (-67.5 <= theta && theta <= -22.5 && negativeOrNoXMovement) {
            moveDirection = MoveDirection.NORTHEAST;
        } else if (22.5 <= theta && theta <= 67.5 && negativeXMovement) {
            moveDirection = MoveDirection.NORTHWEST;
        } else if (theta < -67.5 && negativeOrNoXMovement || 67.5 < theta && negativeXMovement) {
            moveDirection = MoveDirection.NORTH;
        } else if (67.5 < theta || theta < -67.5) {
            moveDirection = MoveDirection.SOUTH;
        }

        setMoveDirection(moveDirection);
    }

    public void setMovementPattern(INPCMove movementPattern) {
        this.movementPattern = movementPattern;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        }
        NpcType rhs = (NpcType) obj;
        return (super.equals(rhs) && this.isHostile() == rhs.isHostile() && this.getImagePath() == rhs.getImagePath()
                && this.getMovementPattern() == rhs.getMovementPattern());

    }

    public INPCMove getMovementPattern() {
        return movementPattern;
    }
}
