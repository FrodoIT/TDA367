package model;

import eventhandler.EventWrapper;

/**
 * Wrapper for the whole Model listening to events coming from the EventBus.
 * @author Alma Ottedag, Ivar Josefsson
 *
 */
public class ModelWrapper extends Model implements EventWrapper{

	@Override
	public void doEvent(int id, Object obj) {
		
	}

}

