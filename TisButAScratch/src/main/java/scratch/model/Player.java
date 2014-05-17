package scratch.model;

import org.simpleframework.xml.Element;
import scratch.controller.PlayerInput;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;

/**
 * Logical representation of the Player in the game.
 *
 * @author Anna Nylander TODO: Add logic for colission detection here through
 * IRoomData.
 *
 */
public final class Player extends GameCharacter {

    @Element(type = IPlayerInput.class, required = false)
    private IPlayerInput playerInput;

    /**
     * Needed for serialization, should not be used
     */
    public Player() {
        super();
    }

    public Player(IPlayerInput playerInput, Rectangle2D.Double unitTile, int id, String imagePath) {
        super(unitTile, new DefaultWeapon(), 10, 2, id, imagePath);
        this.playerInput = playerInput;
    }

    public void setPlayerInput(PlayerInput playerInput) {
        this.playerInput = playerInput;
    }


    /*
     * Give Player opportunity to execute an performAttack
     *
     * @return null if no performAttack is done, otherwise the area that the NPC
     * attacks
     * @pre The player has pressed attackbutton and the weapon has cooldowned.
     *
     public IPlayerInput getPlayerInput() {
     return playerInput;
     }
     */
}
