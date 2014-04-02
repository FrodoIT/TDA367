package controller;

import model.Model;
import model.MoveDirection;
import model.PlayerInput;

import org.newdawn.slick.Input;
/**
 * A separate controller for all player input. Includes movement, interaction and attacking.
 * In one cycle, input for movement, attack and interaction will be 
 * @author Alma Ottedag
 *
 */
public class PlayerInputController {
	private Model model;


	public PlayerInputController(Model model){
		this.model = model;
	}

	public void update(Input input) {
		PlayerInput playerInput = new PlayerInput();
		if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)){
			//Move South-west
			playerInput.setMoveInput(MoveDirection.SOUTHWEST);
		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move South-east
			playerInput.setMoveInput(MoveDirection.SOUTHEAST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)){
			//Move North-west
			playerInput.setMoveInput(MoveDirection.NORTHWEST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move North-east
			playerInput.setMoveInput(MoveDirection.NORTHEAST);
		} else if(input.isKeyDown(Input.KEY_DOWN)){
			//Move South
			playerInput.setMoveInput(MoveDirection.SOUTH);
		} else if(input.isKeyDown(Input.KEY_UP)){
			//Move North
			playerInput.setMoveInput(MoveDirection.NORTH);
		} else if(input.isKeyDown(Input.KEY_LEFT)){
			//Move West
			playerInput.setMoveInput(MoveDirection.WEST);
		} else if(input.isKeyDown(Input.KEY_RIGHT)){
			//Move East
			playerInput.setMoveInput(MoveDirection.EAST);
		}
		//Attack & Interact commands can be sent at the same time as movement command
		if (input.isKeyDown(Input.KEY_X)){
			playerInput.setAttackInput(true);
		}
		if (input.isKeyDown(Input.KEY_Z)){
			playerInput.setInteractInput(true);
		}
		
		model.queuePlayerInput(playerInput);
		
	}
}
