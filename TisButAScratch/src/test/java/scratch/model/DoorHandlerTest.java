package scratch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import scratch.model.mockModules.MockModule;

import java.awt.geom.Rectangle2D;
import java.util.Properties;

public class DoorHandlerTest extends TestCase {

    private Room room;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        Injector injector = Guice.createInjector(new MockModule());

        DoorHandler doorHandler = new DoorHandler();

        room = new Room(
                injector.getInstance(IMap.class),
                doorHandler
        );

        doorHandler.addDoor(room, new IInteractiveObject() {
            @Override
            public String getName() {
                return "door1";
            }

            @Override
            public String getType() {
                return "door";
            }

            @Override
            public Rectangle2D.Double getArea() {
                return new Rectangle2D.Double(32, 32, 64, 64);
            }

            @Override
            public Properties getProperties() {
                Properties props = new Properties();
                props.put("connection", "1");
                return props;
            }
        });


    }

    @Test
    public void testInteractHappened() throws Exception {

    }
}