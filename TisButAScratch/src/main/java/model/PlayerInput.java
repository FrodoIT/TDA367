package model;
public interface PlayerInput {
	public boolean getAttackInput();
	public boolean getInteractInput();
	public MoveDirection getMoveInput();
	public void setAttackInput(boolean attack);
	public void setInteractInput(boolean interact);
	public void setMoveInput(MoveDirection direction);
}
