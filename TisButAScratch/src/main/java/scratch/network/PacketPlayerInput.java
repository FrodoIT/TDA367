/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import org.newdawn.slick.Input;
import scratch.model.MoveDirection;
import scratch.model.Vector2D;

/**
 *
 * @author Ivar
 */
public class PacketPlayerInput {
    private int id;
    private Vector2D movementDirection;
    private boolean attacking, interacting;
    
    public PacketPlayerInput(){
        
    }
    
    public PacketPlayerInput(int id, Input input){
        this.id = id;
        calculateMovementDirection(input);
        setAttack(input);
        setInteract(input);
    }
    
    public int getId(){
        return id;
    }
    
    public Vector2D getMovementDirection(){
        return movementDirection;
    }
    
    public boolean getAttacking(){
        return attacking;
    }
    
    public boolean getInteracting(){
        return interacting;
    }
    
    private void calculateMovementDirection(Input input){
        final boolean southKey = input.isKeyDown(Input.KEY_DOWN);
        final boolean westKey = input.isKeyDown(Input.KEY_LEFT);
        final boolean eastKey = input.isKeyDown(Input.KEY_RIGHT);
        final boolean northKey = input.isKeyDown(Input.KEY_UP);

        if (southKey && westKey) {
            //Southwest
            movementDirection = new Vector2D(-1, -1);

        } else if (southKey && eastKey) {
            movementDirection = new Vector2D(1, -1);

        } else if (northKey && westKey) {
            movementDirection = new Vector2D(-1, 1);

        } else if (northKey && eastKey) {
            movementDirection = new Vector2D(1, 1);

        } else if (southKey) {
            movementDirection = new Vector2D(0, -1);

        } else if (northKey) {
            movementDirection = new Vector2D(0, 1);
        } else if (westKey) {
            movementDirection = new Vector2D(-1, 0);
        } else if (eastKey) {
            movementDirection = new Vector2D(1, 0);
        } else {
            movementDirection = new Vector2D(0, 0);
        }
    }
    
    private void  setAttack(Input input){
        attacking = input.isKeyDown(Input.KEY_X);
    }
    
    private void setInteract(Input input){
        interacting = input.isKeyDown(Input.KEY_Z);
    }
    
    
}
