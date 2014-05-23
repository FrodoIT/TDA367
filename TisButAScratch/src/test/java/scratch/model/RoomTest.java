package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockmodules.MockIMap;
import scratch.model.mockmodules.MockModule;
import scratch.model.weapons.Weapon;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;

/**
 * Created by Anna on 2014-05-02.
 */
public class RoomTest extends TestCase {

    private Room room;
    private final Injector injector = Guice.createInjector(new MockModule());
	private DoorHandler doorHandler;
	private IMap map;

    @Before
    @Override
    public void setUp() {
        map = injector.getInstance(MockIMap.class);
	    doorHandler = new DoorHandler();
        room = new Room(map, doorHandler);
    }

    //made to create mock-players used in tests
    private GameCharacter createPlayerForTest(int id) {
        return new GameCharacter(new Rectangle2D.Double(32, 32, 100, 100), new Weapon(), 10, 2, id, "/res/playerSprite.tmx");
    }

    @Test
    public void testUpdate() {
        final GameCharacter testPlayer = createPlayerForTest(1);
        room.addCharacter(testPlayer);
        testPlayer.setMovementSpeed(0);
        final INPCMove move = injector.getInstance(INPCMove.class);
        NpcType testNpc = new NpcType(new Rectangle2D.Double(50, 55, 100, 100),
                new Weapon(), 100, 0, "test", 10, move);
		/*
	    HashMap<String,String> props1 = new HashMap<>();
	    props1.put("objectType", "door");
	    props1.put("connection", "2");
	    HashMap<String,String> props2 = new HashMap<>();
	    props2.put("objectType", "door");
	    props2.put("connection", "2");
	    final InteractiveObject testObject1 = new InteractiveObject("newTestObject", "door",10,10,32,32, props1);
	    final InteractiveObject testObject2 = new InteractiveObject("newTestObject", "door",20,20,32,32, props2);

	    room.addInteractiveObject(testObject1);
	    room.addInteractiveObject(testObject2);
	    System.out.println(room.getInteractiveObjects().size());*/

        testNpc.setNextMoveDirection(new Vector2D());
        putCharactersInRoom(testPlayer, testNpc);
        assertTrue(room.isActive());
        testNpc.registerListener(room);
        testPlayer.interact();
        testNpc.update();
        testPlayer.setAttacking(true);
	    testPlayer.setInteracting(true);
        testPlayer.update();
        assertFalse(room.getCharacterMovementMap().isEmpty());
        assertFalse(room.getAreaUnderAttack().isEmpty());
        assertFalse(room.getCharacterInteractAreaMap().isEmpty());
        assertFalse(room.getCharacterMovementMap().isEmpty());
	    assertFalse(room.getCharacterInteractAreaMap().isEmpty());
	    room.update();
        assertTrue(room.getAreaUnderAttack().isEmpty());
        assertTrue(room.getCharacterInteractAreaMap().isEmpty());
        assertTrue(room.getCharacterMovementMap().isEmpty());
		/*
	    Vector2D oldPos = new Vector2D(10,10);
	    testPlayer.setPosition(oldPos);
	    room.update();
	    assertFalse(testPlayer.getPosition().equals(oldPos));*/

    }

    private void putCharactersInRoom(GameCharacter testPlayer, NpcType testNpc) {
        room.addCharacter(testNpc);
        room.addCharacter(testPlayer);
    }

	@Test
	public void testCollision(){
		final GameCharacter testPlayer = createPlayerForTest(1);
		room.addCharacter(testPlayer);
		final InteractiveObject testObject = new MovableObject("newTestObject", "box",200,200,32,32, new HashMap());
		room.addInteractiveObject(testObject);

		//test so a player cannot move to a occupied position
		Vector2D originalPosition = new Vector2D(60,60);
		testPlayer.setPosition(originalPosition);
		room.update();
		assertTrue(testPlayer.getPosition().equals(originalPosition));

		//test so player can move to a free position
		Vector2D newPosition = new Vector2D(12,12);
		room.getCharacterMovementMap().clear();
		testObject.setPosition(newPosition);
		room.getCharacterMovementMap().put(testPlayer, newPosition);
		room.update();
		assertTrue(testPlayer.getPosition().equals(newPosition));

		//test to player cannot collide with box
		Vector2D onBoxPosition = new Vector2D(10,10);
		room.getCharacterMovementMap().clear();
		room.getCharacterMovementMap().put(testPlayer, onBoxPosition);
		room.update();
		assertTrue(testPlayer.getPosition().equals(newPosition));
	}

