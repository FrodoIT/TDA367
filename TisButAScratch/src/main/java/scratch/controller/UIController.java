package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.view.CharacterView;
import scratch.view.UIView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Anna on 2014-05-19.
 */
public class UIController extends Listener {
	private PropertyChangeSupport listeners;
	private GameCharacter character;
	private final UIView view;

	public UIController(GameCharacter character) {
		listeners = new PropertyChangeSupport(this);
		this.character= character;
		view = new UIView(character);
	}

	public void render(GameContainer gameContainer){
		view.render(gameContainer);
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public int getId() {
		return character.getId();
	}

	public UIView getView() {
		return view;
	}
}
