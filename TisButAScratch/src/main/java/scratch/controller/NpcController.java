package scratch.controller;

import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.model.NpcType;
import scratch.view.NpcView;

/**
 * Created by Anna on 2014-05-19.
 */
public class NpcController extends CharacterController{
	private final NpcView view;

	public NpcController(NpcType character){
		super(character);
		view = new NpcView(character);

	}
	public NpcView getView() {
		return view;
	}

	@Override
	public void render(GameContainer gameContainer) {
		view.render(gameContainer);
	}

	protected void setCharacter(NpcType character) {
		super.setCharacter(character);
		view.setCharacter(character);
	}
}
