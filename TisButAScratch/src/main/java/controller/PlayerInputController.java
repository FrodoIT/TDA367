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
		if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)){
			//Move South-west
			model.queuePlayerInput(MoveCommand.SOUTHWEST);
		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move South-east
			model.queuePlayerInput(MoveCommand.SOUTHEAST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)){
			//Move North-west
			model.queuePlayerInput(MoveCommand.NORTHWEST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move North-east
			model.queuePlayerInput(MoveCommand.NORTHEAST);
		} else if(input.isKeyDown(Input.KEY_DOWN)){
			//Move South
			model.queuePlayerInput(MoveCommand.SOUTH);
		} else if(input.isKeyDown(Input.KEY_UP)){
			//Move North
			model.queuePlayerInput(MoveCommand.NORTH);
		} else if(input.isKeyDown(Input.KEY_LEFT)){
			//Move West
			model.queuePlayerInput(MoveCommand.WEST);
		} else if(input.isKeyDown(Input.KEY_RIGHT)){
			//Move East
			model.queuePlayerInput(MoveCommand.EAST);
		}
	}
}
