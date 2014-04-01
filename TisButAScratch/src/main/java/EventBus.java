import interfaces.WrapperListener;

import java.util.Observable;
import java.util.Observer;

import model.ModelWrapper;
import view.ViewWrapper;
import controller.ControllerWrapper;

/**
 * 
 * @author Alma Ottedag, Ivar Josefsson
 *
 */
public class EventBus implements Observer{
	private ModelWrapper wModel;
	private ControllerWrapper wController;
	private ViewWrapper wView;
	
	public EventBus(ModelWrapper wModel, ControllerWrapper wController, ViewWrapper wView){
		this.wModel = wModel;
		this.wView = wView;
		this.wController = wController;
	}
	
	public void addSubscriber(WrapperListener listener){
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
