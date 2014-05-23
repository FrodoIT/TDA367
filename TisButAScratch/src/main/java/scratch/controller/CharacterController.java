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
import scratch.model.NpcType;
import scratch.network.NetworkClient;
import scratch.network.NetworkServer;
import scratch.network.PacketPlayerInput;
import scratch.view.CharacterView;
import scratch.view.NpcView;

/**
 *
 * @author Ivar
 */
public class CharacterController extends Listener {

    private final GameCharacter character;
    private NetworkServer server;
    private final PropertyChangeSupport listeners;
    private final CharacterView view;

    public CharacterController(final GameCharacter character) {
        this.character = character;
        listeners = new PropertyChangeSupport(this);
        if (character.getClass().equals(NpcType.class)) {
            view = new NpcView(character);
        } else {
            view = new CharacterView(character);
        }
    }
    
    public void setServer(NetworkServer server){
        server.addListener(this);
        this.server = server;   
    }
    
    public void setClient(NetworkClient client) {
        client.addListener(this);
    }

    public void addListener(final PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public synchronized void update() {
        if (character.isAlive()) {
            character.update();
        }
        server.sendTCP(character);
    }

    public synchronized void render(GameContainer gameContainer) {
        if (character.isAlive()) {
            view.render(gameContainer);
        }

    }

    public synchronized GameCharacter getCharacter() {
        return character;
    }

    public synchronized int getId() {
        return character.getId();
    }

    public synchronized CharacterView getView() {
        return view;
    }

    @Override
    public synchronized void received(Connection connection, Object object) {
        Class<?> objClass = object.getClass();
        if (objClass == GameCharacter.class || objClass == NpcType.class) {
            GameCharacter recievedCharacter = (GameCharacter) object;
            if (recievedCharacter.getId() == getId()) {
                character.setCharacter(recievedCharacter);

            }
        } else if (objClass == PacketPlayerInput.class){
            PacketPlayerInput input = (PacketPlayerInput) object;
            if (input.getId() == character.getId()){
                character.setNextMoveDirection(input.getMovementDirection());
                character.setAttacking(input.getAttacking());
                character.setInteracting(input.getInteracting());
            }
        }
    }
}
