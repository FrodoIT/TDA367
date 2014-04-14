package scratch.model.weapons;

import java.awt.geom.Rectangle2D;
import java.util.Date;

/**
 * The weapon DefaultWeapon:
 The default starting-weapon for all characters with the following stats:
 Damage = 2
 Range = 1
 * @author Alma Ottedag
 */
public final class DefaultWeapon implements IWeapon {
    private final int damage;
    private final int range;
    private final Rectangle2D.Double attackArea;
    //Minimum time between attacks in milliseconds
    private final int attackInterval;
    private Date lastAttack;
    private Date thisMoment;

    public DefaultWeapon(){
        damage = 2;
        range = 1;
        attackArea = new Rectangle2D.Double(0, 0, 3, 3);
        lastAttack = new Date();
        attackInterval = 200;
    }
    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public Rectangle2D.Double getAttackArea(){
        return (Rectangle2D.Double)attackArea.clone();
    }

    /**
     * Tell the weapon to execute an attack
     * @return true if attack was done
     */
    @Override
    public boolean attack(){
        if (hasCooledDown()){
            lastAttack = thisMoment;
            return true;
        }
        return false;
    }

    private boolean hasCooledDown(){
        thisMoment = new Date();
        return Math.abs(lastAttack.getTime() - thisMoment.getTime()) > attackInterval;
    }

}
