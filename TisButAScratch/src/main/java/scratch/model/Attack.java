/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.model;

import java.awt.geom.Rectangle2D;
import scratch.model.weapons.Weapon;

/**
 *
 * @author Ivar
 */
public class Attack implements IMovableEntity{
    private Vector2D startPosition;
    private Rectangle2D.Double attackTile;
    private Vector2D direction;
    private int damage;
    private Class<?> source;
    private boolean remain = true;
    private int range;
    
    public Attack (){
        
    }
    
    public Attack (Rectangle2D.Double attackTile, Vector2D direction, Weapon weapon, Class<?> source){
        this.startPosition = new Vector2D(attackTile.getX(), attackTile.getY());
        this.attackTile = attackTile;
        this.direction = direction;
        this.damage = weapon.getDamage();
        this.source = source;
        this.range = weapon.getRange();
    }
    
    public void update (){
        final double x = attackTile.getX() + direction.getX();
        final double y = attackTile.getY() + direction.getY();
        if (Math.abs(startPosition.getX() - x) > range*32 ||
            Math.abs(startPosition.getY() - y) > range*32){
            remain = false;
        }
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
        attackTile.setRect(newPosition.getX(), newPosition.getY(), attackTile.getWidth(), attackTile.getHeight());
    }

    @Override
    public Rectangle2D.Double getTile() {
        return attackTile;
    }

}
