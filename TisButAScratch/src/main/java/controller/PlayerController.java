package controller;

import model.Model;
import model.MoveDirection;
import model.Player;
import model.PlayerInput;

import org.newdawn.slick.Input;

import view.View;

/**
 * Class to collect input for the player.
 * @author Anna Nylander
 *
 */
public class PlayerController implements PlayerInput{
	private Model model;
	private Player player;
	private MoveDirection moveDirection;
	private boolean attack, interact;
	
	public PlayerController(Model model){
		this.model=model;
		addPlayer();
	}
	
	private void addPlayer(){
		model.addPlayer(this);
	}
	
	public void removePlayer(){
		model.removePlayer(player);
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
		this.attack=attack;
	}
	public void setInteractInput(boolean interact){
		this.interact=interact;
	}
	public void setMoveInput(MoveDirection direction){
		this.moveDirection=direction;
	}
	
	public boolean getAttackInput(){
		return attack;
	}
	public boolean getInteractInput(){
		return interact;
	}
	public MoveDirection getMoveInput(){
		return moveDirection;
	}
}
