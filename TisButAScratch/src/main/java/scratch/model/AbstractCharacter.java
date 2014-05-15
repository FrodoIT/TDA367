package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import scratch.model.weapons.IWeapon;

import javax.annotation.concurrent.Immutable;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface for all Characters. Every character has a given health,
 * position, damage, movement speed and weapon.
 *
 * @author Alma Ottedag revised 2014-03-27 by Ivar Josefsson
 */

@Immutable
public abstract class AbstractCharacter implements KryoSerializable{

    @Element(type = Rectangle2D.Double.class, required = false)
    private Rectangle2D.Double unitTile = new Rectangle2D.Double(0, 0, 32, 32);
    private List<CharacterChangeListener> listenerList = new ArrayList<>();
    @Element(type = IWeapon.class)
    private IWeapon weapon;
    @Element
    private int health;
    @Element
    private int movementSpeed;
    @Attribute
    private int id;
    @Element(type = MoveDirection.class, required = false)
    private MoveDirection moveDirection = MoveDirection.SOUTH;
    @Element
    private String imagePath;


    public AbstractCharacter(Rectangle2D.Double unitTile, IWeapon weapon, int health, int movementSpeed, int id, String imagePath) {
        this.unitTile = new Rectangle2D.Double(unitTile.getX(), unitTile.getY(), unitTile.getWidth(), unitTile.getHeight());
        this.weapon = weapon;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.id = id;
        this.imagePath = imagePath;
        moveDirection = MoveDirection.SOUTH;
    }

    public void registerListener(CharacterChangeListener listener) {
        listenerList.add(listener);
    }

    AbstractCharacter() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract boolean isInteracting();

    public abstract void doInteractCooldown();

    public abstract boolean isAttacking();

    public void removeListener(CharacterChangeListener listener) {
        listenerList.remove(listener);
    }

    public void takeDamage(int dmg) {
        health = health - Math.abs(dmg);

        if (Math.abs(dmg) > health) {
            health = 0;
        }
    }

    public abstract void update();


    public void setPosition(Vector2D position) {
        unitTile.setRect(position.getX(), position.getY(), unitTile.getWidth(), unitTile.getHeight());
    }

    public void setMoveDirection(MoveDirection direction) {
        moveDirection = direction;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle2D.Double getUnitTile() {
        return unitTile;
    }

    public IWeapon getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getId() {
        return id;
    }

    public MoveDirection getMoveDirection() {
        return moveDirection;
    }

    public int getDamage() {
        return weapon.getDamage();
    }

    public Vector2D getPosition() {
        return new Vector2D(unitTile.getX(), unitTile.getY());
    }

    public Rectangle2D.Double getAttackArea() {
        return new Rectangle2D.Double(
                unitTile.x + (32 * weapon.getRange() * moveDirection.getX()),
                unitTile.y + (32 * weapon.getRange() * moveDirection.getY()),
                weapon.getAttackArea().width, weapon.getAttackArea().height);
    }
    
    public String getImagePath(){
        return imagePath;
    }

    public void attack() {
        if (weapon.hasCooledDown()) {
            for (CharacterChangeListener listener : listenerList) {
                listener.handleCharacterAttack(this);

            }
            weapon.startCooldown();
        }
    }

    public void interact() {
        for (CharacterChangeListener listener : listenerList) {
            listener.handleCharacterInteraction(this);
        }
        doInteractCooldown();
    }

    public boolean isAlive() {
        return getHealth() != 0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractCharacter)) {
            return false;
        }

        AbstractCharacter character = (AbstractCharacter) o;

        return (id == character.id);
    }

    @Override
    public final int hashCode() {
        return 31 * id;
    }

    public List<CharacterChangeListener> getListenerList() {
        return listenerList;
    }

    @Override
    public void write(Kryo kryo, Output output) {
        output.writeInt(getId());
        output.writeDouble(unitTile.x);
        output.writeDouble(unitTile.y);
        output.writeDouble(unitTile.width);
        output.writeDouble(unitTile.height);
        
    }

    @Override
    public void read(Kryo kryo, Input input) {
        id = input.readInt();
        double x = input.readInt();
        double y = input.readInt();
        double w = input.readInt();
        double h = input.readInt();
        unitTile = new Rectangle2D.Double(x, y, w, h);
        moveDirection = MoveDirection.NONE;
    }
}
