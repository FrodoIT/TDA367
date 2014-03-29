package controller;

import model.Model;
import model.PlayerCommand;

import org.newdawn.slick.Input;

public class PlayerInputController {
	private Model model;


	public PlayerInputController(Model model){
		this.model = model;
	}

	public void update(Input input) {

		if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)){
			//Move South-west
			model.queuePlayerInput(PlayerCommand.GO_SOUTHWEST);
		} else if(input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move South-east
			model.queuePlayerInput(PlayerCommand.GO_SOUTHEAST);
		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)){
			//Move North-west
			model.queuePlayerInput(PlayerCommand.GO_NORTHWEST);

		} else if(input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)){
			//Move North-east
			model.queuePlayerInput(PlayerCommand.GO_NORTHEAST);

		} else if(input.isKeyDown(Input.KEY_DOWN)){
			//Move South
			model.queuePlayerInput(PlayerCommand.GO_SOUTH);

		} else if(input.isKeyDown(Input.KEY_UP)){
			//Move North
			model.queuePlayerInput(PlayerCommand.GO_NORTH);

		} else if(input.isKeyDown(Input.KEY_LEFT)){
			//Move West
			model.queuePlayerInput(PlayerCommand.GO_WEST);
		} else if(input.isKeyDown(Input.KEY_RIGHT)){
			//Move East
			model.queuePlayerInput(PlayerCommand.GO_EAST);
		}
		//TODO: Should be possible to attack at the same time as moving.
		else if(input.isKeyDown(Input.KEY_Z)){
			model.queuePlayerInput(PlayerCommand.INTERACT);
			//Z for interact.
		} else if(input.isKeyDown(Input.KEY_X)){
			model.queuePlayerInput(PlayerCommand.ATTACK);
			//X for attack
		}

	}

}
