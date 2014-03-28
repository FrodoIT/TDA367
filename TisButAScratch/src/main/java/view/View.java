package view;


import java.awt.Point;

import model.Model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class View {
	
	Model model;
	
	public View (Model model) {
		this.model = model;
	}
	
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		Point pos = model.getPlayerPosition();
		g.drawString("Gubbe", (float)pos.getX(), (float)pos.getY());
	}

}
