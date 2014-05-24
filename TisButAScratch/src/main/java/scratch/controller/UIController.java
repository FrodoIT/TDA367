package scratch.controller;

import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.view.UIView;

/**
 * Created by Anna on 2014-05-19.
 */
public class UIController extends Listener {
	private final UIView view;

	public UIController(GameCharacter character) {
        super();
		view = new UIView(character);
	}

	public void render(GameContainer gameContainer){
		view.render(gameContainer);
	}

	public UIView getView() {
		return view;
	}
}
