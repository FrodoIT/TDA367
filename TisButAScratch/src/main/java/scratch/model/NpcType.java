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
public final class NpcType extends Character{
    @Element
    private boolean hostile;
	@Element
    private String imagePath;
	@Element(type = INPCMove.class)
    private INPCMove movementPattern;
	@Element(type=IRoomData.class, required = false)
    private IRoomData roomData=null;

    public NpcType(Rectangle2D.Double unitTile, IWeapon weapon, int health, int moveSpeed, String imagePath, int id, INPCMove movementPattern){
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

    public void updateRoomData(IRoomData roomData){
        this.roomData=roomData;
    }


    @Override
    public boolean isInteracting() {
            return false;
    }

    @Override
    public boolean isAttacking() {
            return movementPattern.isAttacking(roomData, this);
    }


    @Override
    public Vector2D calculateMovementPosition(IRoomData roomData) {
        Vector2D newPosition = movementPattern.calculateNewPosition(roomData, this);
        calculateMoveDirection(newPosition);
        return newPosition;
    }

    private void calculateMoveDirection(Vector2D newPosition){
        double diffX = newPosition.getX() - getUnitTile().x;
        double diffY = newPosition.getY() - getUnitTile().y;
        double theta = Math.atan(diffY / diffX)*180/Math.PI;

        if(diffX == 0 && diffY == 0){
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
        }else if(theta < -67.5 && 0 < diffX || 67.5 < theta && diffX < 0){
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

    @Override
    public boolean equals(Object obj) {
            if(obj==this){
                    return true;
            }if(obj==null || !obj.getClass().equals(this.getClass())){
                    return false;
            }
            NpcType rhs= (NpcType) obj;
            return (super.equals(rhs) && this.isHostile() == rhs.isHostile() && this.getImagePath() == rhs.getImagePath() &&
                    this.getMovementPattern() == rhs.getMovementPattern());

    }
    
    public INPCMove getMovementPattern(){
        return movementPattern;
    }
}
