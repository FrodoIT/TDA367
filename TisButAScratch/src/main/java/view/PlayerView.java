package view;

import java.awt.Point;

import model.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * 
 * @author André Samuelsson
 *
 */

public class PlayerView {
	
	private Player player;
	
	public PlayerView(Graphics g, Player player) {
		this.player = player;
	}
	
	public void render(GameContainer c, Graphics g) {
		Point p = player.getPosition();
		g.fillOval(p.x -5 , p.y -5, 10, 10);
	}
	

}
