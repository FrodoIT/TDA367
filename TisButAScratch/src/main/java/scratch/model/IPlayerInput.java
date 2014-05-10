package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.controller.PlayerController;

@ImplementedBy(PlayerController.class)
public interface IPlayerInput {
	public boolean isAttacking();
    public void registerAllInput();
	public boolean isInteracting();
	public MoveDirection getMoveInput();
	public void setAttackStatus(boolean attack);
	public void setInteractStatus(boolean interact);
	public void setMoveDirection(MoveDirection direction);
    public void resetInput();
}
