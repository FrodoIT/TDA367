package scratch.model;


import scratch.model.weapons.IWeapon;
import org.simpleframework.xml.*;

import java.awt.geom.Rectangle2D;

/**
 * The interface for all Characters. Every character has a given 
 * health, position, damage, movement speed and weapon. 
 * @author Alma Ottedag
 * revised 2014-03-27 by Ivar Josefsson
 */
@Root
public abstract class Character {
	@Element (type=Rectangle2D.Double.class)
    private Rectangle2D.Double unitTile;
	@Element(type=IWeapon.class)
    private IWeapon weapon;
	@Element
    private int health;
	@Element
    private int movementSpeed;
	@Attribute
    private int id;
	@Element(type=MoveDirection.class, required = false)
    private MoveDirection moveDirection= MoveDirection.SOUTH;
	@Element
    private boolean alive;
    
    public Character(Rectangle2D.Double unitTile, IWeapon weapon, int health, int movementSpeed, int id){
        this.unitTile = new Rectangle2D.Double(unitTile.getX(), unitTile.getY(), unitTile.getWidth(), unitTile.getHeight());
        this.weapon = weapon;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.id = id;
        moveDirection = MoveDirection.SOUTH;
        alive = true;
    }
	//only for NPCType to use
	Character(){
		super();
	}

    public void setId(int id){
	    this.id=id;
    }
    public abstract Vector2D calculateMovementPosition(IRoomData roomData);
    public abstract boolean isInteracting();
	public abstract void doInteractCooldown();
	public abstract boolean isAttacking();

    public void takeDamage(int dmg){
        setHealth(getHealth()-dmg);
        if(getHealth()<0){
            alive=false;
        }
    }
    
    @Override
    public boolean equals (Object obj){
        if(obj==this){
                return true;
        }if(obj==null || !obj.getClass().equals(this.getClass())){
                return false;
        }
        NpcType rhs= (NpcType) obj;
        
        if(this.getUnitTile() == rhs.getUnitTile() && this.getWeapon() == rhs.getWeapon() && 
                this.getHealth() == rhs.getHealth() && this.getMovementSpeed() == rhs.getMovementSpeed() &&
                this.getId() == rhs.getId() && this.getMoveDirection() == rhs.getMoveDirection() && this.isAlive() == rhs.isAlive()){
                return true;
        }
        return false;
    }
    
    public void setPosition(Vector2D position){
        unitTile.setRect(position.getX(),position.getY(), unitTile.getWidth(), unitTile.getHeight());
    }
    
    public void setMoveDirection(MoveDirection direction){
        moveDirection = direction;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    
    
    public Rectangle2D.Double getUnitTile(){
        return unitTile;
    }
    
    public IWeapon getWeapon(){
        return weapon;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMovementSpeed(){
        return movementSpeed;
    }
    
    public int getId(){
        return id;
    }
    
    public MoveDirection getMoveDirection(){
        return moveDirection;
    }
    
    public int getDamage(){
        return weapon.getDamage();
    }
    
    public Vector2D getPosition(){
        return new Vector2D(unitTile.getX(), unitTile.getY());
    }
    
    public Rectangle2D.Double getAttackArea(){
        return new Rectangle2D.Double(unitTile.x+(32*weapon.getRange()*moveDirection.getX()), unitTile.y+(32*weapon.getRange()*moveDirection.getY()), weapon.getAttackArea().width, weapon.getAttackArea().height);
    }
    
    public Rectangle2D.Double attack(){
        if (weapon.isCooledDown()){
            weapon.attack();
            return getAttackArea();
        }
        return null;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public boolean weaponHasCooledDown(){
        return weapon.isCooledDown();
    }
}
