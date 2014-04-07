package construction;

import java.awt.Point;
import java.awt.Rectangle;
import model.INpc;
import model.IWeapon;
import model.MoveDirection;

/**
 * A class that represents a character not controlled by the player
 * @author Ivar
 *
 */
public class MonsterType implements INpc {

	private Rectangle unitTile;
	private IWeapon weapon;
	private int health;
	private int movementSpeed;
        private final String imagePath;
        private final int id;
        private MoveDirection moveDirection;

	public MonsterType(Rectangle unitTile, IWeapon weapon, int health, int moveSpeed, String imagePath, int id){
            this.unitTile = new Rectangle((int)unitTile.getX(), (int)unitTile.getY(), (int)unitTile.getWidth(), (int)unitTile.getHeight());
            this.weapon = weapon;
            this.health = health;
            this.movementSpeed = moveSpeed;
            this.imagePath = imagePath;
            this.id = id;
            moveDirection = MoveDirection.NONE;
	}
      
        
        public void update(Point playerPos){
            move(playerPos);
        }
        
        public void move(Point playerPos){
            setPosition(new Point((int)playerPos.getX() + 50, (int)playerPos.getY()));
            
        }
	
	//Getters
	
	/**
	 * Call to get a Point with the position of the NPC
	 * @return a copy of the pointer holding the position
	 */
	public Point getPosition() {
		return unitTile.getLocation();
	}
	
	public IWeapon getWeapon() {
		return weapon;
	}
	
	public int getDamage() {
		return weapon.getDamage();
	}
	
	public int getRange() {
		return weapon.getRange();
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	
	
	//Setters
	public void setPosition(Point position) {
                calculateMoveDirection(position);
		unitTile.setLocation(position);
	}
          
       private void calculateMoveDirection(Point newPosition){
            double diffX = newPosition.x - unitTile.x;
            double diffY = newPosition.y - unitTile.y;
            double theta = Math.atan(diffY / diffX)*180/Math.PI;
            
            if(diffX == 0 && diffY == 0){
                setMoveDirection(MoveDirection.NONE);
            }else if(-45 <= theta && theta <= 45 && 0 <= diffX){
                setMoveDirection(MoveDirection.EAST);
            }else if(theta < -45){
                setMoveDirection(MoveDirection.NORTH);
            }else if(45 < theta){
                setMoveDirection(MoveDirection.SOUTH);
            }else{
                setMoveDirection(MoveDirection.WEST);
            }
       }
       
       private void setMoveDirection(MoveDirection direction){
           moveDirection = direction;
       }
       
       public MoveDirection getMoveDirection(){
           return moveDirection;
       }
            
	public void setHealth(int health) {
		this.health = health;
	}

    public int getID(){
        return id;
    }

    public String getImagePath(){
        return imagePath;
    }

	

	
}
