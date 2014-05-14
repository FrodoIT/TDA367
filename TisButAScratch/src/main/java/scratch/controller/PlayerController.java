package scratch.controller;

import com.google.inject.Inject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import scratch.model.Player;
import scratch.network.NetworkServer;
import scratch.view.PlayerView;

/**
 * Class to collect input for the player.
 * @author Anna Nylander
 *
 */
public final class PlayerController{
    @Inject
    private Player player;
    private final PlayerView playerView;
    private final PropertyChangeSupport listeners;
    

    public PlayerController(Player player, PlayerView playerView){
        this.player = player;
        this.playerView = playerView;
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


    public PlayerView getPlayerView() {
        return playerView;
    }


}
