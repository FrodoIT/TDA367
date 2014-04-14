package scratch.model;
public interface IPlayerInput {
	public boolean getAttackInput();
	public boolean getInteractInput();
	public MoveDirection getMoveInput();
	public void setAttackStatus(boolean attack);
	public void setInteractStatus(boolean interact);
	public void setMoveDirection(MoveDirection direction);
        public void resetInput();
}
