/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch.controller;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import scratch.model.GameCharacter;
import scratch.model.NpcType;
import scratch.network.PacketPlayerInput;
import scratch.view.CharacterView;
import scratch.view.NpcView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Ivar
 */
public class CharacterController extends Listener {

    private GameCharacter character;
    private final PropertyChangeSupport listeners;
    private final CharacterView view;

    public CharacterController(final GameCharacter character) {
        this.character = character;
        listeners = new PropertyChangeSupport(this);
        if (character.getClass().equals(NpcType.class)) {
            view = new NpcView((NpcType) character);
        } else {
            view = new CharacterView(character);
        }
    }

    public void addListener(final PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void update() {
        if (character.isAlive()) {
            character.update();
        }
        listeners.firePropertyChange(null, null, character);
    }

    public void render(GameContainer gameContainer) {
        if (character.isAlive()) {
            view.render(gameContainer);
        }

    }

    public GameCharacter getCharacter() {
        return character;
    }

    public int getId() {
        return character.getId();
    }

    public CharacterView getView() {
        return view;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof GameCharacter) {
            GameCharacter recievedCharacter = (GameCharacter) object;
            if (recievedCharacter.getId() == getId()) {
                character.setCharacter(recievedCharacter);

            }
        } else if (object instanceof PacketPlayerInput){
            PacketPlayerInput input = (PacketPlayerInput) object;
            if (input.getId() == character.getId()){
                character.setNextMoveDirection(input.getMovementDirection());
                character.setAttacking(input.getAttacking());
                character.setInteracting(input.getInteracting());
            }
        }
    }
}
