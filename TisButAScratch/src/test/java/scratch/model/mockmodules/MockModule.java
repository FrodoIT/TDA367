package scratch.model.mockmodules;

import com.google.inject.AbstractModule;
import scratch.model.*;
import scratch.model.weapons.IWeapon;


/**
 * Created by pippin on 5/3/14.
 */
public class MockModule extends AbstractModule {

    @Override
    protected void configure() {
	    bind(IMap.class).to(MockIMap.class);
	    bind(IWeapon.class).to(MockIWeapon.class);
	    bind(INPCMove.class).to(MockINPCMove.class);
        bind(IRoomData.class).to(MockIRoomData.class);
        bind(IInteractiveObject.class).to(MockIInteractiveObject.class);

    }
}
