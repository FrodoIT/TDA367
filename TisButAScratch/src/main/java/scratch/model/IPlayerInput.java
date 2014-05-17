package scratch.model;


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
