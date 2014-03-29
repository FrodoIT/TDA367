package model;

public class PlayerInput {
	private boolean attackInput;
	private boolean interactInput;
	private MoveCommand moveInput;
	
	public PlayerInput() {
		attackInput = false;
		interactInput = false;
		moveInput = MoveCommand.NONE;
	}
	
	public boolean getAttackInput() {
		return attackInput;
	}
	
	public boolean getInteractInput() {
		return interactInput;
	}
	
	public MoveCommand getMoveInput() {
		return moveInput;
	}
	
	public void setAttackInput(boolean attackValue) {
		attackInput = attackValue;
	}
	
	public void setInteractInput(boolean interactValue) {
		interactInput = interactValue;
	}
	
	public void setMoveInput(MoveCommand moveValue) {
		moveInput = moveValue;
	}
}
