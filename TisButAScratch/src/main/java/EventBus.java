import interfaces.EventWrapper;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

/**
 * EventBus as a Singleton 
 * @author Alma Ottedag, Ivar Josefsson
 * @revisedBy Anna Nylander
 *
 */
public class EventBus implements Observer{
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
