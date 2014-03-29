package view;


import java.awt.Point;

import model.Model;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The class responsible for rendering the game on the screen.
 * @author Ivar Cannonbait Josefsson
 *
 */
public class View {
	
	Model model;
	
	public View (Model model) {
		this.model = model;
	}
	
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		Point pos = model.getPlayerPosition();
		g.setColor(Color.red);
		g.fillOval(pos.x-5, pos.y-5, 10, 10);
	}

}
