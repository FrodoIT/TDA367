package scratch.model;
public interface IPlayerInput {
	public boolean getAttackInput();
	public boolean getInteractInput();
	public MoveDirection getMoveInput();
	public void setAttackInput(boolean attack);
	public void setInteractInput(boolean interact);
	public void setMoveInput(MoveDirection direction);
        public void resetInput();
}
