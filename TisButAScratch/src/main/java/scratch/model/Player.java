package scratch.model;

import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Date;
/**
 * Logical representation of the Player in the game.
 * @author Anna Nylander
 * TODO: Add logic for colission detection here through IRoomData.
 *
 */
public class Player implements ICharacter {
    private int health;
    private final int id;
    private int movementSpeed;
    private Rectangle2D.Double unitTile;
    private IPlayerInput playerInput;
    private Date tookDamageAtTime;
    private boolean alive;
    private IWeapon weapon;

    public Player(IPlayerInput playerInput, Rectangle unitTile, int id){
        this.playerInput=playerInput;
        movementSpeed = 2;
        this.id = id;
        this.health = 10;
        tookDamageAtTime = new Date();
        alive = true;
        //TODO: Can we rely on clone here? Not certain that the copy will be deep enough
        this.unitTile = new Rectangle2D.Double(unitTile.getX(), unitTile.getY(), unitTile.getWidth(), unitTile.getHeight());
        weapon = new DefaultWeapon();
    }

    @Override
    public Vector2D calculateMovementPosition(IRoomData roomData){
        return calculateNewPosition(playerInput.getMoveInput());
    }

    @Override
    public boolean alive() {
        return alive;
    }


    public Vector2D calculateNewPosition(MoveDirection direction){
        int deltaX;
        int deltaY;

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
        return new Vector2D(getPosition().getX()+deltaX, getPosition().getY()+deltaY);
    }

    /**
     * The player will take damage if enough time has passed since last time he took damage
     * @param dmg is the amount of damage the npc should take
     * @return true if the NPC is still alive.
     */
    @Override
    public void takeDamage(int dmg){
        Date moment = new Date();
        if (Math.abs(tookDamageAtTime.getTime() - moment.getTime()) > Constants.TIME_BETWEEN_DAMAGE_INSTANCE){
            tookDamageAtTime = moment;
            health=health-dmg;
        }
        if (health< 0){
            alive = false;
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Vector2D getPosition() {
        //Copy to protect from unintentional modification
        return new Vector2D(unitTile.getX(), unitTile.getY());
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public IWeapon getWeapon() {
        return weapon;
    }

    /**
     * Give Player opportunity to execute an attack
     * @return null if no attack is done, otherwise the area that the NPC attacks
     */
    @Override
    public Rectangle2D.Double attack(){
        if (playerInput.getAttackInput() && weapon.attack()){
            return new Rectangle2D.Double(unitTile.x, unitTile.y, weapon.getAttackArea().width, weapon.getAttackArea().height);
        }
        return null;
    }

    public IPlayerInput getPlayerInput(){
        return playerInput;
    }

    public int getID(){
        return id;
    }

    @Override
    public Rectangle2D.Double getUnitTile(){
        return unitTile;
    }

    @Override
    public void setPosition(Vector2D newPosition){
        //TODO: This is not very optimal. Usage of point and rectangle should probably be omitted.
        unitTile.setRect(newPosition.getX(),newPosition.getY(), unitTile.getWidth(), unitTile.getHeight());
    }

    @Override
    public Rectangle2D.Double getAttackArea(){
        return weapon.getAttackArea();
    }

    public void interact(){
        //TODO: Create after there is an object to interact with.
    }

}
