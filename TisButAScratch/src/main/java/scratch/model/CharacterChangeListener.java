package scratch.model;

/**
 * Created by pippin on 5/8/14.
 */
public interface CharacterChangeListener {
    void handleCharacterMovement(AbstractCharacter character, Vector2D movement);
    void handleCharacterAttack(AbstractCharacter character);
    void handleCharacterInteraction(AbstractCharacter character);
}
