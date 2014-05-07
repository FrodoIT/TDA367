package scratch.model;

import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;
import java.util.Date;
/**
 * Logical representation of the Player in the game.
 * @author Anna Nylander
 * TODO: Add logic for colission detection here through IRoomData.
 *
 */
public final class Player extends Character {
    private IPlayerInput playerInput;
    
    public Player(IPlayerInput playerInput, Rectangle2D.Double unitTile, int id){
        super(unitTile, new DefaultWeapon(), 10, 2, id);
        this.playerInput=playerInput;
    }

    @Override
    public Vector2D calculateMovementPosition(IRoomData roomData){
        int deltaX;
        int deltaY;
        int movementSpeed = getMovementSpeed();
        MoveDirection direction = playerInput.getMoveInput();

        switch(direction){
            case NORTH:
                deltaX=0;
                deltaY=-movementSpeed;
                break;
            case SOUTH:
                deltaX=0;
                deltaY=+movementSpeed;
                break;
            case WEST:
                deltaX=-movementSpeed;
                deltaY=0;
                break;
            case EAST:
                deltaX=+movementSpeed;
                deltaY=0;
                break;
            case NORTHWEST:
                deltaX=-movementSpeed;
                deltaY=-movementSpeed;
                break;
            case NORTHEAST:
                deltaX=+movementSpeed;
                deltaY=-movementSpeed;
                break;
            case SOUTHWEST:
                deltaX=-movementSpeed;
                deltaY=+movementSpeed;
                break;
            case SOUTHEAST:
                deltaX=+movementSpeed;
                deltaY=+movementSpeed;
                break;
            default:
                deltaX = 0;
                deltaY = 0;
        }
        setMoveDirection(playerInput.getMoveInput());
        return new Vector2D(getPosition().getX()+deltaX, getPosition().getY()+deltaY);
    }

    /**
     * Give Player opportunity to execute an attack
     * @return null if no attack is done, otherwise the area that the NPC attacks
     * @pre The player has pressed attackbutton and the weapon has cooldowned.
     */
    
    @Override
    public Rectangle2D.Double attack(){
        if (playerInput.getAttackInput()){
            return super.attack();
        }
        return null;
    }

    public IPlayerInput getPlayerInput(){
        return playerInput;
    }
    
    
    @Override
    public boolean isInteracting(){
        return playerInput.getInteractInput();
    }

    @Override
    public boolean isAttacking() {
        return (playerInput.getAttackInput());
    }



}