    @Test
    public void testRemoveNoneExistingCharacter() {
        final GameCharacter player = createPlayerForTest(0);
        final boolean removed = room.removeCharacter(player);
        assertFalse(removed);
    }

	@Test
	public void testGetDoorHandler() throws Exception {
		assertTrue(room.getDoorHandler().equals(doorHandler));
	}

	@Test
	public void testGetClosestPlayerPosition() throws Exception {
		final INPCMove move = injector.getInstance(INPCMove.class);
		NpcType testNpc = new NpcType(new Rectangle2D.Double(50, 55, 100, 100),
				new Weapon(), 100, 0, "test", 10, move);
		assertTrue(room.getClosestPlayerPosition(testNpc.getPosition()).equals(testNpc.getPosition()));
		GameCharacter gc1 = new GameCharacter();
		GameCharacter gc2 = new GameCharacter();
		room.addCharacter(gc1);
		room.addCharacter(gc2);
		gc1.setPosition(new Vector2D(0,0));
		gc2.setPosition(new Vector2D(50, 50));
		gc1.setHealth(10);
		gc2.setHealth(10);
		assertTrue(room.getClosestPlayerPosition(testNpc.getPosition()).equals(gc2.getPosition()));
	}

	@Test
	public void testClosestCharacterPositionWithNooneAlive(){
		final INPCMove move = injector.getInstance(INPCMove.class);
		NpcType testNpc = new NpcType(new Rectangle2D.Double(50, 55, 100, 100),
				new Weapon(), 100, 0, "test", 10, move);
		assertTrue(room.getClosestPlayerPosition(testNpc.getPosition()).equals(testNpc.getPosition()));
		GameCharacter gc1 = new GameCharacter();
		GameCharacter gc2 = new GameCharacter();
		room.addCharacter(gc1);
		room.addCharacter(gc2);
		gc1.setPosition(new Vector2D(0,0));
		gc2.setPosition(new Vector2D(50,50));
		assertTrue(room.getClosestPlayerPosition(testNpc.getPosition()).equals(testNpc.getPosition()));
	}

	@Test
	public void testUpdateBoxPosition() throws Exception {

	}

	@Test
	public void testGetCharacters() throws Exception {
		GameCharacter gc1 = new GameCharacter();
		GameCharacter gc2 = new GameCharacter();
		room.addCharacter(gc1);
		room.addCharacter(gc2);
		assertEquals(room.getCharacters().size(), 2);
		assertTrue(room.getCharacters().get(1).equals(gc2));
	}

	@Test
	public void testAddInteractiveObject() throws Exception {
		InteractiveObject io1 = new InteractiveObject();
		InteractiveObject io2 = new MovableObject();
		room.addInteractiveObject(io1);
		room.addInteractiveObject(io2);
		assertEquals(room.getInteractiveObjects().size(), 2);
		assertTrue(room.getInteractiveObjects().get(0).equals(io1));
		assertTrue(room.getInteractiveObjects().get(1).equals(io2));

	}
	@Test
	public void testSerialization() throws Exception {
		/*Kryo kryo = new Kryo();
		Output output = new Output(200);

		room.write(kryo, output);
		Room newRoom = new Room();
		Input input = new Input(output.getBuffer());
		room.read(kryo, input);
		assertTrue(room.equals(newRoom)); */

	}
	@Test
	public void testGetId() throws Exception {
		assertEquals(room.getId(),1);

	}

	@Test
	public void testGetMap() throws Exception {
		assertTrue(room.getMap().equals(map));

	}
}
