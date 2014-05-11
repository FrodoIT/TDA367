package scratch.model.mockModules;

import org.newdawn.slick.Input;
import scratch.model.IPlayerInput;
import scratch.model.MoveDirection;
import scratch.model.Player;

/**
 * Created by pippin on 5/3/14.
 */
public class MockPlayerInput implements IPlayerInput{
    private MoveDirection moveDirection;
    private boolean attack = false;
    private boolean interact = false;
    private final int interactionKey = Input.KEY_Z;
    private final int attackKey = Input.KEY_X;

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
