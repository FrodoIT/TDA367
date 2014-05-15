package scratch.model;

import scratch.model.weapons.DefaultWeapon;
import scratch.utils.Cooldown;

import java.awt.geom.Rectangle2D;

/**
 * Logical representation of the Player in the game.
 *
 * @author Anna Nylander TODO: Add logic for colission detection here through
 * IRoomData.
 *
 */
public final class Player extends AbstractCharacter {

    private IPlayerInput playerInput;
    private boolean interactIsCooledDown = true;

    private Runnable cooldownReset = new Runnable() {
        @Override
        public void run() {
            interactIsCooledDown = true;
        }
    };

    /**
     * Needed for serialization, should not be used
     */
    public Player(){
        playerInput = null;
    }
    
    public Player(IPlayerInput playerInput, Rectangle2D.Double unitTile, int id, String imagePath) {
        super(unitTile, new DefaultWeapon(), 10, 2, id, imagePath);
        this.playerInput = playerInput;
    }

    @Override
    public void update() {
        playerInput.registerAllInput();
        int deltaX;
        int deltaY;
        int movementSpeed = getMovementSpeed();
        MoveDirection direction = playerInput.getMoveInput();

        switch (direction) {
            case NORTH:
                deltaX = 0;
                deltaY = -movementSpeed;
                setMoveDirection(MoveDirection.NORTH);
                break;
            case SOUTH:
                deltaX = 0;
                deltaY = +movementSpeed;
                setMoveDirection(MoveDirection.SOUTH);
                break;
            case WEST:
                deltaX = -movementSpeed;
                deltaY = 0;
                setMoveDirection(MoveDirection.WEST);
                break;
            case EAST:
                deltaX = +movementSpeed;
                deltaY = 0;
                setMoveDirection(MoveDirection.EAST);
                break;
            case NORTHWEST:
                deltaX = -movementSpeed;
                deltaY = -movementSpeed;
                setMoveDirection(MoveDirection.NORTHWEST);
                break;
            case NORTHEAST:
                deltaX = +movementSpeed;
                deltaY = -movementSpeed;
                setMoveDirection(MoveDirection.NORTHEAST);
                break;
            case SOUTHWEST:
                deltaX = -movementSpeed;
                deltaY = +movementSpeed;
                setMoveDirection(MoveDirection.SOUTHWEST);
                break;
            case SOUTHEAST:
                deltaX = +movementSpeed;
                deltaY = +movementSpeed;
                setMoveDirection(MoveDirection.SOUTHEAST);
                break;
            default:
                deltaX = 0;
                deltaY = 0;
                setMoveDirection(MoveDirection.NONE);
        }
        Vector2D newPosition = new Vector2D(getPosition().getX() + deltaX, getPosition().getY() + deltaY);

        for (CharacterChangeListener listener : super.getListeners()) {
            listener.handleCharacterMovement(this, newPosition);

            if (isInteracting()) {
                interact();
            }

            if (isAttacking()) {
                attack();
            }
        }
    }

    /**
     * Give Player opportunity to execute an attack
     *
     * @return null if no attack is done, otherwise the area that the NPC
     * attacks
     * @pre The player has pressed attackbutton and the weapon has cooldowned.
     */
    public IPlayerInput getPlayerInput() {
        return playerInput;
    }

    @Override
    public boolean isInteracting() {
        return playerInput.isInteracting() && interactIsCooledDown;
    }

    @Override
    public void doInteractCooldown() {
        interactIsCooledDown = false;
        Cooldown.cooldown(500, cooldownReset);
    }


    public boolean isAttacking() {
        return playerInput.isAttacking() && getWeapon().hasCooledDown();
    }
}
