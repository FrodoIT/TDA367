package scratch.model;

import com.google.inject.Inject;

import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Represents a single room and the contents in it.
 * @author Ivar Josefsson
 *
 */
public final class Room implements IRoomData, CharacterChangeListener{
    @Inject

    private List<Player> players;
    private Map<AbstractCharacter, Vector2D> characterMovementMap = new HashMap<>();
    private List<AbstractCharacter> charactersInteracting = new ArrayList<>();
    private List<AbstractCharacter> areaUnderAttack = new ArrayList<>();
    private Map<Integer, NpcType> npcs;
    private final IMap map;
    private List<IInteractiveObject> interactiveObjects = new ArrayList<>();
    private DoorHandler doorHandler;

    public Room(IMap collisionMap, DoorHandler doorHandler){
        this.map = collisionMap;
        this.doorHandler = doorHandler;
        players = new ArrayList();
        npcs = new TreeMap<Integer, NpcType>();
    }


    public void update(){
        updateCharacterMovements();
        updateCharacterAttacks();
        updateCharacterInteractions();
    }

    public boolean hasPlayers(){
        return !players.isEmpty();
    }

    private void updateCharacterInteractions() {
        for(AbstractCharacter character : charactersInteracting){
            for(IInteractiveObject interactiveObject : interactiveObjects){
                if (character.getUnitTile().intersects(interactiveObject.getArea())){
                    //TODO do the interact stuff. either implement a interact method or find respective interactable object
                    // here and run different methods depending on what kind of object is interacted with
                    doorHandler.interactHappened(this, character, interactiveObject );
                    break;
                }
            }
        }
        charactersInteracting.clear();
    }

    private void updateCharacterMovements() {

        for(Map.Entry<AbstractCharacter, Vector2D> inputEntry : characterMovementMap.entrySet()) {
            AbstractCharacter character = inputEntry.getKey();
            character.setPosition(allowedPosition(character.getUnitTile(), inputEntry.getValue()));
        }
        characterMovementMap.clear();
    }

    private void updateCharacterAttacks() {
        dealDamage();
        areaUnderAttack.clear();
    }

    private boolean dealDamage(){
        for (AbstractCharacter attackingCharacter : areaUnderAttack) {
            if(!npcs.isEmpty()){
                for(Map.Entry<Integer, NpcType> npcEntry: npcs.entrySet()){
                    if((npcEntry.getValue().getUnitTile().intersects( attackingCharacter.getAttackArea())) &&
                            !attackingCharacter.getClass().equals(npcEntry.getValue().getClass())){
                        npcEntry.getValue().takeDamage(attackingCharacter.getDamage());

                        break; //an attack should only damage one character at the time. Should it? Should it really?
                    }
                }
                for(Player player: players){
                    if((player.getUnitTile().intersects( attackingCharacter.getAttackArea()) )&&
                            !attackingCharacter.getClass().equals(player.getClass())){
                        player.takeDamage(attackingCharacter.getDamage());
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Called to determine the best allowed position
     * @param unitTile the position we start from
     * @param toPosition the position we want to end at
     * @return the best allowed position
     */
    private Vector2D allowedPosition(Rectangle2D.Double unitTile, Vector2D toPosition){

        double newX = toPosition.getX();
        double newXRight = newX + unitTile.getWidth();
        double newY = toPosition.getY();
        double newYDown = newY + unitTile.getHeight();
        double oldX = unitTile.getX();
        double oldY = unitTile.getY();
        double returnX = oldX;
        double returnY = oldY;

        //Check if new X position is allowed
        if(0 < newX && newXRight < getMapWidth() && !mapCollision(unitTile, new Vector2D(newX, oldY))){
            returnX = newX;
        }

        //Check if new Y position is allowed
        if(0 < newY && newYDown < getMapHeight() && !mapCollision(unitTile, new Vector2D(oldX, newY))){
            returnY = newY;
        }
        return new Vector2D(returnX, returnY);
    }

    /**
     * Checks if there's a collision at the given coordinates
     * @param objectToPlace A "hitbox" of the object to place at placeToPut
     * @param placeToPut the place to put objectToPlace at
     * @return true if there is a collision
     */
    private boolean mapCollision(Rectangle2D.Double objectToPlace, Vector2D placeToPut){

        Vector2D northWest = new Vector2D (placeToPut.getX() + 1, placeToPut.getY() + 1);
        Vector2D northEast = new Vector2D (placeToPut.getX() + objectToPlace.getWidth() - 1, placeToPut.getY() + 1);
        Vector2D southWest = new Vector2D(placeToPut.getX()+ 1, placeToPut.getY() + objectToPlace.getHeight() - 1);
        Vector2D southEast = new Vector2D (placeToPut.getX() + objectToPlace.getWidth() - 1, placeToPut.getY() + objectToPlace.getHeight() - 1);

        return map.isColliding(northWest) || map.isColliding(northEast) || map.isColliding(southEast) || map.isColliding(southWest);
    }

    public void addInteractivObject(IInteractiveObject interactiveObject) {
        this.interactiveObjects.add(interactiveObject);
    }

    public void addNpc(Map<Integer, NpcType> npcs) {
        this.npcs = npcs;
    }

    /**
     * Adds the specified character from the Room
     * @param character
     */
    public void enterRoom(AbstractCharacter character){
        players.add((Player)character); //TODO this will be rafactored when Player and Npc use the same move pattern. or erlier
        character.registerListener(this);
    }
    /**
     * Removes the specified character from the Room
     * @param character
     */
    public boolean exitRoom(AbstractCharacter character){
        character.removeListener(this);
        return players.remove(character);
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

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Map<Integer, NpcType> getNpcs() {
        return npcs;
    }

    public List<AbstractCharacter> getAreaUnderAttack() {
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
    public void handleCharacterMovement(AbstractCharacter character, Vector2D movement) {
        characterMovementMap.put(character, movement);
    }

    @Override
    public void handleCharacterAttack(AbstractCharacter character) {
        areaUnderAttack.add(character);
        System.out.println("Attack added from " + character);
    }

    public Map<AbstractCharacter, Vector2D> getCharacterMovementMap() {
        return characterMovementMap;
    }

    public List<AbstractCharacter> getCharacterInteractAreaMap() {
        return charactersInteracting;
    }

    public DoorHandler getDoorHandler() {
        return doorHandler;
    }

    @Override
    public void handleCharacterInteraction(AbstractCharacter character) {
        charactersInteracting.add(character);
    }
}
