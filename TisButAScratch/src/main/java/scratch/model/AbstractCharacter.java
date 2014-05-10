package scratch.model;


import scratch.model.weapons.IWeapon;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface for all Characters. Every character has a given 
 * health, position, damage, movement speed and weapon. 
 * @author Alma Ottedag
 * revised 2014-03-27 by Ivar Josefsson
 */

public abstract class AbstractCharacter {
    private List<CharacterChangeListener> listenerList;
    private final Rectangle2D.Double unitTile;
    private IWeapon weapon;
    private int health;
    private int movementSpeed;
    private final int id;
    private MoveDirection moveDirection;
    private boolean alive, attacking;

    public AbstractCharacter(Rectangle2D.Double unitTile, IWeapon weapon, int health, int movementSpeed, int id){
        this.unitTile = new Rectangle2D.Double(unitTile.getX(), unitTile.getY(), unitTile.getWidth(), unitTile.getHeight());
        this.weapon = weapon;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.id = id;
        moveDirection = MoveDirection.SOUTH;
        alive = true;
        listenerList = new ArrayList<>();
    }
    public void registerListener(CharacterChangeListener listener){
        listenerList.add(listener);
    }
    public void removeListener(CharacterChangeListener listener){
        listenerList.remove(listener);
    }

    public void takeDamage(int dmg){
        setHealth(getHealth()-dmg);
        if(getHealth()<0){
            alive=false;
        }
    }

    public abstract void update();

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
        return new Rectangle2D.Double(
                unitTile.x+(32*weapon.getRange()*moveDirection.getX()),
                unitTile.y+(32*weapon.getRange()*moveDirection.getY()),
                weapon.getAttackArea().width, weapon.getAttackArea().height);
    }


    public void attack(){
        if(weapon.hasCooledDown()){
            for(CharacterChangeListener listener : listenerList){
                listener.handleCharacterAttack(this, getAttackArea());

            }
            weapon.startCooldown();
        }
    }
    public void interact(){
       for(CharacterChangeListener listener : listenerList){
           listener.handleCharacterInteraction(this, getUnitTile());
       }
    }

    public boolean isAlive(){
        return alive;
    }


    public List<CharacterChangeListener> getListenerList() {
        return listenerList;
    }
}
