package view;

import eventhandler.EventWrapper;


/**
 * Wrapper for the whole View, listens to events from the Eventbus and delegates tasks.
 * @author Alma Ottedag, Ivar Josefsson
 *
 */
public class ViewWrapper extends View implements EventWrapper{

	@Override
	public void doEvent(int id, Object obj) {
		
	}

}
