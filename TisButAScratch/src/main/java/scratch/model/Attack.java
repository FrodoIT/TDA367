/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Ivar
 */
public class Attack implements KryoSerializable{
    private Rectangle2D.Double attackTile;
    private Vector2D direction;
    private int damage;
    private Class<?> source;
    
    public Attack (){
        
    }
    
    public Attack (Rectangle2D.Double attackTile, Vector2D direction, int damage, Class<?> source){
        this.attackTile = attackTile;
        this.direction = direction;
        this.damage = damage;
        this.source = source;
    }
    
    public void update (){
        final double x = attackTile.getX() + direction.getX();
        final double y = attackTile.getY() + direction.getY();
        attackTile.setRect(x, y, attackTile.getWidth(), attackTile.getHeight());
    }
    
    public Vector2D getPosition () {
        return new Vector2D(attackTile.getX(), attackTile.getY());
    }
    
    public Rectangle2D.Double getAttackTile(){
        return attackTile;
    }
    
    public boolean dealDamage(Class<?> damageTo){
        if (damageTo != source){
            return true;
        }
        return false;
    }
    
    public int getDamage(){
        return damage;
    }

    @Override
    public void write(Kryo kryo, Output output) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void read(Kryo kryo, Input input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
