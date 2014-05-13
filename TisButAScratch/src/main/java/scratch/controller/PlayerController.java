package scratch.controller;

import com.google.inject.Inject;
import org.newdawn.slick.Input;
import scratch.model.IPlayerInput;
import scratch.model.MoveDirection;
import scratch.model.Player;
import scratch.view.PlayerView;

/**
 * Class to collect input for the player.
 * @author Anna Nylander
 *
 */
public final class PlayerController{
    @Inject

    private final PlayerView playerView;
    private final Player player;

    public PlayerController(Player player, PlayerView playerView){
        this.player = player;
        this.playerView = playerView;
    }


    public void updatePlayer(){
        player.update();
        playerView.render();
    }


    public PlayerView getPlayerView() {
        return playerView;
    }


}
