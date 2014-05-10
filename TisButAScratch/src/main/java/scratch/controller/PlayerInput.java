package scratch.controller;

import org.newdawn.slick.Input;
import scratch.model.IPlayerInput;
import scratch.model.MoveDirection;

public class PlayerInput implements IPlayerInput {
    private final int interactionKey = Input.KEY_Z;
    private final int attackKey = Input.KEY_X;
    private final Input input;

    private MoveDirection moveDirection = MoveDirection.NONE;
    private boolean attack, interact;

    PlayerInput(Input input) {
        this.input = input;
    }

    @Override
    public void registerAllInput(){
        registerMoveInput(input);
        registerAttackInput(input);
        registerInteractInput(input);
    }

    private void registerMoveInput(Input input) {
        //TODO: I'm not sure this is the best way to do it...
        final boolean southKey = input.isKeyDown(Input.KEY_DOWN);
        final boolean westKey = input.isKeyDown(Input.KEY_LEFT);
        final boolean eastKey = input.isKeyDown(Input.KEY_RIGHT);
        final boolean northKey = input.isKeyDown(Input.KEY_UP);

        if (southKey && westKey) {
            setMoveDirection(MoveDirection.SOUTHWEST);

        } else if (southKey && eastKey) {
            setMoveDirection(MoveDirection.SOUTHEAST);

        } else if (northKey && westKey) {
            setMoveDirection(MoveDirection.NORTHWEST);

        } else if (northKey && eastKey) {
            setMoveDirection(MoveDirection.NORTHEAST);

        } else if (southKey) {
            setMoveDirection(MoveDirection.SOUTH);

        } else if (northKey) {
            setMoveDirection(MoveDirection.NORTH);

        } else if (westKey) {
            setMoveDirection(MoveDirection.WEST);

        } else if (eastKey) {
            setMoveDirection(MoveDirection.EAST);

        } else {
            setMoveDirection(MoveDirection.NONE);
        }
    }


    private void registerInteractInput(Input input) {
        setInteractStatus(input.isKeyDown(interactionKey));

    }

    private void registerAttackInput(Input input) {
        setAttackStatus(input.isKeyDown(attackKey));
    }

    @Override
    public boolean isAttacking() {
        return attack;
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

