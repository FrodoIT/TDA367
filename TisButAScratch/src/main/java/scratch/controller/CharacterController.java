/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.network.PacketNewPlayer;
import scratch.view.CharacterView;

/**
 *
 * @author Ivar
 */
public class CharacterController extends Listener {

    private GameCharacter character;
    private final PropertyChangeSupport listeners;
    private final CharacterView view;

    public CharacterController(GameCharacter character) {
        this.character = character;
        listeners = new PropertyChangeSupport(this);
        view = new CharacterView(character);
    }

    public void addListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void update() {
        System.out.println("We are updating a character with reference: " + character);
        character.update();
        
        listeners.firePropertyChange(null, null, character);
    }

    public CharacterView getView() {
        return view;
    }

    public void render(GameContainer gameContainer) {
        System.out.println("We are rendering a character with reference: " + character);
        view.render(gameContainer);
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public int getId() {
        return character.getId();
    }

    @Override
    public synchronized void received(Connection connection, Object object) {
        if (object instanceof GameCharacter){
            GameCharacter recievedCharacter = (GameCharacter)object;
            if (recievedCharacter.getId()==getId()){
                character = recievedCharacter;
            }
        }
    }
}
