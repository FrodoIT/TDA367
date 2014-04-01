package model;

import interfaces.EventWrapper;

/**
 * Wrapper for the whole Model listening to events coming from the EventBus.
 * @author Alma Ottedag, Ivar Josefsson
 *
 */
public class ModelWrapper extends Model implements EventWrapper{

	@Override
	public void fireEvent(int id, Object obj) {
		
	}

}

