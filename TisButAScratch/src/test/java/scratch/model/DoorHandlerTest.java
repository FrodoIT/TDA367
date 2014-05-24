package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoorHandlerTest extends TestCase {

    private Room room1;
    private GameCharacter character;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        Injector injector = Guice.createInjector(new MockModule());

        final DoorHandler doorHandler = new DoorHandler();
        final Room room2;

        room1 = new Room(
                injector.getInstance(IMap.class),
                doorHandler
        );

        room2 = new Room(
                injector.getInstance(IMap.class),
                doorHandler
        );

        List<InteractiveObject> testDoorsRoom1 = getTestDoorsRoom1();
        doorHandler.addDoors(room2, testDoorsRoom1);
        for (InteractiveObject iInteractiveObject : testDoorsRoom1) {
            room1.addInteractiveObject(iInteractiveObject);
        }

        List<InteractiveObject> testDoorsRoom2 = getTestDoorsRoom2();
        doorHandler.addDoors(room1, testDoorsRoom2);
        for (InteractiveObject iInteractiveObject : testDoorsRoom2) {
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
        properties.put("connection", "1");
        doors.add(
                new InteractiveObject("door1", "door", 0, 0, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "101");
        properties.put("connection", "1");
        doors.add(
                new InteractiveObject("door2", "door", 64, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "102");
        properties.put("connection", "1");
        doors.add(
                new InteractiveObject("door3", "door", 0, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "103");
        properties.put("connection", "2");
        doors.add(
                new InteractiveObject("door4", "door", 64, 0, 32, 32, properties)
        );

        return doors;
    }

    private List<InteractiveObject> getTestDoorsRoom2() {
        final List<InteractiveObject> doors = new ArrayList<>();

        Map<String, String> properties;

        properties = new HashMap<>();
        properties.put("id", "200");
        properties.put("connection", "1");
        doors.add(
                new InteractiveObject("door1", "door", 0, 0, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "201");
        properties.put("connection", "1");
        doors.add(
                new InteractiveObject("door2", "door", 64, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "202");
        properties.put("connection", "1");
        doors.add(
                new InteractiveObject("door3", "door", 0, 64, 32, 32, properties)
        );

        properties = new HashMap<>();
        properties.put("id", "203");
        properties.put("connection", "3");
        doors.add(
                new InteractiveObject("door4", "door", 64, 0, 32, 32, properties)
        );

        return doors;
    }
}