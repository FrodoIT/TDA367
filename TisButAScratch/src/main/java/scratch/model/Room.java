package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.inject.Inject;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a single room and the contents in it.
 *
 * @author Ivar Josefsson
 *
 */
public final class Room implements IRoomData, CharacterChangeListener, KryoSerializable {

    @Inject

    private List<GameCharacter> characters;
    private final Map<GameCharacter, Vector2D> characterMovementMap = new HashMap<>();
    private final List<GameCharacter> charactersInteracting = new ArrayList<>();
    private final List<GameCharacter> areaUnderAttack = new ArrayList<>();
    private IMap map;
    private List<IInteractiveObject> interactiveObjects;
    private DoorHandler doorHandler;

    public Room() {

    }

    public Room(IMap collisionMap, DoorHandler doorHandler) {
        this.map = collisionMap;
        this.doorHandler = doorHandler;
        characters = new ArrayList();
        interactiveObjects = new ArrayList<>();
    }

    public void update() {
        updateCharacterMovements();
        updateCharacterAttacks();
        updateCharacterInteractions();
    }

    public boolean isActive() {
        for (GameCharacter character:characters){
            if (character instanceof NpcType){
                
            } else {
                return true;
            }
        }
        return false;
    }

    private void updateCharacterInteractions() {
        for (GameCharacter character : charactersInteracting) {
            for (IInteractiveObject interactiveObject : interactiveObjects) {
                if (character.getUnitTile().intersects(interactiveObject.getArea())) {
                    //TODO do the interact stuff. either implement a interact method or find respective interactable object
                    // here and run different methods depending on what kind of object is interacted with
                    doorHandler.interactHappened(this, character, interactiveObject);
                    break;
                }
            }
        }
        charactersInteracting.clear();
    }

    private void updateCharacterMovements() {
        for (Map.Entry<GameCharacter, Vector2D> inputEntry : characterMovementMap.entrySet()) {
            GameCharacter character = inputEntry.getKey();
            character.setPosition(allowedPosition(character.getUnitTile(), inputEntry.getValue()));
        }
        characterMovementMap.clear();
    }

    private void updateCharacterAttacks() {
        dealDamage();
        areaUnderAttack.clear();
    }

    
    private void dealDamage() {
        for (GameCharacter attackingCharacter : areaUnderAttack) {
            for (GameCharacter character: characters){
                if ((character.getUnitTile().intersects(attackingCharacter.getAttackArea())) && 
                        !attackingCharacter.getClass().equals(character.getClass())){
                    character.takeDamage(attackingCharacter.getDamage());
                }
            }
        }
    }

    /**
     * Called to determine the best allowed position
     *
     * @param unitTile the position we start from
     * @param toPosition the position we want to end at
     * @return the best allowed position
     */
    private Vector2D allowedPosition(Rectangle2D.Double unitTile, Vector2D toPosition) {

        double newX = toPosition.getX();
        double newXRight = newX + unitTile.getWidth();
        double newY = toPosition.getY();
        double newYDown = newY + unitTile.getHeight();
        double oldX = unitTile.getX();
        double oldY = unitTile.getY();
        double returnX = oldX;
        double returnY = oldY;

        //Check if new X position is allowed
        if (0 < newX && newXRight < getMapWidth() && !mapCollision(unitTile, new Vector2D(newX, oldY))) {
            returnX = newX;
        }

        //Check if new Y position is allowed
        if (0 < newY && newYDown < getMapHeight() && !mapCollision(unitTile, new Vector2D(oldX, newY))) {
            returnY = newY;
        }
        return new Vector2D(returnX, returnY);
    }

    /**
     * Checks if there's a collision at the given coordinates
     *
     * @param objectToPlace A "hitbox" of the object to place at placeToPut
     * @param placeToPut the place to put objectToPlace at
     * @return true if there is a collision
     */
    private boolean mapCollision(Rectangle2D.Double objectToPlace, Vector2D placeToPut) {

        Vector2D northWest = new Vector2D(placeToPut.getX() + 1, placeToPut.getY() + 1);
        Vector2D northEast = new Vector2D(placeToPut.getX() + objectToPlace.getWidth() - 1, placeToPut.getY() + 1);
        Vector2D southWest = new Vector2D(placeToPut.getX() + 1, placeToPut.getY() + objectToPlace.getHeight() - 1);
        Vector2D southEast = new Vector2D(placeToPut.getX() + objectToPlace.getWidth() - 1, placeToPut.getY() + objectToPlace.getHeight() - 1);

        return map.isColliding(northWest) || map.isColliding(northEast) || map.isColliding(southEast) || map.isColliding(southWest);
    }

    public void addInteractivObject(IInteractiveObject interactiveObject) {
        this.interactiveObjects.add(interactiveObject);
    }

    public void addCharacter(GameCharacter character) {
        character.registerListener(this);
        characters.add(character);
    }

    public boolean removeCharacter(GameCharacter character) {
        character.removeListener(this);
        return characters.remove(character);
    }

    /**
     *
     * @return: the total height of the map in pixels
     */
    private int getMapHeight() {
        return map.getHeight();
    }

    /**
     *
     * @return: the total width of the map in pixels
     */
    private int getMapWidth() {
        return map.getWidth();
    }
    
    public int getId() {
        return map.getId();
    }

    @Override
    public List<GameCharacter> getCharacters(){
        return characters;
    }

    public List<GameCharacter> getAreaUnderAttack() {
        return areaUnderAttack;
    }

    public List<IInteractiveObject> getInteractiveObjects() {
        return interactiveObjects;
    }

    @Override
    public IMap getMap() {
        return map;
    }

    @Override
    public void handleCharacterMovement(GameCharacter character, Vector2D movement) {
        characterMovementMap.put(character, movement);
    }

    @Override
    public void handleCharacterAttack(GameCharacter character) {
        areaUnderAttack.add(character);
    }

    public Map<GameCharacter, Vector2D> getCharacterMovementMap() {
        return characterMovementMap;
    }

    public List<GameCharacter> getCharacterInteractAreaMap() {
        return charactersInteracting;
    }

    public DoorHandler getDoorHandler() {
        return doorHandler;
    }

    @Override
    public void handleCharacterInteraction(GameCharacter character) {
        charactersInteracting.add(character);
    }

    @Override
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, characters);
        /*
         private Map<AbstractCharacter, Vector2D> characterMovementMap = new HashMap<>();
         private List<AbstractCharacter> charactersInteracting = new ArrayList<>();
         private List<AbstractCharacter> areaUnderAttack = new ArrayList<>();
         private List<NpcType> npcs;
         private final IMap map;
         private List<IInteractiveObject> interactiveObjects;
         private DoorHandler doorHandler;*/
    }

    @Override
    public void read(Kryo kryo, Input input) {
        characters = kryo.readObject(input, ArrayList.class);
    }
    
    @Override
    public Vector2D getClosestPlayerPosition(Vector2D position){
        //TODO Fix proper checking
        for (GameCharacter character : characters){
            if (character instanceof GameCharacter){
                return character.getPosition();
            }
        }
        return position;
    }
}
