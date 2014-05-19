package scratch.controller;

import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.view.NpcView;
import scratch.view.PlayerView;

/**
 * Created by Anna on 2014-05-19.
 */
public class PlayerController extends CharacterController{
		private final PlayerView view;

	public PlayerController(GameCharacter player){
		super(player);
		view= new PlayerView(player);

	}
	public PlayerView getView() {
		return view;
	}

	@Override
	public void render(GameContainer gameContainer) {
		view.render(gameContainer);

	}

	protected void setCharacter(GameCharacter character) {
		super.setCharacter(character);
		view.setCharacter(character);
	}
}
