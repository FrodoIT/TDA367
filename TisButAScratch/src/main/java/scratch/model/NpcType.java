package scratch.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;


/**
 * A class that represents a character not controlled by the player
 * @author Ivar
 *
 */
@Root

public final class NpcType extends AbstractCharacter {
    @Element
    private boolean hostile;
	@Element
    private String imagePath;
	@Element(type = INPCMove.class)
    private INPCMove movementPattern;

    public NpcType(Rectangle2D.Double unitTile, IWeapon weapon,
                   int health, int moveSpeed, String imagePath,
                   int id, INPCMove movementPattern, CharacterChangeListener listener){
        super(unitTile, weapon, health, moveSpeed, id);
        this.imagePath = imagePath;
        this.movementPattern = movementPattern;
        hostile = true;

    }

	//used for xml-parsing
	private NpcType(){
		super();
	}

    /**
     * Call to determine if NPC is hostile
     * @return
     */
    
    public boolean isHostile(){
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
    public void update(){
        Vector2D newPosition = movementPattern.calculateNewPosition(this);
        calculateMoveDirection(newPosition);
        for (CharacterChangeListener characterListener : getListenerList()) {
            characterListener.handleCharacterMovement(this, newPosition);
        }

        if (isAttacking()) {
            attack();
        }
    }


    private void calculateMoveDirection(Vector2D newPosition){
        double diffX = newPosition.getX() - getUnitTile().x;
        double diffY = newPosition.getY() - getUnitTile().y;
        double theta = Math.atan(diffY / diffX)*180/Math.PI;

        if(diffX == 0 && diffY == 0 || newPosition.equals(null)){
            setMoveDirection(MoveDirection.NONE);
        }else if(-22.5 <= theta && theta <= 22.5 && 0 <= diffX){
            setMoveDirection(MoveDirection.EAST);
        }else if(-22.5 <= theta && theta <= 22.5 && diffX < 0){
            setMoveDirection(MoveDirection.WEST);
        }else if(22.5 <= theta && theta <= 67.5 && 0 <= diffX){
            setMoveDirection(MoveDirection.SOUTHEAST);
        }else if(-67.5 <= theta && theta <= -22.5 && diffX < 0){
            setMoveDirection(MoveDirection.SOUTHWEST);
        }else if(-67.5 <= theta && theta <= -22.5 && 0 <= diffX){
            setMoveDirection(MoveDirection.NORTHEAST);
        }else if(22.5 <= theta && theta <= 67.5 && diffX < 0){
            setMoveDirection(MoveDirection.NORTHWEST);
        }else if(theta < -67.5 && 0 <= diffX || 67.5 < theta && diffX < 0){
            setMoveDirection(MoveDirection.NORTH);
        }else if(67.5 < theta || theta < -67.5){
            setMoveDirection(MoveDirection.SOUTH);
        }
    }

    public String getImagePath(){
        return imagePath;
    }

    
    public void setMovementPattern(INPCMove movementPattern) {
        this.movementPattern = movementPattern;
    }



    public INPCMove getMovementPattern(){
        return movementPattern;
    }
}
