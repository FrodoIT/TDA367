package scratch.model;

/**
 * Created by pippin on 5/8/14.
 */
public interface CharacterChangeListener {
    void handleCharacterMovement(GameCharacter character, Vector2D movement);
    void handleCharacterAttack(GameCharacter character);
    void handleCharacterInteraction(GameCharacter character);
}
