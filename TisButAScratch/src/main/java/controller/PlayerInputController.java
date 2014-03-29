package controller;

import model.Model;
import model.MoveCommand;
import model.PlayerInput;

import org.newdawn.slick.Input;

public class PlayerInputController {
	private Model model;


	public PlayerInputController(Model model){
		this.model = model;
	}

	public void update(Input input) {
		PlayerInput playerInput = new PlayerInput();
		if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)){
			//Move South-west
			playerInput.setMoveInput(MoveCommand.SOUTHWEST);
		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move South-east
			playerInput.setMoveInput(MoveCommand.SOUTHEAST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)){
			//Move North-west
			playerInput.setMoveInput(MoveCommand.NORTHWEST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move North-east
			playerInput.setMoveInput(MoveCommand.NORTHEAST);
		} else if(input.isKeyDown(Input.KEY_DOWN)){
			//Move South
			playerInput.setMoveInput(MoveCommand.SOUTH);
		} else if(input.isKeyDown(Input.KEY_UP)){
			//Move North
			playerInput.setMoveInput(MoveCommand.NORTH);
		} else if(input.isKeyDown(Input.KEY_LEFT)){
			//Move West
			playerInput.setMoveInput(MoveCommand.WEST);
		} else if(input.isKeyDown(Input.KEY_RIGHT)){
			//Move East
			playerInput.setMoveInput(MoveCommand.EAST);
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
