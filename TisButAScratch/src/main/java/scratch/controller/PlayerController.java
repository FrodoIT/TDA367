package scratch.controller;

import com.google.inject.Inject;
import org.newdawn.slick.GameContainer;
import scratch.model.Player;
import scratch.view.CharacterView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class to collect input for the player.
 *
 * @author Anna Nylander
 *
 */
public final class PlayerController {

    @Inject
    private Player player;
    private final PropertyChangeSupport listeners;
    private final CharacterView characterView;

    public PlayerController(Player player, CharacterView characterView) {
        this.player = player;
        this.characterView = characterView;
        listeners = new PropertyChangeSupport(this);
    }
    
    public void addListener(PropertyChangeListener listener){
        listeners.addPropertyChangeListener(listener);
    }

    public void updatePlayer() {
        player.update();
        listeners.firePropertyChange(null, null, player);
   }
    
    public int getId(){
        return player.getId();
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }

    public void render(GameContainer gameContainer) {
        characterView.render(gameContainer);
    }

    public CharacterView getView() {
        return characterView;
    }

}
