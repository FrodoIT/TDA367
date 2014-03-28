package controller;

import model.Direction;
import model.Model;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;

import view.View;

/**
 * Runs the main loop and delegates to the defined Model and View.
 * @author Cannonbait
 *
 */
public class Controller implements Game{
	
	View view;
	Model model;
	
	public Controller(View view, Model model){
		this.view = view;
		this.model = model;
	}

	@Override
	public boolean closeRequested() {
		//TODO Make sure nothing else should be done
		return true;
	}

	@Override
	public String getTitle() {
		return "Tis but a scratch";
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setTargetFrameRate(60);
		
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		view.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		model.update();

	}
}
