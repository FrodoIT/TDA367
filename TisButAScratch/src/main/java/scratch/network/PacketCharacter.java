/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import scratch.model.AbstractCharacter;
import scratch.model.MoveDirection;
import scratch.model.Vector2D;

/**
 *
 * @author Cannonbait
 */
public class PacketCharacter {
    private Vector2D position;
    private MoveDirection direction;
    private String image;
    
    public PacketCharacter(){
        
    }
    
    public PacketCharacter(AbstractCharacter character){
        position = character.getPosition();
        direction = character.getMoveDirection();
    }
    
    public Vector2D getPosition(){
        return position;
    }
    
    public MoveDirection getMoveDirection(){
        return direction;
    }
}
