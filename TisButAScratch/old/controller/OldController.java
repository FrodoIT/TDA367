package controller;

import model.MoveCommand;
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
public class OldController implements Game{
	private View view;
	private Model model;
	private PlayerInputController inputController;
	private RoomController roomController;
	
	public OldController(View view, Model model){
		this.view = view;
		this.model = model;
		inputController = new PlayerInputController(model);
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
		roomController = new RoomController(model, view, "res/spawn.tmx");
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		roomController.render();
		view.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		inputController.update(container.getInput());
		model.update();

	}

}
