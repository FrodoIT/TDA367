package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import scratch.model.weapons.IWeapon;
import scratch.utils.Cooldown;

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
public class GameCharacter implements KryoSerializable {

    @Element(type = Rectangle2D.Double.class, required = false)
    private Rectangle2D.Double unitTile = new Rectangle2D.Double(0, 0, 32, 32);
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
    @Element (required = false)
    private boolean interactIsCooledDown = true;

    private Vector2D nextMoveDirection;
    private boolean interacting;
    private boolean attacking;

    @Element (type=Runnable.class, required = false)
    private Runnable cooldownReset = new Runnable() {
        @Override
        public void run() {
            interactIsCooledDown = true;
        }
    };

    final private List<CharacterChangeListener> listeners = new ArrayList<>();

    public GameCharacter(Rectangle2D.Double unitTile, IWeapon weapon, int health, int movementSpeed, int id, String imagePath) {
        this.unitTile = new Rectangle2D.Double(unitTile.getX(), unitTile.getY(), unitTile.getWidth(), unitTile.getHeight());
        this.weapon = weapon;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.id = id;
        this.imagePath = imagePath;
        moveDirection = MoveDirection.SOUTH;
        nextMoveDirection = new Vector2D();
    }

    public void registerListener(CharacterChangeListener listener) {
        listeners.add(listener);
    }

    GameCharacter() {
        super();
        nextMoveDirection = new Vector2D();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void performInteractCooldown() {
        interactIsCooledDown = false;
        Cooldown.cooldown(500, cooldownReset);
    }

    public void removeListener(CharacterChangeListener listener) {
        listeners.remove(listener);
    }

    public void takeDamage(int dmg) {
        health = health - Math.abs(dmg);

        if (Math.abs(dmg) > health) {
            health = 0;
        }
    }

    private void calculateMoveDirection(Vector2D newPosition) {
        if (getPosition().equals(newPosition)) {
            moveDirection = MoveDirection.NONE;
            return;
        }

        final Rectangle2D.Double unitTile = getUnitTile();
        final double diffX = newPosition.getX() - unitTile.x;
        final double diffY = newPosition.getY() - unitTile.y;

        // 517.5 =
        // 180 to avid negative angles
        //+ 337.5 (360 - 22.5)
        final double theta = (Math.toDegrees(Math.atan2(diffX, diffY)) + 517.5) % 360;

        if (Double.isNaN(theta)) {
            return;
        }

        final MoveDirection[] directions = {
                MoveDirection.NORTHWEST,
                MoveDirection.WEST,
                MoveDirection.SOUTHWEST,
                MoveDirection.SOUTH,
                MoveDirection.SOUTHEAST,
                MoveDirection.EAST,
                MoveDirection.NORTHEAST,
                MoveDirection.NORTH
        };
        moveDirection = directions[(int)theta/45];
    }

    public void update() {
        Vector2D newPosition = calculateNewPosition();
	    calculateMoveDirection(newPosition);

        for (CharacterChangeListener listener : getListeners()) {
            listener.handleCharacterMovement(this, newPosition);

            if (isInteracting()) {
                interact();
            }

            if (isPromptingAnAttack()) {
                performAttack();
            }
        }
    }

    protected Vector2D calculateNewPosition() {
        return new Vector2D(
                getPosition().getX() + nextMoveDirection.getX() * movementSpeed,
                getPosition().getY() + nextMoveDirection.getY() * movementSpeed
        );
    }

    public void setPosition(Vector2D position) {
	    unitTile.setRect(position.getX(), position.getY(), unitTile.getWidth(), unitTile.getHeight());
    }

    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void setNextMoveDirection(Vector2D moveDirection) {
        this.nextMoveDirection = moveDirection.getNormalisedVector();
    }

    public Vector2D getNextMoveDirection() {
        return nextMoveDirection;
    }

    public boolean isInteracting() {
        return interacting && interactIsCooledDown;
    }

    public boolean isPromptingAnAttack() {
        return attacking && getWeapon().hasCooledDown();
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
	  // System.out.println("I get position" + new Vector2D(unitTile.getX(), unitTile.getY()));
	    return new Vector2D(unitTile.getX(), unitTile.getY());
    }

    public Rectangle2D.Double getAttackArea() {
        int range = weapon.getRange();
        return new Rectangle2D.Double(
                unitTile.x + (32 * range * moveDirection.getX()),
                unitTile.y + (32 * range * moveDirection.getY()),
                weapon.getAttackArea().width, weapon.getAttackArea().height);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void performAttack() {
        if (weapon.hasCooledDown()) {
            for (CharacterChangeListener listener : listeners) {
                listener.handleCharacterAttack(this);

            }
            weapon.startCooldown();
        }
    }

    public void interact() {
        for (CharacterChangeListener listener : listeners) {
            listener.handleCharacterInteraction(this);
        }
        performInteractCooldown();
    }

    public boolean isAlive() {
        return getHealth() != 0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameCharacter)) {
            return false;
        }

        GameCharacter character = (GameCharacter) o;

        return (id == character.id);
    }

    @Override
    public final int hashCode() {
        return 31 * id;
    }

    public List<CharacterChangeListener> getListeners() {
        return listeners;
    }

    @Override
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, unitTile);
        //kryo.writeObject(output, weapon);
        kryo.writeObject(output, health);
        kryo.writeObject(output, movementSpeed);
        kryo.writeObject(output, id);
        kryo.writeObject(output, moveDirection);
        kryo.writeObject(output, imagePath);
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void read(Kryo kryo, Input input) {
        unitTile = kryo.readObject(input, Rectangle2D.Double.class);
        //weapon = kryo.readObject(input, IWeapon.class);
        health = kryo.readObject(input, Integer.class);
        movementSpeed = kryo.readObject(input, Integer.class);
        id = kryo.readObject(input, Integer.class);
        moveDirection = kryo.readObject(input, MoveDirection.class);
        imagePath = kryo.readObject(input, String.class);
    }
}
