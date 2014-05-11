/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.network;

import scratch.model.Model;

/**
 *
 * @author Ivar
 */
public class PacketModel {
    private Model model;
    
    //Kryonet requires an empty constructor
    public PacketModel (){
        model = new Model();
    }
    
    public PacketModel (Model model){
        this.model = model;
    }
    
    public Model getModel(){
        return model;
    }
}
