/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.view.CharacterView;

/**
 *
 * @author Ivar
 */
public class CharacterController {
    
    private GameCharacter character;
    private final PropertyChangeSupport listeners;
    private final CharacterView view;
    
    public CharacterController(GameCharacter character){
        this.character = character;
        listeners = new PropertyChangeSupport(this);
        view = new CharacterView(character);
    }

    public void addListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void update() {
        character.update();
        listeners.firePropertyChange(null, null, character);
    }

    public void setCharacter(GameCharacter character) {
        view.setCharacter(character);
        this.character = character;
    }

    public CharacterView getView() {
        return view;
    }

    public void render(GameContainer gameContainer) {
        view.render(gameContainer);
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public int getId() {
        return character.getId();
    }
}
