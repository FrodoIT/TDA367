package scratch.model;

import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;
/**
 * Logical representation of the Player in the game.
 * @author Anna Nylander
 * TODO: Add logic for colission detection here through IRoomData.
 *
 */
public final class Player extends AbstractCharacter {
    private IPlayerInput playerInput;
    
    public Player(IPlayerInput playerInput, Rectangle2D.Double unitTile, int id) {
        super(unitTile, new DefaultWeapon(), 10, 2, id);
        this.playerInput=playerInput;
    }

    public boolean isAttacking() {
        return playerInput.isAttacking() && getWeapon().hasCooledDown();
    }

    @Override
    public void update() {
        playerInput.registerAllInput();
        int deltaX;
        int deltaY;
        int movementSpeed = getMovementSpeed();
        MoveDirection direction = playerInput.getMoveInput();

        switch(direction){
            case NORTH:
                deltaX=0;
                deltaY=-movementSpeed;
                setMoveDirection(MoveDirection.NORTH);
                break;
            case SOUTH:
                deltaX=0;
                deltaY=+movementSpeed;
                setMoveDirection(MoveDirection.SOUTH);
                break;
            case WEST:
                deltaX=-movementSpeed;
                deltaY=0;
                setMoveDirection(MoveDirection.WEST);
                break;
            case EAST:
                deltaX=+movementSpeed;
                deltaY=0;
                setMoveDirection(MoveDirection.EAST);
                break;
            case NORTHWEST:
                deltaX=-movementSpeed;
                deltaY=-movementSpeed;
                setMoveDirection(MoveDirection.NORTHWEST);
                break;
            case NORTHEAST:
                deltaX=+movementSpeed;
                deltaY=-movementSpeed;
                setMoveDirection(MoveDirection.NORTHEAST);
                break;
            case SOUTHWEST:
                deltaX=-movementSpeed;
                deltaY=+movementSpeed;
                setMoveDirection(MoveDirection.SOUTHWEST);
                break;
            case SOUTHEAST:
                deltaX=+movementSpeed;
                deltaY=+movementSpeed;
                setMoveDirection(MoveDirection.SOUTHEAST);
                break;
            default:
                deltaX = 0;
                deltaY = 0;
                setMoveDirection(MoveDirection.NONE);
        }

        Vector2D newPosition = new Vector2D(getPosition().getX()+deltaX, getPosition().getY()+deltaY);

        for(CharacterChangeListener listener : super.getListenerList()) {
            listener.handleCharacterMovement(this, newPosition);

            if (getPlayerInput().isInteracting()) {
                interact();
            }

            if (getPlayerInput().isAttacking()) {
                attack();
            }
        }
    }

    /**
     * Give Player opportunity to execute an attack
     * @return null if no attack is done, otherwise the area that the NPC attacks
     * @pre The player has pressed attackbutton and the weapon has cooldowned.
     */

    public IPlayerInput getPlayerInput(){
        return playerInput;
    }

}
