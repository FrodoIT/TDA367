package scratch.controller;

import com.google.inject.Inject;
import org.newdawn.slick.GameContainer;
import scratch.model.Player;
import scratch.view.CharacterView;

/**
 * Class to collect input for the player.
 *
 * @author Anna Nylander
 *
 */
public final class PlayerController {

    @Inject

    private final CharacterView characterView;
    private final Player player;

    public PlayerController(Player player, CharacterView characterView) {
        this.player = player;
        this.characterView = characterView;
    }

    public void updatePlayer() {
        player.update();
    }

    public void render(GameContainer gameContainer) {
        characterView.render(gameContainer);
    }

    public CharacterView getView() {
        return characterView;
    }

}
