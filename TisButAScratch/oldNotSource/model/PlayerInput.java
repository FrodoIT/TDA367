package model;

public class PlayerInput {
	private boolean attackInput;
	private boolean interactInput;
	private MoveDirection moveInput;
	
	public PlayerInput() {
		attackInput = false;
		interactInput = false;
		moveInput = MoveDirection.NONE;
	}
	
	public boolean getAttackInput() {
		return attackInput;
	}
	
	public boolean getInteractInput() {
		return interactInput;
	}
	
	public MoveDirection getMoveInput() {
		return moveInput;
	}
	
	public void setAttackInput(boolean attackValue) {
		attackInput = attackValue;
	}
	
	public void setInteractInput(boolean interactValue) {
		interactInput = interactValue;
	}
	
	public void setMoveInput(MoveDirection moveValue) {
		moveInput = moveValue;
	}
	
	public void resetInput() {
		attackInput = false;
		interactInput = false;
		moveInput = MoveDirection.NONE;
	}
}
