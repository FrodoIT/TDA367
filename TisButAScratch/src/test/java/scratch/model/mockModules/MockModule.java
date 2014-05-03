package scratch.model.mockModules;

import com.google.inject.AbstractModule;
import scratch.model.IPlayerInput;
import scratch.model.IRoomData;
import scratch.model.Room;

/**
 * Created by pippin on 5/3/14.
 */
public class MockModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IPlayerInput.class).to(MockPlayerInput.class);
        bind(IRoomData.class).to(MockRoom.class);
    }
}
