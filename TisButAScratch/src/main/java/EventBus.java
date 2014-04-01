import java.util.Set;
import java.util.TreeSet;

import eventhandler.EventWrapper;

/**
 * EventBus as a Singleton 
 * @author Alma Ottedag, Ivar Josefsson
 * @revisedBy Anna Nylander
 *
 */
public class EventBus{
	private Set<EventWrapper> subscribers = new TreeSet<EventWrapper>();
	private EventBus eventBus=null;
	
	private EventBus(){
		//TODO implement usage of the annotation @singleton instead.
	}
	
	public EventBus getInstance(){
		if (eventBus==null){
			eventBus= new EventBus();
		}
		return eventBus;
		
	}
	public void addSubscriber(EventWrapper listener){
		subscribers.add(listener);
	}
	
	public void removeSubsriber(EventWrapper listener){
		subscribers.remove(listener);
	}

	public void fireEvent(int id, Object obj) {
		for (EventWrapper s: subscribers){
			s.doEvent(id, obj);
		}
	}
	
	
}
