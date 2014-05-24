package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.Map;

/**
 * Created by Anna on 2014-05-23.
 */
public final class MovableObject extends InteractiveObject implements IMovableEntity {

    private MoveDirection moveDirection;

    public MovableObject(String name, String type, int x, int y, int width, int height, Map<String, String> properties) {
        super(name, type, x, y, width, height, properties);
        moveDirection = MoveDirection.NONE;
    }

    public MovableObject() {

    }

    @Override
    public MoveDirection getMoveDirection() {
        return moveDirection;
    }

    @Override
    public void setMoveDirection(MoveDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, moveDirection);
    }

    @Override
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        moveDirection = kryo.readObject(input, MoveDirection.class);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object == null || object.getClass() != MovableObject.class) {
            return false;
        }
        final MovableObject recievedObject = (MovableObject) object;
        return super.equals(recievedObject) && (this.getMoveDirection() == recievedObject.getMoveDirection());
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (moveDirection != null ? moveDirection.hashCode() : 0);

    }
}
