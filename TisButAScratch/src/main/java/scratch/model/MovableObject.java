package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;

/**
 * Created by Anna on 2014-05-23.
 */
public class MovableObject extends InteractiveObject implements IMovableEntity{
	MoveDirection moveDirection;

	public MovableObject(String name, String type, int x, int y, int width, int height, HashMap<String, String> properties){
		super(name, type, x,y,width,height,properties);
		moveDirection=MoveDirection.NONE;
	}
    public MovableObject() {

    }

	public MoveDirection getMoveDirection(){
		return moveDirection;
	}


	public void setMoveDirection(MoveDirection moveDirection){
		this.moveDirection=moveDirection;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MovableObject that = (MovableObject) o;

        return (moveDirection != that.moveDirection);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (moveDirection != null ? moveDirection.hashCode() : 0);
        return result;
    }
}
