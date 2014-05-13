package scratch.model;

import java.awt.geom.Rectangle2D;

/**
 * Created by pippin on 5/8/14.
 */
public interface CharacterChangeListener {
    public void handleCharacterMovement(AbstractCharacter character, Vector2D movement);
    public void handleCharacterAttack(AbstractCharacter character);
    public void handleCharacterInteraction(AbstractCharacter character, Rectangle2D.Double characterArea);
}
