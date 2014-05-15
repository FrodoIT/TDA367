package scratch.model.mockmodules;

import scratch.model.IPlayerInput;
import scratch.model.MoveDirection;

/**
 * Created by pippin on 5/3/14.
 */
public class MockPlayerInput implements IPlayerInput{
    private MoveDirection moveDirection = MoveDirection.EAST;
    private boolean attack = false;
    private boolean interact = false;


    @Override
    public boolean isAttacking() {
        return attack;
    }

    @Override
    public void registerAllInput() {

    }

    @Override
    public boolean isInteracting() {
        return interact;
    }

    @Override
    public MoveDirection getMoveInput() {
        return moveDirection;
    }

    @Override
    public void setAttackStatus(boolean attack) {
        this.attack = attack;
    }

    @Override
    public void setInteractStatus(boolean interact) {
        this.interact = interact;
    }

    @Override
    public void setMoveDirection(MoveDirection direction) {
        this.moveDirection = direction;
    }

    @Override
    public void resetInput() {
        attack = false;
        interact = false;
        moveDirection = MoveDirection.NONE;
    }
}
