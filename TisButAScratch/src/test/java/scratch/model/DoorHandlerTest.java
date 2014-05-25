package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoorHandlerTest extends TestCase {

    private Room room1;
    private GameCharacter character;

    private static final String CONNECTION_PROPERTY = "connection";
    private static final String OBJECT_TYPE_DOOR = "door";

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        final Injector injector = Guice.createInjector(new MockModule());

        final DoorHandler doorHandler = new DoorHandler();
        final Room room2;
        final IMap map = injector.getInstance(IMap.class);
        
        room1 = new Room(map, doorHandler);
        room2 = new Room(map, doorHandler);

        final List<InteractiveObject> testDoorsRoom1 = getTestDoorsRoom1();
        doorHandler.addDoors(room2, testDoorsRoom1);
        for (final InteractiveObject iInteractiveObject : testDoorsRoom1) {
            room1.addInteractiveObject(iInteractiveObject);
        }

        final List<InteractiveObject> testDoorsRoom2 = getTestDoorsRoom2();
        doorHandler.addDoors(room1, testDoorsRoom2);
        for (final InteractiveObject iInteractiveObject : testDoorsRoom2) {
            room2.addInteractiveObject(iInteractiveObject);
        }


        character = new GameCharacter(new Rectangle2D.Double(32, 32, 32, 32), new Weapon(), 10, 2, 1, "/res/playerSprite.tmx");
        room1.addCharacter(character);

    }

    @Test
    public void testInteractHappened() throws Exception {
        final Vector2D pos = new Vector2D(0, 0);
        character.setPosition(pos);
        character.setInteracting(true);
        character.update();
        room1.update();
        assertNotSame(pos, character.getPosition());
    }

    @Test
    public void testInteractHappenedWithoutConnectingDoor() {
        final Vector2D pos = new Vector2D(64, 0);
        character.setPosition(pos);
        character.setInteracting(true);
        character.update();
        room1.update();
        assertEquals(pos, character.getPosition());


    }

    private List<InteractiveObject> getTestDoorsRoom1() {
        final List<InteractiveObject> doors = new ArrayList<>();

        Map<String, String> properties;


        properties = new HashMap<>();
        properties.put("id", "100");
        properties.put(CONNECTION_PROPERTY, "1");
        doors.add(
                new InteractiveObject("door1", OBJECT_TYPE_DOOR, 0, 0, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "101");
        properties.put(CONNECTION_PROPERTY, "1");
        doors.add(
                new InteractiveObject("door2", OBJECT_TYPE_DOOR, 64, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "102");
        properties.put(CONNECTION_PROPERTY, "1");
        doors.add(
                new InteractiveObject("door3", OBJECT_TYPE_DOOR, 0, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "103");
        properties.put(CONNECTION_PROPERTY, "2");
        doors.add(
                new InteractiveObject("door4", OBJECT_TYPE_DOOR, 64, 0, 32, 32, properties)
        );

        return doors;
    }

    private List<InteractiveObject> getTestDoorsRoom2() {
        final List<InteractiveObject> doors = new ArrayList<>();

        Map<String, String> properties;

        properties = new HashMap<>();
        properties.put("id", "200");
        properties.put(CONNECTION_PROPERTY, "1");
        doors.add(
                new InteractiveObject("door1", OBJECT_TYPE_DOOR, 0, 0, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "201");
        properties.put(CONNECTION_PROPERTY, "1");
        doors.add(
                new InteractiveObject("door2", OBJECT_TYPE_DOOR, 64, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "202");
        properties.put(CONNECTION_PROPERTY, "1");
        doors.add(
                new InteractiveObject("door3", OBJECT_TYPE_DOOR, 0, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "203");
        properties.put(CONNECTION_PROPERTY, "3");
        doors.add(
                new InteractiveObject("door4", OBJECT_TYPE_DOOR, 64, 0, 32, 32, properties)
        );

        return doors;
    }
}