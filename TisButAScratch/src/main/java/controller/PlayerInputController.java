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

		if(input.isKeyDown(208) && input.isKeyDown(203)){
			//Move South-west
			model.queuePlayerInput(PlayerCommand.GO_SOUTHWEST);
		} else if(input.isKeyDown(208) && input.isKeyDown(205)){
			//Move South-east
			model.queuePlayerInput(PlayerCommand.GO_SOUTHEAST);
		} else if(input.isKeyDown(200) && input.isKeyDown(203)){
			//Move North-west
			model.queuePlayerInput(PlayerCommand.GO_NORTHWEST);

		} else if(input.isKeyDown(200) && input.isKeyDown(205)){
			//Move North-east
			model.queuePlayerInput(PlayerCommand.GO_NORTHEAST);

		} else if(input.isKeyDown(208)){
			//Move South
			model.queuePlayerInput(PlayerCommand.GO_SOUTH);

		} else if(input.isKeyDown(200)){
			//Move North
			model.queuePlayerInput(PlayerCommand.GO_NORTH);

		} else if(input.isKeyDown(203)){
			//Move West
			model.queuePlayerInput(PlayerCommand.GO_WEST);
		} else if(input.isKeyDown(205)){
			//Move East
			model.queuePlayerInput(PlayerCommand.GO_EAST);
		}
		//TODO: Should be possible to attack at the same time as moving.
		else if(input.isKeyDown(44)){
			model.queuePlayerInput(PlayerCommand.INTERACT);
			//Z for interact.
		} else if(input.isKeyDown(45)){
			model.queuePlayerInput(PlayerCommand.ATTACK);
			//X for attack
		}

	}

}
