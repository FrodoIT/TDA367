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
	public void addListener(final PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	public void update() {
		//doesn't need to do anything here yet. made to simplify for future implementation
		listeners.firePropertyChange(null, null, character);
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

	protected void setCharacter(GameCharacter character) {
		this.character = character;
		view.setCharacter(character);
	}

	public UIView getView() {
		return view;
	}

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof GameCharacter) {
			GameCharacter recievedCharacter = (GameCharacter) object;
			if (recievedCharacter.getId() == getId()) {
				setCharacter(recievedCharacter);
			}
		}
	}
}
