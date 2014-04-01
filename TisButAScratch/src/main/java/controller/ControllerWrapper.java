package controller;

import eventhandler.EventWrapper;

/**
 * Wrapper for the Controller listening to events coming from the EventBus.
 * @author Alma Ottedag, Ivar Josefsson
 *
 */
public class ControllerWrapper extends Controller implements EventWrapper{

	@Override
	public void doEvent(int id, Object obj) {
		
	}

}
