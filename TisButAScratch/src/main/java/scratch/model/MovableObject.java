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
public class MovableObject extends InteractiveObject implements IMovableEntity, KryoSerializable{
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
}
