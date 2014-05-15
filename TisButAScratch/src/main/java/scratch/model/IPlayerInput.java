package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.controller.PlayerController;

@ImplementedBy(PlayerController.class)
public interface IPlayerInput {

    boolean isAttacking();
    void registerAllInput();
    boolean isInteracting();
    MoveDirection getMoveInput();
    void setAttackStatus(boolean attack);
    void setInteractStatus(boolean interact);
    void setMoveDirection(MoveDirection direction);
    void resetInput();
}
