package controller;

import model.Model;
import model.MoveCommand;

import org.newdawn.slick.Input;

public class PlayerInputController {
	private Model model;


	public PlayerInputController(Model model){
		this.model = model;
	}

	public void update(Input input) {
		if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)){
			//Move South-west
			model.queuePlayerInput(MoveCommand.GO_SOUTHWEST);
		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move South-east
			model.queuePlayerInput(MoveCommand.GO_SOUTHEAST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)){
			//Move North-west
			model.queuePlayerInput(MoveCommand.GO_NORTHWEST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move North-east
			model.queuePlayerInput(MoveCommand.GO_NORTHEAST);
		} else if(input.isKeyDown(Input.KEY_DOWN)){
			//Move South
			model.queuePlayerInput(MoveCommand.GO_SOUTH);
		} else if(input.isKeyDown(Input.KEY_UP)){
			//Move North
			model.queuePlayerInput(MoveCommand.GO_NORTH);
		} else if(input.isKeyDown(Input.KEY_LEFT)){
			//Move West
			model.queuePlayerInput(MoveCommand.GO_WEST);
		} else if(input.isKeyDown(Input.KEY_RIGHT)){
			//Move East
			model.queuePlayerInput(MoveCommand.GO_EAST);
		}
	}
}
