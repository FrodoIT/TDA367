package scratch.controller;

import scratch.model.IPlayerInput;
import scratch.model.Model;
import scratch.model.MoveDirection;
import scratch.model.Player;

import org.newdawn.slick.Input;

import scratch.view.View;

/**
 * Class to collect input for the player.
 * @author Anna Nylander
 *
 */
public final class PlayerController implements IPlayerInput {
    private final Model model;
    private final View view;
    private Player player;
    private MoveDirection moveDirection = MoveDirection.NONE;
    private boolean attack, interact;
    private final int interactionKey = Input.KEY_Z;
    private final int attackKey = Input.KEY_X;

    public PlayerController(Model model, View view){
        this.model=model;
        this.view = view;
        addPlayer();
    }

    private void addPlayer(){
        player=model.addPlayer(this);
        view.addPlayerView(player);
    }

    public void removePlayer(){
        model.removePlayer(player);
    }


    public void registerAllInput(Input input){
        if(player.alive()){
            registerMoveInput(input);
            registerAttackInput(input);
            registerInteractInput(input);
        } else {
            resetInput();
        }

    }

    private void registerInteractInput(Input input) {
        setInteractStatus(input.isKeyDown(interactionKey));

    }

    private void registerAttackInput(Input input) {
        setAttackStatus(input.isKeyDown(attackKey));
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




    @Override
    public void setAttackStatus(boolean attack){
        this.attack=attack;
    }
    @Override
    public void setInteractStatus(boolean interact){
        this.interact=interact;
    }
    @Override
    public void setMoveDirection(MoveDirection direction){
        this.moveDirection=direction;
    }

    @Override
    public boolean getAttackInput(){
        return attack;
    }
    @Override
    public boolean getInteractInput(){
        return interact;
    }
    @Override
    public MoveDirection getMoveInput(){
        return moveDirection;
    }

    @Override
    public void resetInput(){
        attack = false;
        interact = false;
        moveDirection = MoveDirection.NONE;
    }


}
