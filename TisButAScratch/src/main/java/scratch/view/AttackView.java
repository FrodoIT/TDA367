/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.view;

import java.awt.geom.Rectangle2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import scratch.model.Attack;

/**
 *
 * @author Ivar
 */
public class AttackView {

    public void render(GameContainer gameContainer, Attack attack) {
        Rectangle2D area = attack.getAttackTile();
        Graphics graphics = gameContainer.getGraphics();
        graphics.setColor(Color.red);
        graphics.fill(new Rectangle((int) area.getX(), (int) area.getY(), (int) area.getWidth(), (int) area.getHeight()));

    }
}
