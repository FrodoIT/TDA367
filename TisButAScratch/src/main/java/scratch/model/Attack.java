/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.model;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author Ivar
 */
public class Attack implements IMovableEntity{
    private Rectangle2D.Double attackTile;
    private Vector2D direction;
    private int damage;
    private Class<?> source;
    private boolean remain = true;
    
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
            remain = false;
            return true;
        }
        return false;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public boolean isRemaining(){
        return remain;
    }

    @Override
    public Direction getMoveDirection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMoveDirection(Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPosition(Vector2D newPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D.Double getTile() {
        return attackTile;
    }

}
