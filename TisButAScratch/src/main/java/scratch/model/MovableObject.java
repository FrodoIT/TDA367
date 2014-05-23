package scratch.model;

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

	public MoveDirection getMoveDirection(){
		return moveDirection;
	}


	public void setMoveDirection(MoveDirection moveDirection){
		this.moveDirection=moveDirection;
	}

}
