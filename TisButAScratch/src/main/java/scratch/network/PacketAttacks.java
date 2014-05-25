/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import java.util.List;
import scratch.model.Attack;

/**
 *
 * @author Ivar
 */
public class PacketAttacks {
    private int id;
    private List<Attack> attacks;
    
    public PacketAttacks(){
        
    }
    
    public PacketAttacks(int id, List<Attack> attacks){
        this.id = id;
        this.attacks = attacks;
    }
    
    public int getId(){
        return id;
    }
    
    public List<Attack> getAttacks(){
        return attacks;
    }
    
}
