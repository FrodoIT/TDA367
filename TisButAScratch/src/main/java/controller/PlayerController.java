package controller;

import model.MoveDirection;
import model.Player;
import model.PlayerInput;

import org.newdawn.slick.Input;

/**
 * 
 * @author Alma Ottedag
 *
 */
public class PlayerController implements PlayerInput{
	private Controller controller;
	private Player player;
	private MoveDirection moveDirection;
	private boolean attack, interact;
	
	public PlayerController(){
		
	}
	
	public void registerInput(Input input){
		if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)){
			//Move South-west
			setMoveInput(MoveDirection.SOUTHWEST);
		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move South-east
			setMoveInput(MoveDirection.SOUTHEAST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)){
			//Move North-west
			setMoveInput(MoveDirection.NORTHWEST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move North-east
			setMoveInput(MoveDirection.NORTHEAST);
		} else if(input.isKeyDown(Input.KEY_DOWN)){
			//Move South
			setMoveInput(MoveDirection.SOUTH);
		} else if(input.isKeyDown(Input.KEY_UP)){
			//Move North
			setMoveInput(MoveDirection.NORTH);
		} else if(input.isKeyDown(Input.KEY_LEFT)){
			//Move West
			setMoveInput(MoveDirection.WEST);
		} else if(input.isKeyDown(Input.KEY_RIGHT)){
			//Move East
			setMoveInput(MoveDirection.EAST);
		}
		//Attack & Interact commands can be sent at the same time as movement command
		if (input.isKeyDown(Input.KEY_X)){
			setAttackInput(true);
		}
		if (input.isKeyDown(Input.KEY_Z)){
			setInteractInput(true);
		}
		
		
	}
	public void setAttackInput(boolean attack){
	//TODO finish method	
	}
	public void setInteractInput(boolean interact){
		//TODO finish method	
	}
	public void setMoveInput(MoveDirection direction){
		//TODO finish method
	}
	
	public boolean getAttackInput(){
		//TODO finish method
	}
	public boolean getInteractInput(){
		//TODO finish method
	}
	public MoveDirection getMoveInput(){
		//TODO finish method
	}
}
