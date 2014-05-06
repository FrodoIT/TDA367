package scratch.model.mockModules;

import com.google.inject.AbstractModule;
import scratch.construction.SlickMap;
import scratch.model.*;
import scratch.model.weapons.IWeapon;
import scratch.model.IPlayerInput;
import scratch.model.IRoomData;


/**
 * Created by pippin on 5/3/14.
 */
public class MockModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IPlayerInput.class).to(MockPlayerInput.class);
	    bind(IMap.class).to(MockIMap.class);
	    bind(IWeapon.class).to(MockWeapon.class);
	    bind(INPCMove.class).to(MockINPCMove.class);
        bind(IRoomData.class).to(MockIRoomData.class);

    }
}
