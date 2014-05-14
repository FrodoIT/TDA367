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
    private final Player player;
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
   }


    public PlayerView getPlayerView() {
        return playerView;
    }


}
